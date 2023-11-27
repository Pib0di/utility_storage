
package com.thewhite.utilitystorage.repository;

import com.thewhite.utilitystorage.model.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID>, QuerydslPredicateExecutor<Rating> {

    @Query("select rating from Rating rating where rating.utilityId = :utilityId")
    List<Rating> getList(@Param("utilityId") UUID utilityId);
}