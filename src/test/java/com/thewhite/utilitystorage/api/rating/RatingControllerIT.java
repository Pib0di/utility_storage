package com.thewhite.utilitystorage.api.rating;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.api.rating.mapper.RatingMapper;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@EnablePostgresIntegrationTest
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SoftAssertionsExtension.class)
@DBRider
@DBUnit(
        caseSensitiveTableNames = true,
        cacheConnection = false,
        leakHunter = true
//        schema = "public"as
)
class RatingControllerIT {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    UtilityStorageRepository utilityStorageRepository;
    @Autowired
    RatingMapper mapper;

    UUID id = UUID.fromString("724aebb0-251f-447b-a293-bee75c676ecc");
    UUID utilityId = UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c921");
    UtilityStorage utilityStorage;

    @BeforeEach
    void setUp() {
        utilityStorage = UtilityStorage.builder()
                .description("Description1")
                .id(utilityId)
                .link(new HashSet<>(Arrays.asList("link1")))
                .name("name1")
                .build();
    }

    @Test
    @DataSet("dataTest/api/rating/rating_add.json")
    void add(SoftAssertions assertions) {
        //Arrange
        AddRatingDto dto = AddRatingDto.builder()
                .description("desc")
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
        Optional<Rating> rating = ratingRepository.findById(response.getId());
        RatingDto ratingDto = mapper.toDto(rating.get());

        assertions.assertThat(response.getId()).isEqualTo(ratingDto.getId());
        assertions.assertThat(response.getDescription()).isEqualTo(ratingDto.getDescription());
        assertions.assertThat(response.getPoint()).isEqualTo(ratingDto.getPoint());
    }


    @Test
    @DataSet("dataTest/api/rating/rating_delete.json")
    void delete() {
        //Act
        webTestClient
                .delete()
                .uri("ratings/delete/?id=" + id)
                .exchange()
                //Assert
                .expectStatus()
                .isOk();
    }

}