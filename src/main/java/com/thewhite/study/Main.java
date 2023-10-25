package com.thewhite.study;

import com.thewhite.study.servicess.UtilityStorageManager;
import com.thewhite.study.servicess.UserInteraction;
import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String @NotNull [] args) {
        UtilityStorageManager utilityStorageManager = new UtilityStorageManager(args[0]);
        UserInteraction userInteraction = new UserInteraction(utilityStorageManager);
        userInteraction.actionsLoop();
    }
}