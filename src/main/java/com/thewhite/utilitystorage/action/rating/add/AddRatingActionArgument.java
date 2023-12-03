package com.thewhite.utilitystorage.action.rating.add;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
public class AddRatingActionArgument {

    @NotNull(message = "не указан идентификатор харанилища")
    UUID utilityStorageId;

    @NotNull(message = "не указан рейтинг")
    NumberPoints point;

    @NotNull(message = "не указано описание")
    String description;
}
