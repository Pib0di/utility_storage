package com.thewhite.utilityStorage.api.UtilityStorage;

import com.thewhite.utilityStorage.api.UtilityStorage.dto.CreateUtilityDto;
import com.thewhite.utilityStorage.api.UtilityStorage.dto.UpdateUtilityDto;
import com.thewhite.utilityStorage.api.UtilityStorage.dto.UtilityStorageDto;
import com.thewhite.utilityStorage.api.UtilityStorage.mapper.UtilityMapper;
import com.thewhite.utilityStorage.exception.NotFoundException;
import com.thewhite.utilityStorage.models.UtilityStorage;
import com.thewhite.utilityStorage.servicess.UtilityStorage.UtilityStorageService;
import com.thewhite.utilityStorage.action.argument.UtilityStorage.CreateUtilityArg;
import com.thewhite.utilityStorage.action.argument.UtilityStorage.UpdateUtilityArg;
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
public class UtilityController {

    private final UtilityStorageService service;
    private final UtilityMapper mapper;

    @PostMapping("create")
    @Operation(description = "Создать поле")
    public UtilityStorageDto create(@RequestBody CreateUtilityDto dto) throws NotFoundException {
        CreateUtilityArg argument = mapper.toCreate(dto);
        return mapper.toDto(service.create(argument));
    }

    @PutMapping("update")
    @Operation(description = "Обновить поле")
    @ApiResponse(description = "Запись не обновлена", responseCode = "404")
    public UtilityStorageDto update(@RequestBody UpdateUtilityDto dto) {
        UpdateUtilityArg arg = mapper.toUpdate(dto);

        return mapper.toDto(service.update(arg));
    }

    @DeleteMapping("delete/{id}")
    @Operation(description = "Удалить поле")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public UtilityStorageDto deleteUtility(@PathVariable UUID id) {
        UtilityStorage utilityStorage = service.delete(id);

        return mapper.toDto(utilityStorage);
    }

    @GetMapping("get/{id}")
    @Operation(description = "Получить поле")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public UtilityStorageDto get(@PathVariable UUID id) {
        UtilityStorage utilityStorage = service.get(id);

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
