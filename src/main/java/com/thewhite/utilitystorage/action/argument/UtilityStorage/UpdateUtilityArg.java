package com.thewhite.utilitystorage.action.argument.UtilityStorage;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class UpdateUtilityArg {
    UUID id;
    String name;
    String description;
    String link;
}
