package com.thewhite.utility_storage.api.message;

import com.thewhite.utility_storage.api.message.dto.CreateUtilityDto;
import com.thewhite.utility_storage.api.message.dto.UpdateUtilityDto;
import com.thewhite.utility_storage.api.message.dto.UtilityStorageDto;
import com.thewhite.utility_storage.models.UtilityStorage;
import com.thewhite.utility_storage.repository.UtilityStorageRepository;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;


@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureWebClient
@ExtendWith(SoftAssertionsExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UtilityStorageControllerIT {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    UtilityStorageRepository repository;

    UUID uuid;

    @BeforeEach
    void setUp() {
        CreateUtilityDto dto = CreateUtilityDto.builder()
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();
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

        assert response != null;
        uuid = response.getUuid();
    }

    @AfterEach
    void tearDown() {
        repository.clear();
    }

    @Test
    void search(SoftAssertions assertions) {
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
                .uuid(uuid)
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build());

        assertions.assertThat(response)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedDtoList);
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
                .uuid(response.getUuid())
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();

        assertions.assertThat(response)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expected);

        System.out.println("repository.getUtility(response.getUuid().toString()=>" + repository.getUtility(response.getUuid()));
        assertions.assertThat(repository.getUtility(response.getUuid()))
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(UtilityStorage.builder()
                        .uuid(response.getUuid())
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
                .uuid(uuid)
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();

        UtilityStorageDto expected = UtilityStorageDto.builder()
                .uuid(uuid)
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
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expected);
    }

    @Test
    void deleteUtility() {

        //Act
        webTestClient
                .delete()
                .uri("utility/delete/" + uuid)
                .exchange()
                .expectStatus()
                .isOk();

    }

    @Test
    void getUtility(SoftAssertions assertions) {
        UtilityStorageDto response = webTestClient.get()
                .uri("utility/get/" + uuid)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(UtilityStorageDto.class)
                .returnResult()
                .getResponseBody();

        //Assert
        UtilityStorageDto expectedDto = UtilityStorageDto.builder()
                .uuid(response.getUuid())
                .name("bloc")
                .description("стейтменеджмент")
                .link("https://bloclibrary.dev/#/")
                .build();

        assertions.assertThat(response)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedDto);
    }
}