package com.thewhite.utilitystorage.api.utilitystorage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
@Schema(description = "ДТО для создания записи")
public class CreateUtilityDto {
    String name;
    String description;
    Set<String> link;
}
