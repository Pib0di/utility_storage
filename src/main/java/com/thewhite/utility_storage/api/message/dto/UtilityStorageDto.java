package com.thewhite.utility_storage.api.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО со всеми существующими полями")
public class UtilityStorageDto {
    UUID uuid;
    String name;
    String description;
    String link;
}
