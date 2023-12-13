package com.thewhite.utilitystorage.api.rating.dto;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;


@Value
@Builder
@Schema(description = "ДТО добавления рейтинга к записи")
public class AddRatingDto {

    @NotNull(message = "не указан идентификатор харанилища")
    UUID utilityStorageId;

    @NotNull(message = "не указан рейтинг")
    NumberPoints point;

    @NotNull(message = "не указано описание")
    String description;
}
