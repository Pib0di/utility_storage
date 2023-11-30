package com.thewhite.utilitystorage.action.utilityStorage.delete;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DeleteUtilityStorageArgument {
    UUID utilityStorageId;
}
