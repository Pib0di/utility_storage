package com.thewhite.utilitystorage.api.utilitystorage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО со всеми существующими полями")
public class UtilityStorageDto {
    UUID id;

    String name;

    String description;

    String link;
}
