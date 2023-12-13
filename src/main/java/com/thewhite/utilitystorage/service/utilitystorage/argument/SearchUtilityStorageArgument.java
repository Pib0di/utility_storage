package com.thewhite.utilitystorage.service.utilitystorage.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchUtilityStorageArgument {
    String name;
    String description;
}
