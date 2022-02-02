package com.ezlol.visualnovella;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Engine {
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
    public static Map<String, Resource> images = new HashMap<>();
    public static Map<String, Resource> audios = new HashMap<>();

    private static OnSceneChangeListener onSceneChangeListener = null;

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
                images.put(command.params[0], new Resource(environment.PATH + command.params[1],
                        readFile(environment.PATH + command.params[1]),
                        Resource.Type.IMAGE_TYPE));
                break;
            }

            case "load_audio": {
                System.out.println("[INFO] Loading audio from " + environment.PATH + command.params[1] + "...");
                audios.put(command.params[0], new Resource(environment.PATH + command.params[1],
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
                quest.setCurrentScene(searchScene);
                break;
            }

            default:
                throw new UnknownCommandException();
        }
    }

    public void init() throws UnknownCommandException, IOException, SceneNotFoundException {
        for (SL.Scene scene :
                quest.scenes) {
            scene.init(new Environment(""));
        }
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

    public OnSceneChangeListener getOnSceneChangeListener() {
        return onSceneChangeListener;
    }

    public void setOnSceneChangeListener(OnSceneChangeListener onSceneChangeListener) {
        Engine.onSceneChangeListener = onSceneChangeListener;
    }
}