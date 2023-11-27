package com.thewhite.utilitystorage.repository;

import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface UtilityStorageRepository extends JpaRepository<UtilityStorage, UUID>, QuerydslPredicateExecutor<UtilityStorage> {

    @Query("SELECT storage FROM UtilityStorage storage  WHERE lower(storage.name) LIKE lower(concat('%', :name,'%'))")
    List<UtilityStorage> searchByName(String name);

    @Query("SELECT storage FROM UtilityStorage storage  WHERE lower(storage.description) LIKE lower(concat('%', :description,'%'))")
    List<UtilityStorage> searchByDescription(String description);

}
