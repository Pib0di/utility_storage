package com.thewhite.utilitystorage.action.rating.add;

import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.service.rating.RatingService;
import com.thewhite.utilitystorage.service.rating.argument.AddRatingArgument;
import com.thewhite.utilitystorage.service.utilitystorage.UtilityStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddRatingAction {
    private final RatingService ratingService;
    private final UtilityStorageService utilityStorageService;

    public Rating add(CreateRatingArgument argument) {
        if (utilityStorageService.get(argument.getUtilityStorageId()) == null) {
            throw new BadInputDataForRating("Запись по заданному id не найдена");
        }

        return ratingService.add(AddRatingArgument.builder()
                .point(argument.getPoint())
                .utilityStorageId(argument.getUtilityStorageId())
                .build());
    }
}
