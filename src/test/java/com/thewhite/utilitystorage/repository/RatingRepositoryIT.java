package com.thewhite.utilitystorage.repository;

import com.github.database.rider.core.api.dataset.DataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
class RatingRepositoryIT {

    @Autowired
    private RatingRepository repository;

    @Test
    @DataSet("dataTest/repository/rating_list.json")
    void deleteAllByUtilityId(SoftAssertions softly) {
        // Arrange
//        UUID utilityStorageId = UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c921");
//        List<Rating> expected = ResourceUtils.parseJson("dataTest/repository/rating_list.json", new TypeReference<>() {});
//        List<Rating> actual = expected;
//        actual.remove(0);
//        actual.remove(1);
//
//        UtilityStorage utilityStorage = UtilityStorage.builder()
//                .description("")
//                .id(utilityStorageId)
//                .link(new HashSet<>(Arrays.asList("элемент1", "элемент2", "элемент3")))
//                .name("name")
//                .build();
//
//        // Act
//        repository.deleteAllByUtilityId(utilityStorage);
//
//        // Assert
//        softly.assertThat(actual)
//                .usingRecursiveComparison()
//                .withStrictTypeChecking()
//                .isEqualTo(expected);
    }
}