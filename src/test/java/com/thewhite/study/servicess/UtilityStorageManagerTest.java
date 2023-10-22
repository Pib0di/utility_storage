package com.thewhite.study.servicess;

import com.thewhite.study.models.UtilityStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UtilityStorageManagerTest {
    private UtilityStorageManager utilityStorageManager = new UtilityStorageManager("src/test/resources/utilities_list.json");
    UtilityStorage utilityStorage_1;

    @BeforeEach
    void setUp() {
        utilityStorage_1 = new UtilityStorage(
                1,
                "Backend in 1 hour",
                "бэкенд за час, бесплатно и без регистрации ",
                "https://backend.ru/error_data");
    }

    @Nested
    class AddUtility{
        @Test
        void adding_empty_utility() {
            // Setup
            UtilityStorage utilityStorage = new UtilityStorage();

            // Act
            utilityStorageManager.addUtility(0, utilityStorage);

            // Assert
            assertEquals(utilityStorageManager.getUtility(0), utilityStorage);
        }

        @Test
        void adding_multiple_Utility_via_path() {
            // Setup
            UtilityStorageManager utilityStorageManager = new UtilityStorageManager("src/test/resources/utilities_list.json");

            // Assert
            assertEquals(utilityStorage_1.hashCode(), utilityStorageManager.getUtility(0).hashCode());
        }

        @Test
        void adding_utility_with_existing_id() {
            // Setup
            UtilityStorageManager utilityStorageManager = new UtilityStorageManager("src/test/resources/utilities_list.json");

            UtilityStorage utilityStorage = new UtilityStorage(
                    1,
                    "Backend in 1 hour",
                    "данные под выбранным id(0) должны перезаписаться",
                    "https://backend.ru/error_data");

            // Act
            utilityStorageManager.addUtility(0, utilityStorage);

            // Assert
            assertEquals(utilityStorage.hashCode(), utilityStorageManager.getUtility(0).hashCode());
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
            UtilityStorage utilityStorage = utilityStorageManager.getUtility(0);

            // Assert
            assertEquals(utilityStorage.hashCode(), utilityStorage_1.hashCode());
        }
    }

    @Nested
    class Search {
        @Test
        void finding_all_rows() {
            // Setup
            List<Integer> expectedList = List.of(0, 1, 2, 3);

            // Act
            List<Integer> listInteger = utilityStorageManager.search("");

            // Assert
            assertEquals(listInteger.size(), 4);
        }

        @Test
        void finding_multiple_rows() {
            // Setup
            List<Integer> expectedList = List.of(1, 2);

            // Act
            List<Integer> listInteger = utilityStorageManager.search("Oracle");

            // Assert
            assertEquals(listInteger, expectedList);
        }

        @Test
        void finding_rows_with_space() {
            // Setup
            List<Integer> expectedList = List.of(0);

            // Act
            List<Integer> listInteger = utilityStorageManager.search(" in 1 hour");

            // Assert
            assertEquals(listInteger, expectedList);
        }

        @Test
        void checking_processing_of_regular_expressions() {
            // Setup
            List<Integer> expectedList = List.of();

            // Act
            List<Integer> listInteger = utilityStorageManager.search("*.?{}()\\/.*&&^@");

            // Assert
            assertEquals(listInteger, expectedList);
        }

        @Test
        void checking_zero_input_value() {

            // Act
            List<Integer> listInteger = utilityStorageManager.search(null);

            // Assert
            assertEquals(listInteger, new ArrayList<>());
        }
    }
}