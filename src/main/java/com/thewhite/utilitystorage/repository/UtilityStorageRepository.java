package com.thewhite.utilitystorage.repository;

import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface UtilityStorageRepository extends JpaRepository<UtilityStorage, UUID>, QuerydslPredicateExecutor<UtilityStorage>, PagingAndSortingRepository<UtilityStorage, UUID> {
}
