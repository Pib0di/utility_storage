package com.thewhite.utilitystorage.servicess.rating;

import com.thewhite.utilitystorage.action.argument.rating.AddRatingArg;
import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.models.Rating;
import com.thewhite.utilitystorage.repository.RatingRepository;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RatingService {
    RatingRepository ratingRepository;
    UtilityStorageRepository repository;

    public Rating add(AddRatingArg addRatingArg) {
        if(repository.get(addRatingArg.getUtilityId()) == null){
            throw new BadInputDataForRating("Запись по заданному id не найдена");
        }
        return ratingRepository.add(
                Rating.builder()
                        .id(UUID.randomUUID())
                        .utilityId(addRatingArg.getUtilityId())
                        .point(addRatingArg.getPoint())
                        .build()
        );
    }

    public Rating delete(UUID id) {
        Rating result = ratingRepository.delete(id);
        if (result == null){
            throw new BadInputDataForRating("Запись по указанному id не найдена");
        }
        return result;
    }

    public List<Rating> getList(UUID utilityId) {
        return ratingRepository.getList(utilityId);
    }

}
