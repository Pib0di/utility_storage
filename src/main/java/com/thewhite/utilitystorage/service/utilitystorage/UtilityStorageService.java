package com.thewhite.utilitystorage.service.utilitystorage;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.model.utilityStorage.QUtilityStorage;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.SearchUtilityStorageArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.UpdateUtilityArgument;
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
public class UtilityStorageService {
    private final UtilityStorageRepository utilityStorageRepository;
    private final QUtilityStorage qUtilityStorage = QUtilityStorage.utilityStorage;


    @Transactional
    public UtilityStorage create(@Valid CreateUtilityArgument createUtility) throws NotFoundException {
        return utilityStorageRepository.save(UtilityStorage.builder()
                .id(UUID.randomUUID())
                .name(createUtility.getName())
                .link(createUtility.getLink())
                .description(createUtility.getDescription())
                .build());
    }

    @Transactional(readOnly = true)
    public UtilityStorage get(UUID id) {
        return utilityStorageRepository.findById(id)
                .orElseThrow(() -> new BadInputDataForRating("Запись по указанному id не найдена"));
    }

    @Transactional(readOnly = true)
    public List<UtilityStorage> search(SearchUtilityStorageArgument argument, Pageable pageable) {

        BooleanExpression expression = qUtilityStorage.description.containsIgnoreCase(
                argument.getDescription() != null ? argument.getDescription() : ""
        );

        expression = argument.getName() != null
                ? expression.and(qUtilityStorage.name.containsIgnoreCase(argument.getName()))
                : expression;

        return utilityStorageRepository.findAll(expression, pageable).toList();
    }


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UtilityStorage update(@NonNull @Valid UpdateUtilityArgument updateUtilityArgument) {
        Optional<UtilityStorage> utilityStorage = utilityStorageRepository.findById(updateUtilityArgument.getId());
        if (utilityStorage.isEmpty()) {
            throw new BadInputDataForRating("Запись по указанному id не найдена");
        }
        return utilityStorageRepository.save(UtilityStorage.builder()
                .id(updateUtilityArgument.getId())
                .name(updateUtilityArgument.getName())
                .description(updateUtilityArgument.getDescription())
                .link(updateUtilityArgument.getLink())
                .build());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UtilityStorage delete(UUID id) {
        Optional<UtilityStorage> utilityStorage = utilityStorageRepository.findById(id);
        if (utilityStorage.isEmpty()) {
            throw new BadInputDataForRating("Запись по указанному id не найдена");
        }

        utilityStorageRepository.deleteById(id);
        return utilityStorage.get();
    }

}
