package com.thewhite.utilitystorage.service.rating.argument;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AddRatingArgument {

    UUID utilityStorageId;


    NumberPoints point;
}
