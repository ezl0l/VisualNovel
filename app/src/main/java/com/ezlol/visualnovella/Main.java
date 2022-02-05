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
//                "\t\t\t\t\t\t{\n" +
//                "\t\t\t\t\t\t\t\"name\": \"load_image\",\n" +
//                "\t\t\t\t\t\t\t\"params\": [\"background\", \"/images/back.png\"]\n" +
//                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"name\": \"add_dialog\",\n" +
                "\t\t\t\t\t\t\t\"params\": [\"0\", \"dialog\"]\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"name\": \"dialog\",\n" +
                "\t\t\t\t\t\"commands\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"name\": \"add_dialog\",\n" +
                "\t\t\t\t\t\t\t\"params\": [1, \"choice\"]\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"name\": \"choice\",\n" +
                "\t\t\t\t\t\"commands\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"name\": \"choice\",\n" +
                "\t\t\t\t\t\t\t\"params\": [\"agree title\", \"decline title\", \"agree\", \"decline\"]\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"name\": \"agree\",\n" +
                "\t\t\t\t\t\"commands\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"name\": \"add_dialog\",\n" +
                "\t\t\t\t\t\t\t\"params\": [0]\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"dialogs\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 0,\n" +
                "\t\t\t\"phrases\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"Name1\",\n" +
                "\t\t\t\t\t\"content\": \"Shalom!!!!\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"Name2\",\n" +
                "\t\t\t\t\t\"content\": \"Shalom too!!!\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"Name1\",\n" +
                "\t\t\t\t\t\"content\": \"HAW?\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"Name2\",\n" +
                "\t\t\t\t\t\"content\": \"ZBS, and u?\"\n" +
                "\t\t\t\t},{\n" +
                "\t\t\t\t\t\"author\": \"Name1\",\n" +
                "\t\t\t\t\t\"content\": \"Shalom!!!!\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"Name2\",\n" +
                "\t\t\t\t\t\"content\": \"Shalom too!!!\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"Name1\",\n" +
                "\t\t\t\t\t\"content\": \"HAW?\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"Name2\",\n" +
                "\t\t\t\t\t\"content\": \"ZBS, and u?\"\n" +
                "\t\t\t\t}" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 1,\n" +
                "\t\t\t\"phrases\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"DEVOPЁS\",\n" +
                "\t\t\t\t\t\"content\": \"it's callback dialog!\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"author\": \"JUNIOR\",\n" +
                "\t\t\t\t\t\"content\": \"it's truth!\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }
}
