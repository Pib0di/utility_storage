package com.thewhite.utilitystorage.service.utilitystorage.argument;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateUtilityArgument {
    @NotBlank(message = "не указано название")
    String name;

    @NotBlank(message = "не указано описание")
    String description;

    @NotBlank(message = "не указана ссылка")
    String link;
}
