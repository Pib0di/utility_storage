package com.thewhite.utilitystorage.service.utilitystorage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UtilityStorageServiceTest {
    private final UtilityStorageRepository utilityStorageRepository = new UtilityStorageRepository();
    private final UtilityStorageServiceImpl utilityStorageService = new UtilityStorageServiceImpl(utilityStorageRepository);

    List<UtilityStorage> expectedUtilityStorageList = new ArrayList<>();

    @SneakyThrows
    @BeforeEach
    void setUp() {

        FileReader fileReader = new FileReader("src/test/resources/utilities_list.json");
        expectedUtilityStorageList = new ObjectMapper().readValue(fileReader, new TypeReference<>() {
        });


        expectedUtilityStorageList.replaceAll(utilityStorage -> utilityStorageService.create(
                CreateUtilityArgument.builder()
                        .description(utilityStorage.getDescription())
                        .link(utilityStorage.getLink())
                        .name(utilityStorage.getName())
                        .build()
        ));
    }

    @Nested
    class create {

        @Test
        void adding_utility_to_existing_set_utilities() {
            // Arrange
            UtilityStorage utilityStorage = UtilityStorage.builder()
                    .name("Backend in 1 hour")
                    .description("данные должны записаться под уникальным id")
                    .link("https://backend.ru/error_data").build();

            // Act
            UUID id = utilityStorageService.create(
                    CreateUtilityArgument.builder()
                            .description(utilityStorage.getDescription())
                            .link(utilityStorage.getLink())
                            .name(utilityStorage.getName())
                            .build()
            ).getId();

            utilityStorage.setId(id);

            // Assert
            assertEquals(utilityStorage, utilityStorageService.get(id));
        }
    }

    @Nested
    class get {

        @Test
        void get_nonexistent_utility() {
            // Act
            UtilityStorage utilityStorage = utilityStorageService.get(UUID.fromString("36b63a92-8dec-4fc6-b4d1-bf4d3d698f0f"));

            // Assert
            assertNull(utilityStorage);
        }

        @Test
        void getUtility() {
            // Act
            UtilityStorage utilityStorage = utilityStorageService.get(expectedUtilityStorageList.get(0).getId());

            // Assert
            assertEquals(utilityStorage, expectedUtilityStorageList.get(0));
        }
    }


    @Nested
    class search {
        @Test
        void finding_empty_rows() {
            // Arrange
            List<UtilityStorage> expectedList = new ArrayList<>();

            // Act
            List<UtilityStorage> listInteger = utilityStorageService.search("");

            // Assert
            assertEquals(listInteger, expectedList);
        }

        @Test
        void finding_multiple_rows() {
            // Arrange
            List<UtilityStorage> expectedList = new ArrayList<>();
            expectedList.add(expectedUtilityStorageList.get(2));
            expectedList.add(expectedUtilityStorageList.get(1));

            // Act
            List<UtilityStorage> actualList = utilityStorageService.search("Oracle");

            // Assert
//            Assertions.assertEquals(expectedList, actualList);
            Assertions.assertTrue(actualList.containsAll(expectedList));
        }

        @Test
        void finding_rows_with_space() {
            // Arrange
            List<UtilityStorage> expectedList = new ArrayList<>();
            expectedList.add(expectedUtilityStorageList.get(0));

            // Act
            List<UtilityStorage> listInteger = utilityStorageService.search(" in 1 hour");

            // Assert
            assertEquals(listInteger, expectedList);
        }

        @Test
        void checking_processing_of_regular_expressions() {
            // Act
            List<UtilityStorage> listInteger = utilityStorageService.search("*.?{}()\\/.*&&^@");

            // Assert
            assertEquals(listInteger, new ArrayList<>());
        }

        @Test
        void checking_zero_input_value() {
            // Act
            List<UtilityStorage> listInteger = utilityStorageService.search(null);

            // Assert
            assertEquals(listInteger, new ArrayList<>());
        }
    }
}


