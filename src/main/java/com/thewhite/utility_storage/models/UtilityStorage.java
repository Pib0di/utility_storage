package com.thewhite.utility_storage.models;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilityStorage {
    UUID uuid;
    @NonNull String name;
    @NonNull String description;
    @NonNull String link;
}
