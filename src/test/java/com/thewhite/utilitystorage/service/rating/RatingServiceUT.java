//package com.thewhite.utilitystorage.service.rating;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.thewhite.utilitystorage.model.rating.Rating;
//import com.thewhite.utilitystorage.repository.RatingRepository;
//import com.thewhite.utilitystorage.service.rating.argument.AddRatingArgument;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.lenient;
//
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//class RatingServiceUT {
//
//    @Spy
//    RatingRepository repository;
//
//    @Autowired
//    RatingService service;
//
//    List<Rating> expectedRatingList = new ArrayList<>();
//    List<AddRatingArgument> AddRatingArgumentList = new ArrayList<>();
//
//    @BeforeEach
//    void setUp() throws IOException {
//        FileReader fileReader = new FileReader("src/test/resources/rating_list.json");
//        expectedRatingList = new ObjectMapper().readValue(fileReader, new TypeReference<>() {});
//
//        repository = Mockito.mock(RatingRepository.class);
////        service = new RatingService(ratingRepository, repository);
//
//        Map<UUID, Rating> ratingMap;
//        ratingMap = new HashMap<>();
//
//        for (Rating rating : expectedRatingList) {
//            ratingMap.put(rating.getId(), rating);
//
//            AddRatingArgumentList.add(AddRatingArgument.builder()
//                    .utilityStorageId(rating.getUtilityStorageId())
//                    .point(rating.getPoint())
//                    .build());
//        }
//
//        lenient().when(repository.save(any(Rating.class))).thenAnswer(invocation -> {
//            Rating arg = invocation.getArgument(0);
//            ratingMap.put(arg.getId(), arg);
//            return arg;
//        });
//
//    }
//
//    @Nested
//    class add {
//        @Test
//        void add_rating() {
//            // Arrange
//            Rating expected = expectedRatingList.get(0);
//
//            // Act
//            Rating actual = service.add(AddRatingArgumentList.get(0));
//            expected.setId(actual.getId());
//
//            // Assert
//            assertEquals(expected, actual);
//        }
//
//        @Test
//        void adding_without_parameters() {
//            // Arrange
//            AddRatingArgument argument = AddRatingArgument.builder().build();
//            Rating expected = Rating.builder().build();
//
//            // Act
//            Rating actual = service.add(argument);
//            expected.setId(actual.getId());
//
//            // Assert
//            assertEquals(expected, actual);
//        }
//
//    }
//
//
//    @Test
//    void delete() {
//        // Arrange
//        UUID id = UUID.fromString("724aebb0-251f-447b-a293-bee75c676ecc");
//
//        // Act
//        service.delete(id);
//
//        // Assert
//
////        Mockito.verify(repository).delete(id);
//    }
//
//    @Nested
//    class getList {
//        @Test
//        void get_list() {
//            // Arrange
//            List<Rating> expectedList = new ArrayList<>();
//            expectedList.add(expectedRatingList.get(0));
//            expectedList.add(expectedRatingList.get(1));
//
//            // Act
////            List<Rating> actual = service.getList(expectedRatingList.get(1).getUtilityStorageId());
//
//            // Assert
////            assertEquals(expectedList, actual);
////            Assertions.assertTrue(expectedList.containsAll(actual));
//        }
//
//        @Test
//        void get_nonexistent_rating() {
//            // Act
////            List<Rating> actual = service.getList(UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c920"));
//
//            // Assert
////            assertEquals(new ArrayList<>(), actual);
//        }
//    }
//}