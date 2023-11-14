package com.thewhite.utilitystorage.servicess.rating;

import com.thewhite.utilitystorage.models.Rating;
import com.thewhite.utilitystorage.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RatingService {
    RatingRepository ratingRepository;

    public Rating add(Rating rating){
        return ratingRepository.add(rating);
    }

    public Rating delete(UUID id){
        return ratingRepository.delete(id);
    }

    public List<Rating> getList(UUID utilityId){
        return ratingRepository.getList(utilityId);
    }

}
