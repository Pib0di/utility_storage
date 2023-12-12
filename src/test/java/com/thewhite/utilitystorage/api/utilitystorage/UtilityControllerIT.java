package com.thewhite.utilitystorage.api.utilitystorage;


import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import com.thewhite.utilitystorage.api.utilitystorage.dto.CreateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UpdateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UtilityStorageDto;
import com.thewhite.utilitystorage.api.utilitystorage.mapper.UtilityMapper;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import com.thewhite.utilitystorage.util.ResourceUtils;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        leakHunter = true,
        schema = "public"
)
public class UtilityControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    UtilityStorageRepository repository;
    @Autowired
    UtilityMapper mapper;


    UUID id = UUID.fromString("a9147542-4860-4c67-9d2a-f506c2b7c921");
    Set<String> setLink = new HashSet<>();

    @BeforeEach
    void setUp() {
        setLink.add("https://bloclibrary.dev/#/");
    }

    @Test
    void create(SoftAssertions assertions) {
        //Arrange
        CreateUtilityDto dto = CreateUtilityDto.builder()
                .name("bloc")
                .description("стейтменеджмент")
                .link(setLink)
                .build();

        //Act
        UtilityStorageDto actual = webTestClient.post()
                .uri("utility-storages/create")
                .contentType(APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UtilityStorageDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        UtilityStorage expected = repository.findById(actual.getId()).get();

        assertions.assertThat(expected.getId()).isEqualTo(actual.getId());
        assertions.assertThat(expected.getName()).isEqualTo(actual.getName());
        assertions.assertThat(expected.getDescription()).isEqualTo(actual.getDescription());
    }

    @Test
    @DataSet("dataTest/api/utilityStorage/utilityStorage_update.json")
    void update(SoftAssertions assertions) {
        //Arrange
        UpdateUtilityDto dto = UpdateUtilityDto.builder()
                .id(id)
                .name("bloc")
                .description("стейтменеджмент")
                .link(setLink)
                .build();

        UtilityStorageDto expected = UtilityStorageDto.builder()
                .id(id)
                .name("bloc")
                .description("стейтменеджмент")
                .link(setLink)
                .build();


        //Act
        UtilityStorageDto response = webTestClient.put()
                .uri("utility-storages/update")
                .contentType(APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UtilityStorageDto.class)
                .returnResult()
                .getResponseBody();


        //Assert
        assertions.assertThat(response)
                .isEqualTo(expected);
    }

    @Test
    @DataSet("dataTest/api/utilityStorage/utilityStorage_update.json")
    void deleteUtility(SoftAssertions assertions) {
        //Act
        webTestClient
                .delete()
                .uri("utility-storages/delete/" + id)
                .exchange()
                //Assert
                .expectStatus()
                .isOk();

        assertions.assertThat(repository.findById(id).isEmpty()).isEqualTo(true);
    }

    @Test
    @DataSet("dataTest/api/utilityStorage/utilityStorage_update.json")
    void getUtility(SoftAssertions assertions) {
        //Act
        UtilityStorageDto response = webTestClient.get()
                .uri("utility-storages/get/" + id)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UtilityStorageDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        setLink.clear();
        setLink.add("link1");
        UtilityStorageDto expectedDto = UtilityStorageDto.builder()
                .id(response.getId())
                .name("name1")
                .description("Description1")
                .link(setLink)
                .build();

        assertions.assertThat(response)
                .isEqualTo(expectedDto);
    }

    @Test
    @DataSet("dataTest/api/utilityStorage/utilityStorage_update.json")
    void search(SoftAssertions assertions) {
        //Arrange
        List<UtilityStorage> expected = ResourceUtils.parseJson("dataTest/api/utilityStorage/utilityStorage_search__expected.json", new TypeReference<>() {});

        // Act
        List<UtilityStorageDto> response = webTestClient.get()
                .uri("utility-storages/search")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(UtilityStorageDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        assertions.assertThat(response).containsExactlyInAnyOrderElementsOf(mapper.toDtoList(expected));
    }
}