package com.thewhite.utilitystorage.repository;

import com.thewhite.utilitystorage.model.rating.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID>, QuerydslPredicateExecutor<Rating>, PagingAndSortingRepository<Rating, UUID> {

    @Modifying
    @Query("delete from Rating rating where rating.utilityStorageId = :utilityStorageId")
    void deleteAllByUtilityId(@Param("utilityStorageId") UUID utilityStorageId);

}