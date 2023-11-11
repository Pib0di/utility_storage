package com.thewhite.utility_storage.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
@Schema(description = "ДТО ошибки")
public class ErrorDto {
    String errorMessage;
}
