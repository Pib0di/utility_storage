package com.thewhite.utilitystorage.action.rating.add;

import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.service.rating.RatingService;
import com.thewhite.utilitystorage.service.rating.argument.AddRatingArgument;
import com.thewhite.utilitystorage.service.utilitystorage.UtilityStorageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@RequiredArgsConstructor
public class AddRatingAction {
    private final RatingService ratingService;
    private final UtilityStorageServiceImpl utilityStorageService;

    public Rating add(AddRatingActionArgument argument) {
        if (utilityStorageService.get(argument.getUtilityStorageId()) == null) {
            throw new BadInputDataForRating("Запись (UtilityStorage) по заданному id не найдена");
        }

        return ratingService.add(AddRatingArgument.builder()
                .description(argument.getDescription())
                .point(argument.getPoint())
                .utilityStorageId(argument.getUtilityStorageId())
                .build()
        );
    }
}
