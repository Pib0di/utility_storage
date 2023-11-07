package com.thewhite.study.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilityStorage{
    int id;
    @NonNull String name;
    @NonNull String description;
    @NonNull String link;
}
