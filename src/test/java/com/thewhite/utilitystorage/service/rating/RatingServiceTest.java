package com.thewhite.utilitystorage.service.rating;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thewhite.utilitystorage.model.rating.NumberPoints;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.repository.RatingRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

class RatingServiceTest {

    private final RatingRepository repository = new RatingRepository();

    List<Rating> expectedRatingList = new ArrayList<>();

    @SneakyThrows
    @BeforeEach
    void setUp() {
        FileReader fileReader = new FileReader("src/test/resources/rating_list.json");
        expectedRatingList = new ObjectMapper().readValue(fileReader, new TypeReference<>() {
        });

        expectedRatingList.replaceAll(repository::add);
    }

    @Nested
    class add {
        @Test
        void add_rating() {
            // Arrange
            Rating expected = Rating.builder()
                    .utilityId(UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c921"))
                    .point(NumberPoints.FOUR)
                    .build();

            // Act
            Rating actual = repository.add(expected);

            expected.setId(expected.getId());

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        void adding_without_parameters() {
            // Arrange
            Rating expected = Rating.builder().build();

            // Act
            Rating actual = repository.add(expected);

            expected.setId(expected.getId());

            // Assert
            assertEquals(expected, actual);
        }

        @Test
        void adding_with_an_erroneous_id() {
            // Arrange
            // Act
            // Assert
            assertThrows(IllegalArgumentException.class, () -> Rating.builder()
                    .utilityId(UUID.fromString("error"))
                    .point(NumberPoints.FOUR)
                    .build());
        }
    }


    @Test
    void delete() {
        // Arrange
        Rating expected = expectedRatingList.get(0);

        // Act
        Rating actual = repository.delete(UUID.fromString("724aebb0-251f-447b-a293-bee75c676ecc"));

        // Assert
        assertEquals(expected, actual);
    }

    @Nested
    class getList {
        @Test
        void get_list() {
            // Arrange
            List<Rating> expectedList = new ArrayList<>();
            expectedList.add(expectedRatingList.get(0));
            expectedList.add(expectedRatingList.get(1));

            // Act
            List<Rating> actual = repository.getList(UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c921"));

            // Assert
            assertEquals(expectedList, actual);
            Assertions.assertTrue(expectedList.containsAll(actual));
        }

        @Test
        void get_nonexistent_rating() {
            // Act
            List<Rating> actual = repository.getList(UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c920"));

            // Assert
            assertEquals(new ArrayList<>(), actual);
        }
    }
}