package com.thewhite.utilitystorage.action.argument.rating;

import com.thewhite.utilitystorage.common.variable.NumberPoints;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class AddRatingArg {
    UUID utilityId;
    NumberPoints point;
}
