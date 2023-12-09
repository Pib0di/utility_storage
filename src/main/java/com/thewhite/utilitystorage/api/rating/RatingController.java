package com.thewhite.utilitystorage.api.rating;

import com.thewhite.utilitystorage.action.rating.add.AddRatingAction;
import com.thewhite.utilitystorage.action.rating.add.AddRatingActionArgument;
import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.api.rating.dto.SearchRatingDto;
import com.thewhite.utilitystorage.api.rating.mapper.RatingMapper;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.service.rating.RatingService;
import com.thewhite.utilitystorage.service.rating.argument.SearchRatingArgument;
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

    @DeleteMapping("delete/")
    @Operation(description = "Удалить оценку по id")
    @ApiResponse(description = "Оценка не найдена/удалена", responseCode = "404")
    public RatingDto delete(@RequestParam UUID id) {

        return mapper.toDto(service.delete(id));
    }

    @GetMapping("list/")
    @Operation(description = "Получить список оценок по id записи + фильтр по оценке от 0 до 4")
    public List<RatingDto> getList(@RequestParam UUID utilityStorageId,
                                   @Valid SearchRatingDto searchRatingDto,
                                   @PageableDefault(size = 10, page = 0, sort = "point", direction = DESC) Pageable pageable) {

        SearchRatingArgument searchRatingArgument = mapper.toCreateRatingArgument(searchRatingDto);
        List<Rating> ratingList = service.getList(utilityStorageId, searchRatingArgument, pageable);

        return mapper.toDtoRatingList(ratingList);
    }
}
