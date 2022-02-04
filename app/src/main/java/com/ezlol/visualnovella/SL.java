package com.ezlol.visualnovella;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SL {

    public static class Quest {
        protected Scene[] scenes;
        public Dialog[] dialogs;
        protected Scene currentScene;

        public Quest(Scene[] scenes) {
            this.scenes = scenes;
        }

        public Quest(Scene[] scenes, Dialog[] dialogs) {
            this.scenes = scenes;
            this.dialogs = dialogs;
        }

        public Dialog findDialog(int id) {
            Dialog d = null;
            for (Dialog dialog :
                    dialogs) {
                if(dialog.id == id)
                    d = dialog;
            }
            return d;
        }

        public Scene getCurrentScene() {
            return currentScene;
        }

        public Quest setCurrentScene(Scene currentScene) {
            this.currentScene = currentScene;
            return this;
        }
    }

    public static abstract class Token {
        protected String name;

        public Token(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Token{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class Scene extends Token {
        protected Action[] actions;

        public Scene(String name) {
            super(name);
        }

        public void init(Environment environment) throws UnknownCommandException, IOException, SceneNotFoundException {
            for (Action action :
                    actions) {
                if (action.name.equals("init"))
                    action.exec(environment);
            }
        }

        public Scene(String name, Action[] actions) {
            super(name);
            this.actions = actions;
        }

        public Action findAction(String name) {
            Action a = null;
            for (Action action :
                    actions) {
                if (action.name.equals(name)) {
                    a = action;
                }
            }
            return a;
        }

        public String[] getPossibleOutcomes() {
            List<String> outcomes = new ArrayList<>();
            for (Action action :
                    actions) {
                for (Command command :
                        action.commands) {
                    if(command.name.equals("goto")) {
                        outcomes.add(command.params[0]);
                    }
                }
            }
            return outcomes.toArray(new String[]{});
        }

        public Action[] getActions() {
            return actions;
        }

        @Override
        public String toString() {
            return "Scene{" +
                    "name='" + name + '\'' +
                    ", actions=" + Arrays.toString(actions) +
                    '}';
        }
    }

    static class Action extends Token {
        protected Command[] commands;

        public Action(String name) {
            super(name);
        }

        public void exec(Environment environment) throws UnknownCommandException, IOException, SceneNotFoundException {
            for (Command command :
                    commands) {
                command.exec(environment);
            }
        }

        public Action(String name, Command[] commands) {
            super(name);
            this.commands = commands;
        }

        public Command[] getCommands() {
            return commands;
        }

        @Override
        public String toString() {
            return "Action{" +
                    "name='" + name + '\'' +
                    ", commands=" + Arrays.toString(commands) +
                    '}';
        }
    }

    static class Command extends Token {
        protected String[] params;

        public Command(String name) {
            super(name);
        }

        public void exec(Environment environment) throws UnknownCommandException, IOException, SceneNotFoundException {
            Engine.execCommand(this, environment);
        }

        public Command(String name, String[] params) {
            super(name);
            this.params = params;
        }

        public String[] getParams() {
            return params;
        }

        @Override
        public String toString() {
            return "Command{" +
                    "name='" + name + '\'' +
                    ", params=" + Arrays.toString(params) +
                    '}';
        }
    }

    public static Engine parse(String data) {
        return new Engine().setQuest(new Gson().fromJson(data, Quest.class));
    }
}
