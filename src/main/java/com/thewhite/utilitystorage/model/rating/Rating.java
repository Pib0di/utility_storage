package com.thewhite.utilitystorage.model.rating;

import jakarta.validation.constraints.NotBlank;
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

    @NotNull(message = "не указано id записи")
    UUID id;

    @NotBlank(message = "не указан комментарий")
    UUID utilityId;


    NumberPoints point;
}