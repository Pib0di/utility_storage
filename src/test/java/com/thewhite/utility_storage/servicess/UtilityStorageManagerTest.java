//package com.thewhite.utility_storage.servicess;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.thewhite.utility_storage.models.UtilityStorage;
//import com.thewhite.utility_storage.repository.UtilityStorageRepository;
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//class UtilityStorageManagerTest {
//    private final UtilityStorageRepository utilityStorageRepository = new UtilityStorageRepository();
//    List<UtilityStorage> expectedUtilityStorageList = new ArrayList<>();
//
//    @SneakyThrows
//    @BeforeEach
//    void setUp() {
//        utilityStorageRepository.readData("src/test/resources/utilities_list.json");
//
//        FileReader fileReader = new FileReader("src/test/resources/utilities_list.json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        expectedUtilityStorageList = objectMapper.readValue(fileReader, new TypeReference<>() {});
//    }
//
//    @Nested
//    class AddUtility {
//
//        @Test
//        void adding_multiple_Utility_via_path() {
//            // Setup
//            //инициализируется черезе указание пути в setUp()
//
//            // Act
//            List<UtilityStorage> actualList = utilityStorageRepository.getUtilityStorageList();
//
//            // Assert
//            assertEquals(expectedUtilityStorageList, actualList);
//        }
//
//        @Test
//        void adding_utility_to_existing_set_utilities() {
//            // Setup
//            UtilityStorage utilityStorage = UtilityStorage.builder()
//                    .uuid(UUID.randomUUID())
//                    .name("Backend in 1 hour")
//                    .description("данные должны записаться под уникальным id")
//                    .link("https://backend.ru/error_data").build();
//
//            // Act
//            utilityStorageRepository.createUtility(utilityStorage);
//
//            // Assert
////            ReflectionUtils.tryToReadFieldValue(UtilityStorageManager.class, "utilityStorageMap", utilityStorageManager);
//            assertEquals(utilityStorageRepository.getUtilityStorageList().get(4), utilityStorage);
//        }
//    }
//
//    @Nested
//    class GetUtility {
//        @Test
//        void get_nonexistent_utility() {
//            // Act
//            UtilityStorage utilityStorage = utilityStorageRepository.getUtility(-1);
//
//            // Assert
//            assertNull(utilityStorage);
//        }
//
//        @Test
//        void getUtility() {
//            // Act
//            UtilityStorage utilityStorage = utilityStorageRepository.getUtility(1);
//
//            // Assert
//            assertEquals(utilityStorage, expectedUtilityStorageList.get(0));
//        }
//    }
//
//    @Nested
//    class Search {
//        @Test
//        void finding_empty_rows() {
//            // Setup
//            List<UtilityStorage> expectedList = new ArrayList<>();
//
//            // Act
//            List<UtilityStorage> listInteger = utilityStorageRepository.search("");
//
//            // Assert
//            assertEquals(listInteger, expectedList);
//        }
//
//        @Test
//        void finding_multiple_rows() {
//            // Setup
//            List<UtilityStorage> expectedList = new ArrayList<>();
//            expectedList.add(expectedUtilityStorageList.get(1));
//            expectedList.add(expectedUtilityStorageList.get(2));
//
//            // Act
//            List<UtilityStorage> actualList = utilityStorageRepository.search("Oracle");
//
//            // Assert
//            Assertions.assertEquals(expectedList, actualList);
//        }
//
//        @Test
//        void finding_rows_with_space() {
//            // Setup
//            List<UtilityStorage> expectedList = new ArrayList<>();
//            expectedList.add(expectedUtilityStorageList.get(0));
//
//            // Act
//            List<UtilityStorage> listInteger = utilityStorageRepository.search(" in 1 hour");
//
//            // Assert
//            assertEquals(listInteger, expectedList);
//        }
//
//        @Test
//        void checking_processing_of_regular_expressions() {
//            // Act
//            List<UtilityStorage> listInteger = utilityStorageRepository.search("*.?{}()\\/.*&&^@");
//
//            // Assert
//            assertEquals(listInteger, new ArrayList<>());
//        }
//
//        @Test
//        void checking_zero_input_value() {
//            // Act
//            List<UtilityStorage> listInteger = utilityStorageRepository.search(null);
//
//            // Assert
//            assertEquals(listInteger, new ArrayList<>());
//        }
//
//
//    }
//}