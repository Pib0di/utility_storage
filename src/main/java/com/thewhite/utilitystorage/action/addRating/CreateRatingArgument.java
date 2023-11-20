package com.thewhite.utilitystorage.action.addRating;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreateRatingArgument {

    @NotNull(message = "не указан идентификатор харанилища")
    UUID utilityStorageId;

    @NotNull(message = "не указан рейтинг")
    @Min(value = 0, message = "диапазон рейтинга от 0 до 4")
    @Max(value = 4, message = "диапазон рейтинга от 0 до 4")
    NumberPoints point;
}
