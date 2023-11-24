package com.thewhite.utilitystorage.model.utilityStorage;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilityStorage {
    UUID id;

    @NonNull String name;

    @NonNull String description;

    @NonNull String link;
}
