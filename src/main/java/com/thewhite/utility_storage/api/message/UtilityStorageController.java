package com.thewhite.utility_storage.api.message;

import com.thewhite.utility_storage.api.message.dto.CreateUtilityDto;
import com.thewhite.utility_storage.api.message.dto.UpdateUtilityDto;
import com.thewhite.utility_storage.api.message.dto.UtilityStorageDto;
import com.thewhite.utility_storage.api.message.mapper.UtilityStorageMapper;
import com.thewhite.utility_storage.exception.NotFoundException;
import com.thewhite.utility_storage.models.UtilityStorage;
import com.thewhite.utility_storage.servicess.UtilityStorageService;
import com.thewhite.utility_storage.servicess.argument.CreateUtilityStorageArg;
import com.thewhite.utility_storage.servicess.argument.UpdateUtilityStorageArg;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("utility")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с хранилищем")
public class UtilityStorageController {

    private final UtilityStorageService service;
    private final UtilityStorageMapper mapper;

    @PostMapping("create")
    @Operation(description = "Создать поле")
    public UtilityStorageDto create(@RequestBody CreateUtilityDto dto) throws NotFoundException {
        CreateUtilityStorageArg argument = mapper.toCreateUtility(dto);
        return mapper.toDto(service.createUtility(argument));
    }

    @PutMapping("update")
    @Operation(description = "Обновить поле")
    @ApiResponse(description = "Запись не обновлена", responseCode = "404")
    public UtilityStorageDto update(@RequestBody UpdateUtilityDto dto) {
        UpdateUtilityStorageArg arg = mapper.toUpdateUtility(dto);

        return mapper.toDto(service.updateUtility(arg));
    }

    @DeleteMapping("delete/{uuid}")
    @Operation(description = "Удалить поле")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public UtilityStorageDto deleteUtility(@PathVariable UUID uuid) {
        UtilityStorage utilityStorage = service.deleteUtility(uuid);

        return mapper.toDto(utilityStorage);
    }

    @GetMapping("get/{uuid}")
    @Operation(description = "Получить поле")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public UtilityStorageDto getUtility(@PathVariable UUID uuid) {
        UtilityStorage utilityStorage = service.getUtility(uuid);

        return mapper.toDto(utilityStorage);
    }

    @GetMapping("search/{str}")
    @Operation(description = "Найти поле (без учета регистра)")
    @ApiResponse(description = "Записи не найдены", responseCode = "404")
    public List<UtilityStorageDto> search(@PathVariable String str) {
        List<UtilityStorage> utilityStorageList = service.search(str);

        return mapper.toDtoList(utilityStorageList);
    }
}
