package com.thewhite.utilitystorage.api.utilitystorage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО со всеми существующими полями")
public class UtilityStorageDto {
    @NotNull(message = "не указан идентификатор хранилища")
    UUID id;

    @NotNull(message = "не указано наименование")
    String name;

    @NotNull(message = "не указано описание")
    String description;


    @NotNull(message = "не указаны ссылки")
    @JsonProperty("link")
    Set<String> link;
}
