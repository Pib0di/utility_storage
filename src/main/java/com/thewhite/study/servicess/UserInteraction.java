package com.thewhite.study.servicess;

import com.thewhite.study.models.UtilityStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class UserInteraction implements CommandLineRunner {
    UtilityStorageManager utilityStorageManager;

    @Autowired
    public UserInteraction(UtilityStorageManager utilityStorageManager) {
        this.utilityStorageManager = utilityStorageManager;
    }
    public UserInteraction( ) {
    }

    @Override
    public void run(String... args) throws Exception {
        actionsLoop();
    }

    public void actionsLoop() {
        while (true) {
            displayMainMenu();
            choose();
        }
    }

    void displayMainMenu() {
        System.out.println("""
                1 - вывести на экран запись
                2 - найти записи по части наименования без учёта регистра
                3 - завершить
                Выберите пункт меню:
                """);
    }

    void choose() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        switch (parseInt(userInput)) {
            case 1 -> enterId(scanner);
            case 2 -> enterEntry(scanner);
            case 3 -> System.exit(0);
            default -> System.out.print("Введено некорректное значение\n");
        }
    }

    void enterEntry(Scanner scanner) {
        System.out.print("Введите часть искомой записи: ");
        String userInput = scanner.nextLine();
        List<UtilityStorage> indexArray = utilityStorageManager.search(userInput);
        if (indexArray.size() > 0) {
            System.out.println("Нашлось:");
            indexArray.forEach(element -> {
                System.out.println(element.toString());
            });
        } else {
            System.out.println("Ничего не нашлось! :(");
        }
    }

    void enterId(Scanner scanner) {
        System.out.println("Введите идентификатор искомой записи");
        String userInput = scanner.nextLine();
        UtilityStorage utilityStorage = utilityStorageManager.getUtility(parseInt(userInput));
        if (utilityStorage != null) {
            System.out.println(utilityStorage);
        } else {
            System.out.println("Такой записи не существует!\n");
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