package com.thewhite.utilityStorage.api.UtilityStorage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО для обновления записи")
public class UpdateUtilityDto {
    UUID id;
    String name;
    String description;
    String link;
}
