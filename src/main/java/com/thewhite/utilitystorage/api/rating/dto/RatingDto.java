package com.thewhite.utilitystorage.api.rating.dto;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО рейтинга со всеми существующими полями")
public class RatingDto {
    @NotNull(message = "не указан идентификатор рейтига")
    UUID id;

    @NotNull(message = "не указан идентификатор харанилища")
    UUID utilityId;

    @NotNull(message = "не указан рейтинг")
    NumberPoints point;

    @NotNull(message = "не указано описание")
    String description;
}
