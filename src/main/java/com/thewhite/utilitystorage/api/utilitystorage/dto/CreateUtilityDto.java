package com.thewhite.utilitystorage.api.utilitystorage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Value
@Builder
@Schema(description = "ДТО для создания записи")
public class CreateUtilityDto {
    @NotNull(message = "не указано наименование")
    String name;

    @NotNull(message = "не указано описание")
    String description;

    @NotNull(message = "не указаны ссылки")
    String link;
}
