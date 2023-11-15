package com.thewhite.utilitystorage.api.rating;

import com.thewhite.utilitystorage.action.argument.rating.AddRatingArg;
import com.thewhite.utilitystorage.api.rating.dto.AddRatingDto;
import com.thewhite.utilitystorage.api.rating.dto.RatingDto;
import com.thewhite.utilitystorage.api.rating.mapper.RatingMapper;
import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.servicess.rating.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("rating")
@RequiredArgsConstructor
@Tag(name = "контроллер для работы с рейтингом полезностей")
public class RatingController {
    private final RatingService service;
    private final RatingMapper mapper;

    @PostMapping("add")
    @Operation(description = "Добавить оценку к записи")
    public RatingDto add(@RequestBody AddRatingDto addRatingDto) {

            AddRatingArg addRatingArg = mapper.toAdd(addRatingDto);

            return mapper.toDto(service.add(addRatingArg));
    }

    @DeleteMapping("delete/{id}")
    @Operation(description = "Удалить оценку по id")
    public RatingDto delete(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new BadInputDataForRating("неверный формат id (невозможно преобразовать к UUID)");
        }

        return mapper.toDto(service.delete(uuid));

    }

    @GetMapping("getList/{idUtility}")
    @Operation(description = "Получить список оценок по id записи")
    public List<RatingDto> getList(@PathVariable String idUtility) {
        UUID uuid;
        try {
            uuid = UUID.fromString(idUtility);
        } catch (IllegalArgumentException e) {
            throw new BadInputDataForRating("неверный формат id (невозможно преобразовать к UUID)");
        }

        return mapper.toDtoList(service.getList(uuid));
    }
}
