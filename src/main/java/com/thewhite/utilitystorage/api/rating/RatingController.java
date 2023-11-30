package com.thewhite.utilitystorage.api.rating;

import com.thewhite.utilitystorage.action.rating.add.AddRatingAction;
import com.thewhite.utilitystorage.action.rating.add.AddRatingActionArgument;
import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.api.rating.mapper.RatingMapper;
import com.thewhite.utilitystorage.model.rating.NumberPoints;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.service.rating.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@RequestMapping("ratings")
@RequiredArgsConstructor
@Tag(name = "контроллер для работы с рейтингом полезностей")
@Validated
public class RatingController {
    private final RatingService service;
    private final RatingMapper mapper;
    private final AddRatingAction addRatingAction;

    @PostMapping("add")
    @Operation(description = "Добавить оценку к записи")
    public RatingDto add(@Valid @RequestBody AddRatingDto addRatingDto) {

        AddRatingActionArgument addRatingActionArgument = mapper.toCreateRatingArgument(addRatingDto);

        return mapper.toDto(addRatingAction.add(addRatingActionArgument));
    }

    @DeleteMapping("delete/{id}")
    @Operation(description = "Удалить оценку по id")
    @ApiResponse(description = "Оценка не найдена/удалена", responseCode = "404")
    public RatingDto delete(@PathVariable UUID id) {

        return mapper.toDto(service.delete(id));
    }

    @GetMapping("list/{utilityStorageId}/filter/{point}")
    @Operation(description = "Получить список оценок по id записи + фильтр по оценке от 0 до 4")
    public List<RatingDto> getList(@PathVariable UUID utilityStorageId,
                                   @PathVariable NumberPoints point,
                                   @RequestParam(defaultValue = "desc") String sortType,
                                   @RequestParam(defaultValue = "0") @Min(0) int page,
                                   @RequestParam(defaultValue = "200") @Min(1) @Max(200) int size) {

        Pageable pageable = PageRequest.of(page, size);

        List<Rating> ratingList = service.getList(utilityStorageId, point, sortType, pageable);

        return mapper.toDtoRatingList(ratingList);
    }
}
