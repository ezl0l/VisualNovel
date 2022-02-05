package com.ezlol.visualnovella;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Engine implements Dialog.OnDialogEndListener {
    public interface OnChoiceChangeListener {
        void onChoiceChange(Dialog.Choice choice);
    }

    @Override
    public void onDialogEnd(Dialog dialog) {
        Log.d("My", "Dialog end " + dialogQueue.size());
        if(dialog == null)
            Log.w("My", "Dialog is null");

//        if(dialog != null) {
//            if (dialog.onDialogEndListenerNonStatic != null)
//                dialog.onDialogEndListenerNonStatic.onDialogEnd(dialog);
//        }

        if(onDialogEndListener != null)
            onDialogEndListener.onDialogEnd(dialog);

        currentDialog = dialogQueue.poll();

        if(currentDialog == null) return;

        if(onPhraseChangeListener != null)
            onPhraseChangeListener.onPhraseChange(currentDialog.getCurrentPhrase());

        Log.d("My", "CurrentDialog:" + currentDialog.phrases.length);
    }

    private static void kickDialogQueue() {
        if(dialogQueue.size() == 0) return;

        if(currentDialog != null && !currentDialog.isEnded) return;

        currentDialog = dialogQueue.poll();

        if(currentDialog == null) return;

        if(onPhraseChangeListener != null)
            onPhraseChangeListener.onPhraseChange(currentDialog.getCurrentPhrase());
    }

    static class Resource {
        enum Type {
            IMAGE_TYPE,
            AUDIO_TYPE
        }

        private String path;
        private byte[] data;
        private Type type;

        public Resource(String path, byte[] data, Type type) {
            this.path = path;
            this.data = data;
            this.type = type;
        }

        public String getPath() {
            return path;
        }

        public byte[] getData() {
            return data;
        }

        public Type getType() {
            return type;
        }
    }

    protected static SL.Quest quest;
    public static Map<String, Resource> resources = new HashMap<>();
    public static List<String> flags = new ArrayList<>();

    public static Queue<Dialog> dialogQueue = new LinkedList<>();
    public static Dialog currentDialog = null;

    private static OnSceneChangeListener onSceneChangeListener = null;
    private static OnPhraseChangeListener onPhraseChangeListener = null;
    private static Dialog.OnDialogEndListener onDialogEndListener = null;
    private static OnChoiceChangeListener onChoiceChangeListener = null;

    public static Dialog.Choice currentChoice = null;

    {
        Dialog.onDialogEndListener = this;
    }

    public Engine() {
    }

    public Engine(SL.Quest quest) {
        Engine.quest = quest;
    }

    private static byte[] readFile(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }

    public static void execCommand(SL.Command command, Environment environment) throws UnknownCommandException,
            IOException, SceneNotFoundException {
        switch(command.name) {
            case "load_image": {
                System.out.println("[INFO] Loading image from " + environment.PATH + command.params[1] + "...");
                resources.put(command.params[0], new Resource(environment.PATH + command.params[1],
                        readFile(environment.PATH + command.params[1]),
                        Resource.Type.IMAGE_TYPE));
                break;
            }

            case "load_audio": {
                System.out.println("[INFO] Loading audio from " + environment.PATH + command.params[1] + "...");
                resources.put(command.params[0], new Resource(environment.PATH + command.params[1],
                        readFile(environment.PATH + command.params[1]),
                        Resource.Type.AUDIO_TYPE));
                break;
            }

            case "if": {
                if(command.params[0].equals("true")) {
                    String[] commandParams = new String[command.params.length - 2];
                    System.arraycopy(command.params, 2, commandParams, 0, commandParams.length);
                    execCommand(new SL.Command(command.params[1], commandParams), environment);
                }
                break;
            }

            case "goto": {
                SL.Scene searchScene = null;
                for (SL.Scene scene :
                        quest.scenes) {
                    if(scene.name.equals(command.params[0])) {
                        searchScene = scene;
                        break;
                    }
                }
                if(searchScene == null)
                    throw new SceneNotFoundException();
                Engine.setCurrentScene(searchScene);
                break;
            }

            case "add_dialog": {
                Log.e("My", command.name + " " + Arrays.toString(command.params));
                Dialog dialog = quest.findDialog(Integer.parseInt(command.params[0]));

                dialogQueue.add(dialog);

                kickDialogQueue();

                if(command.params.length > 1) {
                    SL.Action action = environment.scene.findAction(command.params[1]);
                    if(action != null) {
                        dialog.onDialogEndListenerNonStatic = dialog1 -> {
                            try {
                                action.exec(environment);
                            } catch (UnknownCommandException | IOException | SceneNotFoundException e) {
                                e.printStackTrace();
                            }
                        };
                    }
                }
                break;
            }

            case "set_flag":
                if(!flags.contains(command.params[0]))
                    flags.add(command.params[0]);
                break;

            case "remove_flag":
                flags.remove(command.params[0]);
                break;

            case "check_flag":
                if(flags.contains(command.params[0]))
                    environment.scene.findAction(command.params[1]).exec(environment);
                break;

            case "choice":
                if(command.params.length > 4)
                    Log.w(Engine.class.getSimpleName(), "This 'choice' command syntax isn't supported yet ;/");

                Dialog.Choice choice = new Dialog.Choice(environment, command.params[0], command.params[1],
                        command.params[2], command.params[3]);

                currentChoice = choice;

                if(onChoiceChangeListener != null)
                    onChoiceChangeListener.onChoiceChange(choice);
                break;

            default:
                throw new UnknownCommandException();
        }
    }

    public void init() throws UnknownCommandException, IOException, SceneNotFoundException {
        for (SL.Scene scene :
                quest.scenes) {
            scene.init(new Environment("", scene));
        }
    }

    public static void setOnDialogEndListener(Dialog.OnDialogEndListener onDialogEndListener) {
        Engine.onDialogEndListener = onDialogEndListener;
    }

    public boolean gameDialogClick() {
        if(currentDialog == null) return false;

        if(!currentDialog.next()) {
            //currentDialog = null; // надо ли?
            if(dialogQueue.size() > 0) {
                this.onDialogEnd(currentDialog);
                return true;
            }
            return false;
        }

        if(onPhraseChangeListener != null)
            onPhraseChangeListener.onPhraseChange(currentDialog.getCurrentPhrase());
        return true;
    }

    public void declineClick() throws UnknownCommandException, IOException, SceneNotFoundException {
        if(currentChoice == null) return;
        SL.Action declineAction = currentChoice.environment.scene.findAction(currentChoice.declineAction);
        if(declineAction != null)
            declineAction.exec(currentChoice.environment);
        currentChoice = null;
    }

    public void agreeClick() throws UnknownCommandException, IOException, SceneNotFoundException {
        if(currentChoice == null) return;

        Log.d("agreeClick", currentChoice.agreeAction);

        SL.Action agreeAction = currentChoice.environment.scene.findAction(currentChoice.agreeAction);
        if(agreeAction != null) {
            Log.d("agreeClick", "exec");
            agreeAction.exec(currentChoice.environment);
        }
        currentChoice = null;
    }

    private static void setCurrentScene(SL.Scene scene) {
        if(onSceneChangeListener != null)
            onSceneChangeListener.onSceneChange(quest.currentScene, scene);
        quest.setCurrentScene(scene);
    }

    public Engine setQuest(SL.Quest quest) {
        Engine.quest = quest;
        return this;
    }

    public void setOnSceneChangeListener(OnSceneChangeListener onSceneChangeListener) {
        Engine.onSceneChangeListener = onSceneChangeListener;
    }

    public void setOnPhraseChangeListener(OnPhraseChangeListener onDialogChangeListener) {
        Engine.onPhraseChangeListener = onDialogChangeListener;
    }

    public static void setOnChoiceChangeListener(OnChoiceChangeListener onChoiceChangeListener) {
        Engine.onChoiceChangeListener = onChoiceChangeListener;
    }
}