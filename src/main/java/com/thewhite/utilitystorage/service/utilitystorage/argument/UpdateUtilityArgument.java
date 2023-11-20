package com.thewhite.utilitystorage.service.utilitystorage.argument;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UpdateUtilityArgument {
    UUID id;
    String name;
    String description;
    String link;
}
