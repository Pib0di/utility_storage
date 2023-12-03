package com.thewhite.utilitystorage.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.util.ResourceUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.UUID;

@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
class RatingRepositoryIT {

    private RatingRepository repository;

    @Test
    @DataSet("src/test/resources/dataTest/repository/rating/rating_list.json")
    void deleteAllByUtilityId(SoftAssertions softly) {
        // Arrange
        UUID utilityStorageId = UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c921");
        List<Rating> expected = ResourceUtils.parseJson("src/test/resources/dataTest/repository/rating/rating_list.json", new TypeReference<>() {});
        List<Rating> actual = expected;
        actual.remove(0);
        actual.remove(1);

        // Act
        repository.deleteAllByUtilityId(utilityStorageId);

        // Assert
        softly.assertThat(actual)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expected);
    }
}