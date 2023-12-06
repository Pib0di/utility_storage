package com.thewhite.utilitystorage.service.utilitystorage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.util.ResourceUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
@EnablePostgresIntegrationTest
@ExtendWith(SoftAssertionsExtension.class)
@DBRider
class UtilityStorageServiceImplIT {
    UtilityStorageService service;

    @Test
    @DataSet("dataTest/service/utilityStorage/dataSet_search.json")
    void search(SoftAssertions softly) {
        // Arrange
        String typeRequiredField = "name";
        String findStr = "Oracle";
        String sortType = "desc";
        Pageable pageable = PageRequest.of(0, 5);

        List<Rating> expected = ResourceUtils.parseJson("dataTest/service/utilityStorage/search_expected.json",  new TypeReference<>() {});

        // Act
        List<UtilityStorage> actual = service.search(typeRequiredField, pageable);

        // Assert
        softly.assertThat(actual)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expected);
    }

}