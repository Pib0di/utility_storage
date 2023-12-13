package com.thewhite.utilitystorage.service.utilitystorage.argument;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import java.util.Set;
import java.util.UUID;

@Value
@Builder
public class UpdateUtilityArgument {
    @NotBlank(message = "не указан идентификатор хранилища")
    UUID id;

    @NotBlank(message = "не указано название")
    String name;

    @NotBlank(message = "не указано описание")
    String description;

    @NotBlank(message = "не указана ссылка")
    Set<String> link;
}
