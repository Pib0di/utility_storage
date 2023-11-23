package com.thewhite.utilitystorage.api.utilitystorage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО для обновления записи")
public class UpdateUtilityDto {
    UUID id;
    String name;
    String description;
    Set<String> link;
}
