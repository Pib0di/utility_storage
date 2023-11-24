package com.thewhite.utilitystorage.api.utilitystorage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    String link;
}
