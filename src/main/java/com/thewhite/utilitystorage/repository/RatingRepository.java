
package com.thewhite.utilitystorage.repository;

import com.thewhite.utilitystorage.model.rating.Rating;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class RatingRepository {
    private final Map<UUID, Rating> ratingMap = new HashMap<>();

    public Rating add(Rating rating) {

        if (ratingMap.put(rating.getId(), rating) == null) {
            return rating;
        }

        return null;
    }

    public Rating delete(UUID id) {
        return ratingMap.remove(id);
    }

    public List<Rating> getList(UUID utilityId) {
        return ratingMap.values().stream()
                .filter(arg -> arg.getUtilityId().equals(utilityId))
                .toList();
    }
}