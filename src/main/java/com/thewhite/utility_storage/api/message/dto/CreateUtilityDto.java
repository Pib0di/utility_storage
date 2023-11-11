package com.thewhite.utility_storage.api.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Value
@Builder
@Schema(description = "ДТО для создания записи")
public class CreateUtilityDto {
    String name;
    String description;
    String link;
}
