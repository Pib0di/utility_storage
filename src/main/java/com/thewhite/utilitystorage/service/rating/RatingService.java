package com.thewhite.utilitystorage.service.rating;

import com.querydsl.core.types.Predicate;
import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.model.rating.NumberPoints;
import com.thewhite.utilitystorage.model.rating.QRating;
import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.repository.RatingRepository;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import com.thewhite.utilitystorage.service.rating.argument.AddRatingArgument;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UtilityStorageRepository utilityStorageRepository;
    private final QRating qRating = QRating.rating;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Rating add(@NonNull @Valid AddRatingArgument addRatingArgument) {
        UtilityStorage utilityStorage = utilityStorageRepository.findById(addRatingArgument.getUtilityStorageId())
                .orElseThrow(() -> new BadInputDataForRating("Запись с узказанным UtilityStorageId не найдена"));


        return ratingRepository.save(Rating.builder()
                .id(UUID.randomUUID())
                .utilityStorage(utilityStorage)
                .point(addRatingArgument.getPoint())
                .description(addRatingArgument.getDescription())
                .build());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Rating delete(UUID id) {
        Optional<Rating> rating = ratingRepository.findById(id);
        if (rating.isEmpty()) {
            throw new BadInputDataForRating("Запись по указанному id не найдена");
        }

        ratingRepository.deleteById(id);
        return rating.get();
    }

    @Transactional(readOnly = true)
    public List<Rating> getList(UUID utilityStorageId, Pageable pageable) {
        UtilityStorage utilityStorage = utilityStorageRepository.findById(utilityStorageId)
                .orElseThrow(() -> new BadInputDataForRating("Запись с узказанным UtilityStorageId не найдена"));

        System.out.println(NumberPoints.valueOf(pageable.getSort().get().toString()));
        NumberPoints numberPoints = NumberPoints.valueOf(pageable.getSort().get().toString());

        Predicate predicate = qRating
                .utilityStorage.eq(utilityStorage)
                .and(qRating.point.eq(numberPoints));

        return ratingRepository.findAll(predicate, pageable).toList();
    }
}
