package com.thewhite.utilitystorage.api.rating;

import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.model.rating.NumberPoints;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.repository.RatingRepository;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebClient
@ExtendWith(SoftAssertionsExtension.class)
class RatingControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    UtilityStorageRepository utilityStorageRepository;

    UUID id = UUID.fromString("36b63a92-8dec-4fc6-b4d1-bf4d3d698f0f");
    UUID utilityId = UUID.fromString("73bd61ff-d38b-4132-89d5-676754e9baca");

    final Map<UUID, Rating> ratingMap = new HashMap<>();
    final Map<UUID, UtilityStorage> utilityStorageMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        ratingMap.put(
                id, Rating.builder()
                        .id(id)
                        .utilityId(utilityId)
                        .point(NumberPoints.ONE)
                        .build()
        );

        ReflectionTestUtils.setField(ratingRepository, "ratingMap", ratingMap);

        utilityStorageMap.put(
                utilityId, UtilityStorage.builder()
                        .id(utilityId)
                        .name("bloc")
                        .description("utilityStorage которому будет присваиваться рейтинг")
                        .link("https://bloclibrary.dev/#/")
                        .build()
        );

        ReflectionTestUtils.setField(utilityStorageRepository, "utilityStorageMap", utilityStorageMap);
    }

    @Test
    void add(SoftAssertions assertions) {
        //Arrange
        AddRatingDto dto = AddRatingDto.builder()
                .utilityStorageId(utilityId)
                .point(NumberPoints.THREE)
                .build();

        //Act
        RatingDto response = webTestClient.post()
                .uri("ratings/add")
                .contentType(APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(RatingDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        RatingDto expected = RatingDto.builder()
                .id(response.getId())
                .utilityId(utilityId)
                .point(NumberPoints.THREE)
                .build();

        assertions.assertThat(response).isEqualTo(expected);
    }

    @Test
    void delete() {
        //Act
        webTestClient
                .delete()
                .uri("ratings/delete/" + id)
                .exchange()
                //Assert
                .expectStatus()
                .isOk();
    }

}