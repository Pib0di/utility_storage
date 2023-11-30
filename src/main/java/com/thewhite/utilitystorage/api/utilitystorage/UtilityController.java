package com.thewhite.utilitystorage.api.utilitystorage;

import com.thewhite.utilitystorage.action.utilityStorage.delete.DeleteUtilityStorageAction;
import com.thewhite.utilitystorage.action.utilityStorage.delete.DeleteUtilityStorageArgument;
import com.thewhite.utilitystorage.api.utilitystorage.dto.CreateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UpdateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UtilityStorageDto;
import com.thewhite.utilitystorage.api.utilitystorage.mapper.UtilityMapper;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.service.utilitystorage.UtilityStorageServiceImpl;
import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.UpdateUtilityArgument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("utility-storages")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с хранилищем")
@Validated
public class UtilityController {

    private final UtilityStorageServiceImpl service;
    private final DeleteUtilityStorageAction deleteUtilityStorageAction;
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

        DeleteUtilityStorageArgument deleteUtilityStorageArgument = DeleteUtilityStorageArgument.builder()
                .utilityStorageId(id)
                .build();

        UtilityStorage utilityStorage = deleteUtilityStorageAction.delete(deleteUtilityStorageArgument);

        return mapper.toDto(utilityStorage);
    }

    @GetMapping("get/{id}")
    @Operation(description = "Получить поле")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public UtilityStorageDto get(@PathVariable UUID id) {
        UtilityStorage utilityStorage = service.get(id);

        return mapper.toDto(utilityStorage);
    }

    @GetMapping("get/{findStr}")
    @Operation(description = "Получить поле")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public List<UtilityStorageDto> search(
            @PathVariable() String findStr,
            @RequestParam(defaultValue = "name") String sortType,
            @RequestParam(defaultValue = "name") String typeRequiredField,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "200") @Min(1) @Max(200) int size)
    {
        Pageable pageable = PageRequest.of(page, size);
        List<UtilityStorage> utilityStorage = service.search(findStr, sortType, typeRequiredField, pageable);

        return mapper.toDtoList(utilityStorage);
    }

}
