package com.thewhite.study;

import com.google.gson.Gson;
import com.thewhite.study.models.UtilityStorage;
import com.thewhite.study.servicess.UtilityStorageManager;
import com.thewhite.study.servicess.UserInteraction;

import java.awt.dnd.DragSourceMotionListener;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        var echo = new Echo();
        System.out.println(echo.echo("Hello world!"));

        UtilityStorageManager utilityStorageManager = new UtilityStorageManager("src/main/resources/utilities_list.json");
        UserInteraction userInteraction = new UserInteraction(utilityStorageManager);
    }
}