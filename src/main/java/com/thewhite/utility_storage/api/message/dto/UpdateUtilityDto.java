package com.thewhite.utility_storage.api.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО для обновления записи")
public class UpdateUtilityDto {
    UUID uuid;
    String name;
    String description;
    String link;
}
