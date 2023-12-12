package com.thewhite.utilitystorage.repository;

import com.thewhite.utilitystorage.model.rating.Rating;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID>, QuerydslPredicateExecutor<Rating>, PagingAndSortingRepository<Rating, UUID> {

    @Modifying
    @Query("delete from Rating rating where rating.utilityStorage = :utilityStorageId")
    void deleteAllByUtilityId(@Param("utilityStorageId") UtilityStorage utilityStorageId);

}