package com.thewhite.utilitystorage.service.rating;

import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.repository.RatingRepository;
import com.thewhite.utilitystorage.service.rating.argument.AddRatingArgument;
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

    public Rating add(AddRatingArgument addRatingArgument) {

        return ratingRepository.add(
                Rating.builder()
                        .id(UUID.randomUUID())
                        .utilityId(addRatingArgument.getUtilityStorageId())
                        .point(addRatingArgument.getPoint())
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
