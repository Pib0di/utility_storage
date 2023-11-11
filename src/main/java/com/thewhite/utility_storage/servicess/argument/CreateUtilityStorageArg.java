package com.thewhite.utility_storage.servicess.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUtilityStorageArg {
    String name;
    String description;
    String link;
}
