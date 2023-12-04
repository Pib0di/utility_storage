package com.thewhite.utilitystorage.service.rating;

import com.querydsl.core.types.Predicate;
import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.model.rating.NumberPoints;
import com.thewhite.utilitystorage.model.rating.QRating;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.repository.RatingRepository;
import com.thewhite.utilitystorage.service.rating.argument.AddRatingArgument;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RatingService {

    private final RatingRepository ratingRepository;
    private final EntityManager entityManager;
    private final QRating qRating = QRating.rating;

    @Transactional
    public Rating add(@NonNull AddRatingArgument addRatingArgument) {
        return ratingRepository.save(Rating.builder()
                .id(UUID.randomUUID())
                .utilityStorageId(addRatingArgument.getUtilityStorageId())
                .point(addRatingArgument.getPoint())
                .description(addRatingArgument.getDescription())
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

    @Transactional
    public void deleteAllByUtilityId(UUID utilityStorageId) {
        ratingRepository.deleteAllByUtilityId(utilityStorageId);
    }

    @Transactional(readOnly = true)
    public List<Rating> getList(UUID utilityStorageId, Pageable pageable) {

        System.out.println(NumberPoints.valueOf(pageable.getSort().get().toString()));
        NumberPoints numberPoints = NumberPoints.valueOf(pageable.getSort().get().toString());

        Predicate predicate = qRating
                .utilityStorageId.eq(utilityStorageId)
                .and(qRating.point.eq(numberPoints));

        return ratingRepository.findAll(predicate, pageable).toList();
    }
}
