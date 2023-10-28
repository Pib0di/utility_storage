package com.thewhite.study.servicess;

import com.thewhite.study.models.UtilityStorage;

import java.util.List;
import java.util.Scanner;

public class UserInteraction {
    UtilityStorageManager utilityStorageManager;

    public UserInteraction(UtilityStorageManager utilityStorageManager) {
        this.utilityStorageManager = utilityStorageManager;
    }

    public void actionsLoop() {
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
        switch (parseInt(userInput)) {
            case 1: {
                System.out.println("Введите идентификатор искомой записи");
                userInput = scanner.nextLine();
                UtilityStorage utilityStorage = utilityStorageManager.getUtility(parseInt(userInput));
                if (utilityStorage != null) {
                    System.out.println(utilityStorage);
                } else {
                    System.out.println("Такой записи не существует!");
                    System.out.println();
                }
                break;
            }
            case 2: {
                System.out.print("Введите часть искомой записи: ");
                userInput = scanner.nextLine();
                List<UtilityStorage> indexArray = utilityStorageManager.search(userInput);
                if (indexArray.size() > 0) {
                    System.out.println("Нашлось:");
                    indexArray.forEach(element -> {
                        System.out.println(element.toString());
                    });
                } else {
                    System.out.println("Ничего не нашлось! :(");
                }
                break;
            }
            case 3: {
                System.exit(0);
                break;
            }
            default: {
                System.out.print("Введено некорректное значение\n");
            }
        }
    }

    Integer parseInt(String userInput) {
        try {
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            return -1;
        }
    }

}