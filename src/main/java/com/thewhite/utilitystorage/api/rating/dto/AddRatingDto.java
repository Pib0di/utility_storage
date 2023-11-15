package com.thewhite.utilitystorage.api.rating.dto;

import com.thewhite.utilitystorage.common.variable.NumberPoints;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО добавления рейтинга к записи")
public class AddRatingDto {
    UUID utilityId;
    NumberPoints point;
}

