package com.thewhite.utilitystorage.service.rating;

import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.repository.RatingRepository;
import com.thewhite.utilitystorage.service.rating.argument.AddRatingArgument;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RatingService {
    RatingRepository ratingRepository;

    @Transactional
    public Rating add(AddRatingArgument addRatingArgument) {
        return ratingRepository.save(Rating.builder()
                .id(UUID.randomUUID())
                .utilityId(addRatingArgument.getUtilityStorageId())
                .point(addRatingArgument.getPoint())
                .build());
    }

    @Transactional
    public Rating delete(UUID id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isEmpty()) {
            throw new BadInputDataForRating("Запись по указанному id не найдена");
        }
        ratingRepository.deleteById(id);
        return rating.get();
    }

    @Transactional(readOnly = true)
    public List<Rating> getList(UUID utilityId) {
        return ratingRepository.getList(utilityId);
    }

}
