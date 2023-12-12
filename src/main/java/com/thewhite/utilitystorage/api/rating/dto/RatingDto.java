package com.thewhite.utilitystorage.api.rating.dto;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
@Schema(description = "ДТО рейтинга со всеми существующими полями")
public class RatingDto {
    @NotNull(message = "не указан идентификатор рейтига")
    UUID id;

    @NotNull(message = "не указан идентификатор харанилища")
    UtilityStorage utilityStorage;

    @NotNull(message = "не указан рейтинг")
    NumberPoints point;

    @NotNull(message = "не указано описание")
    String description;
}
