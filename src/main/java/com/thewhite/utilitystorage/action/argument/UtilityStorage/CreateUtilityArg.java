package com.thewhite.utilitystorage.action.argument.UtilityStorage;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUtilityArg {
    String name;
    String description;
    String link;
}
