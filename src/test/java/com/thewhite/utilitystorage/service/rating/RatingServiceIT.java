package com.thewhite.utilitystorage.service.rating;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.thewhite.utilitystorage.model.rating.NumberPoints;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.service.rating.argument.AddRatingArgument;
import com.thewhite.utilitystorage.service.rating.argument.SearchRatingArgument;
import com.thewhite.utilitystorage.util.ResourceUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
class RatingServiceIT {

    @Autowired
    RatingService service;

    @Test
    @DataSet("dataTest/service/rating/actual_rating_list.json")
    void add(SoftAssertions softly) {
        // Arrange
        UtilityStorage utilityStorage = UtilityStorage.builder()
                .description("")
                .id(UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c921"))
                .link(new HashSet<>(Arrays.asList("элемент1", "элемент2", "элемент3")))
                .name("name")
                .build();

        Rating expected = Rating.builder()
                .description("description")
                .point(NumberPoints.FOUR)
                .utilityStorage(utilityStorage)
                .build();
        AddRatingArgument addRatingArgument = AddRatingArgument.builder()
                .utilityStorageId(expected.getUtilityStorage().getId())
                .description(expected.getDescription())
                .point(expected.getPoint())
                .build();

        // Act
        Rating actual = service.add(addRatingArgument);

        // Assert
        softly.assertThat(actual)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("id")
                .isEqualTo(expected);
    }

    @Test
    @DataSet("dataTest/service/rating/actual_rating_list.json")
    void getList(SoftAssertions softly) {
        // Arrange
        UUID utilityStorageId = UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c921");
        NumberPoints point = NumberPoints.FOUR;
        String sortType = "desc";
        Pageable pageable = PageRequest.of(0, 5);

        List<Rating> expected = ResourceUtils.parseJson("dataTest/service/rating/actual_rating_list.json", new TypeReference<>() {
        });

        SearchRatingArgument searchRatingArgument = SearchRatingArgument.builder()
                .point(NumberPoints.EXCELLENTLY).build();

        // Act
        List<Rating> actual = service.getList(utilityStorageId, searchRatingArgument, pageable);

        // Assert
        softly.assertThat(actual)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expected);
    }
}