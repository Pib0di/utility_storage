package com.thewhite.study.servicess;

import com.thewhite.study.models.UtilityStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UtilityStorageManagerTest {
    private UtilityStorageManager utilityStorageManager = new UtilityStorageManager("src/test/resources/utilities_list.json");
    UtilityStorage utilityStorage_1;
    List<UtilityStorage> utilityStorageList = new ArrayList<>();

    @Mock
    private UtilityStorageManager mockUtilityStorageManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        utilityStorage_1 = new UtilityStorage(1, "Backend in 1 hour", "бэкенд за час, бесплатно и без регистрации ", "https://backend.ru/error_data");

        utilityStorageList.add(new UtilityStorage(1, "Backend in 1 hour", "бэкенд за час, бесплатно и без регистрации ", "https://backend.ru/error_data"));
        utilityStorageList.add(new UtilityStorage(2, "Oracle Backend for Spring Boot", "Oracle Backend ", "https://oracle.github.io/microservices-datadriven/spring"));
        utilityStorageList.add(new UtilityStorage(3, "Oracle Backend for Spring Boot", "", "C:\\Users\\...\\Downloads\\Telegram Desktop\\result.json"));
        utilityStorageList.add(new UtilityStorage(4, "4", "4", "4"));

    }

    @Nested
    class AddUtility {

        @Test
        void adding_multiple_Utility_via_path() {
            // Act
            List<UtilityStorage> actualList = utilityStorageManager.getUtilityStorageList();

            // Assert
            Assertions.assertTrue(utilityStorageList.equals(actualList));
        }

        @Test
        void adding_utility_to_existing_set_utilities() {
            // Setup
            UtilityStorage utilityStorage = new UtilityStorage(4567745, "Backend in 1 hour", "данные под должны записаться под уникальным id", "https://backend.ru/error_data");

//            when(mockUtilityStorageManager.getUtility(-1)).thenReturn(utilityStorage);

            // Act
//            mockUtilityStorageManager.addUtility(utilityStorage);
            utilityStorageManager.addUtility(utilityStorage);

            // Assert
            assertEquals(utilityStorageManager.getUtilityStorageList().get(4), utilityStorageManager.getUtility(5));
        }
    }

    @Nested
    class GetUtility {
        @Test
        void get_nonexistent_utility() {
            // Act
            UtilityStorage utilityStorage = utilityStorageManager.getUtility(-1);

            // Assert
            assertNull(utilityStorage);
        }

        @Test
        void getUtility() {
            // Act
            UtilityStorage utilityStorage = utilityStorageManager.getUtility(1);

            // Assert
            assertEquals(utilityStorage, utilityStorage_1);
        }
    }

    @Nested
    class Search {
        @Test
        void finding_all_rows() {
            // Setup
            List<UtilityStorage> expectedList = new ArrayList<>();

            // Act
            List<UtilityStorage> listInteger = utilityStorageManager.search("");

            // Assert
            assertEquals(listInteger, expectedList);
        }

        @Test
        void finding_multiple_rows() {
            // Setup
            List<UtilityStorage> expectedList = new ArrayList<>();
            expectedList.add(new UtilityStorage(2, "Oracle Backend for Spring Boot", "Oracle Backend ", "https://oracle.github.io/microservices-datadriven/spring"));
            expectedList.add(new UtilityStorage(3, "Oracle Backend for Spring Boot", "", "C:\\Users\\...\\Downloads\\Telegram Desktop\\result.json"));

            // Act
            List<UtilityStorage> actualList = utilityStorageManager.search("Oracle");

            // Assert
            Assertions.assertTrue(expectedList.equals(actualList));
        }

        @Test
        void finding_rows_with_space() {
            // Setup
            List<UtilityStorage> expectedList = new ArrayList<>();
            expectedList.add(new UtilityStorage(1, "Backend in 1 hour", "бэкенд за час, бесплатно и без регистрации ", "https://backend.ru/error_data"));

            // Act
            List<UtilityStorage> listInteger = utilityStorageManager.search(" in 1 hour");

            // Assert
            assertEquals(listInteger, expectedList);
        }

        @Test
        void checking_processing_of_regular_expressions() {
            // Act
            List<UtilityStorage> listInteger = utilityStorageManager.search("*.?{}()\\/.*&&^@");

            // Assert
            assertEquals(listInteger, new ArrayList<>());
        }

        @Test
        void checking_zero_input_value() {
            // Act
            List<UtilityStorage> listInteger = utilityStorageManager.search(null);

            // Assert
            assertEquals(listInteger, new ArrayList<>());
        }


    }
}