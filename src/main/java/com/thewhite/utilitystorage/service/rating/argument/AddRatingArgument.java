package com.thewhite.utilitystorage.service.rating.argument;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
public class AddRatingArgument {

    @NotNull(message = "не указан идентификатор харанилища")
    UUID utilityStorageId;

    @NotNull(message = "не указан рейтинг")
    NumberPoints point;

    @NotNull(message = "не указано описание")
    String description;
}
