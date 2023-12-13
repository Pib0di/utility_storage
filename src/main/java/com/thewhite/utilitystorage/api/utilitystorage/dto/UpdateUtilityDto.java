package com.thewhite.utilitystorage.api.utilitystorage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО для обновления записи")
public class UpdateUtilityDto {
    @NotNull(message = "не указан идентификатор хранилища")
    UUID id;

    @NotNull(message = "не указано наименование")
    String name;

    @NotNull(message = "не указано описание")
    String description;

    @NotNull(message = "не указаны ссылки")
    Set<String> link;
}
