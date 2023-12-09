package com.thewhite.utilitystorage.api.rating.dto;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@Schema(description = "ДТО для указания фильтрации по полю point")
public class SearchRatingDto {
    @NotNull(message = "не указан рейтинг")
    NumberPoints point;
}
