package com.thewhite.utilitystorage.api.utilitystorage;

import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.UpdateUtilityArgument;
import com.thewhite.utilitystorage.api.utilitystorage.dto.CreateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UpdateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UtilityStorageDto;
import com.thewhite.utilitystorage.api.utilitystorage.mapper.UtilityMapper;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.service.utilitystorage.UtilityStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("utility-storages")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с хранилищем")
public class UtilityController {

    private final UtilityStorageService service;
    private final UtilityMapper mapper;

    @PostMapping("create")
    @Operation(description = "Создать поле")
    public UtilityStorageDto create(@RequestBody CreateUtilityDto dto) throws NotFoundException {
        CreateUtilityArgument argument = mapper.toCreate(dto);
        return mapper.toDto(service.create(argument));
    }

    @PutMapping("update")
    @Operation(description = "Обновить поле")
    @ApiResponse(description = "Запись не обновлена", responseCode = "404")
    public UtilityStorageDto update(@RequestBody UpdateUtilityDto dto) {
        UpdateUtilityArgument arg = mapper.toUpdate(dto);

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
