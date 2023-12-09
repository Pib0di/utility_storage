package com.thewhite.utilitystorage.service.rating.argument;

import com.thewhite.utilitystorage.model.rating.NumberPoints;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchRatingArgument {
    NumberPoints point;
}
