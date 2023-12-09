package com.thewhite.utilitystorage.api.utilitystorage;

import com.thewhite.utilitystorage.api.utilitystorage.dto.CreateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.SearchUtilityStorageDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UpdateUtilityDto;
import com.thewhite.utilitystorage.api.utilitystorage.dto.UtilityStorageDto;
import com.thewhite.utilitystorage.api.utilitystorage.mapper.UtilityMapper;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.service.utilitystorage.UtilityStorageService;
import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.SearchUtilityStorageArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.UpdateUtilityArgument;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequestMapping("utility-storages")
@RequiredArgsConstructor
@Tag(name = "Контроллер для работы с хранилищем")
@Validated
public class UtilityController {

    private final UtilityStorageService service;
    private final UtilityMapper mapper;

    @PostMapping("create")
    @Operation(description = "Создать поле")
    public UtilityStorageDto create(@RequestBody @Valid CreateUtilityDto dto) throws NotFoundException {
        CreateUtilityArgument argument = mapper.toCreate(dto);
        return mapper.toDto(service.create(argument));
    }

    @PutMapping("update")
    @Operation(description = "Обновить поле")
    @ApiResponse(description = "Запись не обновлена", responseCode = "404")
    public UtilityStorageDto update(@RequestBody @Valid  UpdateUtilityDto dto) {
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

    @GetMapping("search")
    @Operation(description = "Получить поле")
    @ApiResponse(description = "Запись не найдена", responseCode = "404")
    public List<UtilityStorageDto> search(
            @Valid SearchUtilityStorageDto dto,
            @PageableDefault(size = 10, page = 0, sort = "name", direction = DESC) Pageable pageable) {

        SearchUtilityStorageArgument argument = mapper.toSearch(dto);

        List<UtilityStorage> utilityStorage = service.search(argument, pageable);

        return mapper.toDtoList(utilityStorage);
    }

}
