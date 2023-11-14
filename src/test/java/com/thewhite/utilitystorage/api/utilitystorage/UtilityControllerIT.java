package com.thewhite.utilitystorage.api.utilitystorage;


import com.thewhite.utilitystorage.api.utilitystorage.dto.CreateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UpdateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UtilityStorageDto;
import com.thewhite.utilitystorage.models.UtilityStorage;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.*;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebClient
@ExtendWith(SoftAssertionsExtension.class)
public class UtilityControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    UtilityStorageRepository repository;

    UUID id = UUID.fromString("36b63a92-8dec-4fc6-b4d1-bf4d3d698f0f");
    final Map<UUID, UtilityStorage> utilityStorageMap = new HashMap<>();

    @BeforeEach
    void setUp() {
        utilityStorageMap.put(
                id, UtilityStorage.builder()
                        .id(id)
                        .name("bloc")
                        .description("стейтменеджмент")
                        .link("https://bloclibrary.dev/#/")
                        .build()
        );

        ReflectionTestUtils.setField(repository, "utilityStorageMap", utilityStorageMap);
    }

    @Test
    void search(SoftAssertions assertions) {
        // Act
        List<UtilityStorageDto> response = webTestClient.get()
                .uri("utility/search/bloc")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(UtilityStorageDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        List<UtilityStorageDto> expectedDtoList = new ArrayList<>();
        expectedDtoList.add(UtilityStorageDto.builder()
                .id(id)
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build());

        assertions.assertThat(response).isEqualTo(expectedDtoList);
    }

    @Test
    void create(SoftAssertions assertions) {
        //Arrange
        CreateUtilityDto dto = CreateUtilityDto.builder()
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();

        //Act
        UtilityStorageDto response = webTestClient.post()
                .uri("utility/create")
                .contentType(APPLICATION_JSON)
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UtilityStorageDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        UtilityStorageDto expected = UtilityStorageDto.builder()
                .id(response.getId())
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();

        assertions.assertThat(response)
                .isEqualTo(expected);

        assertions.assertThat(repository.get(response.getId()))
                .isEqualTo(UtilityStorage.builder()
                        .id(response.getId())
                        .link(response.getLink())
                        .name(response.getName())
                        .description(response.getDescription())
                        .build()
                );
    }

    @Test
    void update(SoftAssertions assertions) {
        //Arrange
        UpdateUtilityDto dto = UpdateUtilityDto.builder()
                .id(id)
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();

        UtilityStorageDto expected = UtilityStorageDto.builder()
                .id(id)
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();

        //Act
        UtilityStorageDto response = webTestClient.put()
                .uri("utility/update")
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
    void deleteUtility() {
        //Act
        webTestClient
                .delete()
                .uri("utility/delete/" + id)
                .exchange()
                //Assert
                .expectStatus()
                .isOk();

    }

    @Test
    void getUtility(SoftAssertions assertions) {
        //Act
        UtilityStorageDto response = webTestClient.get()
                .uri("utility/get/" + id)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UtilityStorageDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        UtilityStorageDto expectedDto = UtilityStorageDto.builder()
                .id(response.getId())
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();

        assertions.assertThat(response)
                .isEqualTo(expectedDto);
    }
}