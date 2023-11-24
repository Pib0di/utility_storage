package com.thewhite.utilitystorage.model.rating;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    @NotNull(message = "не указан id оценки")
    UUID id;

    @NotNull(message = "не указан id записи")
    UUID utilityId;

    @NotNull(message = "не указан комментарий")
    NumberPoints point;

    @NotNull(message = "не указано описание")
    String description;
}