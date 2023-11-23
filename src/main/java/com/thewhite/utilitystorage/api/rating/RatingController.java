package com.thewhite.utilitystorage.api.rating;

import com.thewhite.utilitystorage.action.rating.add.AddRatingAction;
import com.thewhite.utilitystorage.action.rating.add.CreateRatingArgument;
import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.api.rating.mapper.RatingMapper;
import com.thewhite.utilitystorage.service.rating.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("ratings")
@RequiredArgsConstructor
@Tag(name = "контроллер для работы с рейтингом полезностей")
@Validated
public class RatingController {
    private final RatingService service;
    private final RatingMapper mapper;
    private  final AddRatingAction addRatingAction;

    @PostMapping("add")
    @Operation(description = "Добавить оценку к записи")
    public RatingDto add(@Valid @RequestBody AddRatingDto addRatingDto) {

        CreateRatingArgument createRatingArgument = mapper.toCreateRatingArgument(addRatingDto);

        return mapper.toDto(addRatingAction.add(createRatingArgument));
    }

    @DeleteMapping("delete/{id}")
    @Operation(description = "Удалить оценку по id")
    public RatingDto delete(@PathVariable UUID id) {
        return mapper.toDto(service.delete(id));
    }

    @GetMapping("list/{utilityStorageId}")
    @Operation(description = "Получить список оценок по id записи")
    public List<RatingDto> getList(@PathVariable UUID utilityStorageId) {

        return mapper.toDtoRatingList(service.getList(utilityStorageId));
    }
}
