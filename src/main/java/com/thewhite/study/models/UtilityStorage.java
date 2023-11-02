package com.thewhite.study.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilityStorage{
    int id;
    @NotNull String name;
    @NotNull String description;
    @NotNull String link;
}
