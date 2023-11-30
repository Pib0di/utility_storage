package com.thewhite.utilitystorage.service.utilitystorage;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.thewhite.utilitystorage.exception.BadInputDataForRating;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.model.utilityStorage.QUtilityStorage;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.UpdateUtilityArgument;
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
public class UtilityStorageServiceImpl {
    private final UtilityStorageRepository utilityStorageRepository;
    private final EntityManager entityManager;
    private final QUtilityStorage qUtilityStorage = QUtilityStorage.utilityStorage;


    @Transactional
    public UtilityStorage create(CreateUtilityArgument createUtility) throws NotFoundException {
        return utilityStorageRepository.save(UtilityStorage.builder()
                .id(UUID.randomUUID())
                .name(createUtility.getName())
                .link(createUtility.getLink())
                .description(createUtility.getDescription())
                .build());
    }

    @Transactional(readOnly = true)
    public UtilityStorage get(UUID id) {
        Optional<UtilityStorage> utilityStorage = utilityStorageRepository.findById(id);
        if (utilityStorage.isEmpty()) {
            throw new BadInputDataForRating("Запись по указанному id не найдена");
        }
        return utilityStorage.get();
    }

    @Transactional(readOnly = true)
    public List<UtilityStorage> search(String findStr, String sortType, String typeRequiredField, Pageable pageable  ) {

        Predicate predicate= switch (typeRequiredField) {
            case "description":
                yield qUtilityStorage.description.eq(findStr);
            default:
                yield qUtilityStorage.name.eq(findStr);
        };

        var order = switch (sortType) {
            case "description":
                yield qUtilityStorage.description.desc();
            default:
                yield qUtilityStorage.name.desc();
        };


        return new JPAQuery<UtilityStorage>(entityManager)
                .select(qUtilityStorage)
                .from(qUtilityStorage)
                .where(predicate)
                .orderBy(order)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


    @Transactional
    public UtilityStorage update(@NonNull UpdateUtilityArgument updateUtilityArgument) {
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

    @Transactional
    public UtilityStorage delete(UUID id) {
        Optional<UtilityStorage> utilityStorage = utilityStorageRepository.findById(id);
        if (utilityStorage.isEmpty()) {
            throw new BadInputDataForRating("Запись по указанному id не найдена");
        }

        utilityStorageRepository.deleteById(id);
        return utilityStorage.get();
    }

}
