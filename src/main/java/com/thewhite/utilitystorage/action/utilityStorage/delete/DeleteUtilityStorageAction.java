package com.thewhite.utilitystorage.action.utilityStorage.delete;

import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.service.rating.RatingService;
import com.thewhite.utilitystorage.service.utilitystorage.UtilityStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteUtilityStorageAction {
    private final RatingService ratingService;
    private final UtilityStorageService utilityStorageService;

    public UtilityStorage delete(DeleteUtilityStorageArgument argument) {
        UtilityStorage result = utilityStorageService.delete(argument.getUtilityStorageId());

        if (result == null){
            throw new BadInputDataForRating("Запись по заданному id не найдена");
        }

        ratingService.deleteAllByUtilityId(argument.getUtilityStorageId());

        return result;
    }
}
