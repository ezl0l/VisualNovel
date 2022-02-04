package com.ezlol.visualnovella;

public class Environment {
    public String PATH;
    public SL.Scene scene;

    public Environment() {
    }

    public Environment(String PATH) {
        this.PATH = PATH;
    }

    public Environment(String PATH, SL.Scene scene) {
        this.PATH = PATH;
        this.scene = scene;
    }
}
