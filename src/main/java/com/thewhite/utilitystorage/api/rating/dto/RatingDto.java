package com.thewhite.utilitystorage.api.rating.dto;

import com.thewhite.utilitystorage.common.variable.NumberPoints;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО рейтинга со всеми существующими полями")
public class RatingDto {
    UUID id;
    UUID utilityId;
    NumberPoints point;
}
