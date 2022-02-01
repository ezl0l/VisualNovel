package com.ezlol.visualnovella;

import java.io.IOException;

public class Main {
    public static String g() {
        return "{\n" +
                "\t\"scenes\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"name\": \"dungeon\",\n" +
                "\t\t\t\"actions\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"name\": \"init\",\n" +
                "\t\t\t\t\t\"commands\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"name\": \"if\",\n" +
                "\t\t\t\t\t\t\t\"params\": [\"true\", \"load_image\", \"background\", \"S:\\/marketplace\\/blog\\/source\\/logo.png\"]" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }
}
