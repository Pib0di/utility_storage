package com.thewhite.study.servicess;

import com.thewhite.study.models.UtilityStorage;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    public UserInteraction(UtilityStorageManager utilityStorageManager) {
        this.utilityStorageManager = utilityStorageManager;
        actionsLoop();
    }

    void actionsLoop() {
        while (true) {
            displayMainMenu();
            choose();
        }
    }

    void displayMainMenu() {
        System.out.println("1 - вывести на экран запись");
        System.out.println("2 - найти записи по части наименования без учёта регистра");
        System.out.println("3 - завершить");
        System.out.print("Выберите пункт меню: ");
    }

    void choose() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        switch (Integer.parseInt(userInput)) {
            case 1: {
                System.out.println("Введите индекс искомой записи");
                userInput = scanner.nextLine();
                UtilityStorage utilityStorage = utilityStorageManager.getUtility(Integer.parseInt(userInput));
                if (utilityStorage != null){
                    utilityStorageManager.outputAtId(Integer.parseInt(userInput));
                } else {
                    System.out.println("Такой записи не существует!");
                    System.out.println();
                }
                break;
            }
            case 2: {
                System.out.print("Введите часть искомой записи: ");
                userInput = scanner.nextLine();
                List<Integer> indexArray = utilityStorageManager.search(userInput);
                if (indexArray.size() > 0){
                    System.out.println("Нашлось:");
                    indexArray.forEach(element -> {
                        utilityStorageManager.outputAtId(element);
                    });
                } else {
                    choose();
                    System.out.println("Ничего не нашлось! :(");
                }
                break;
            }
            case 3: {
                System.exit(0);
                break;
            }
            default: {
                System.out.print("Введено некорректное значение: ");
            }
        }
    }

    UtilityStorageManager utilityStorageManager;
}