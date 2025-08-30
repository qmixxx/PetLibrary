package io.reflectoring.library.utils;

import io.qameta.allure.Allure;

public class AllureUtils {
    public static void attachText(String name, String content) {
        Allure.addAttachment(name, "text/plain", content, ".txt");
    }

    public static void attachJson(String name, String json) {
        Allure.addAttachment(name, "application/json", json, ".json");
    }
}

