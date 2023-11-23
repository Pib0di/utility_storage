package com.thewhite.utilitystorage.action.rating.add;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreateRatingArgument {

    UUID utilityStorageId;

    NumberPoints point;
}
