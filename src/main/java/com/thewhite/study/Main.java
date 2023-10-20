package com.thewhite.study;

import com.thewhite.study.models.UtilityStorage;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        var echo = new Echo();
        System.out.println(echo.echo("Hello world!"));

        Map<Integer, UtilityStorage> utilityStorageMap;
        UtilityStorage utilityStorage = new UtilityStorage("C:\\Users\\2001a\\Downloads\\Telegram Desktop\\result.json");
    }
}