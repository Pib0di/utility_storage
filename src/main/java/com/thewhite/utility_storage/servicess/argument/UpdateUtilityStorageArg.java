package com.thewhite.utility_storage.servicess.argument;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UpdateUtilityStorageArg {
    UUID uuid;
    String name;
    String description;
    String link;
}
