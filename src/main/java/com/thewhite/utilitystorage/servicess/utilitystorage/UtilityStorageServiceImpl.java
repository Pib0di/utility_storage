package com.thewhite.utilitystorage.servicess.utilitystorage;

import com.thewhite.utilitystorage.action.argument.UtilityStorage.CreateUtilityArg;
import com.thewhite.utilitystorage.action.argument.UtilityStorage.UpdateUtilityArg;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.models.UtilityStorage;
import com.thewhite.utilitystorage.repository.UtilityStorageRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UtilityStorageServiceImpl implements UtilityStorageService {
    UtilityStorageRepository utilityStorageRepository;

    @Override
    public UtilityStorage create(CreateUtilityArg createUtility) throws NotFoundException {
        UUID id = UUID.randomUUID();
        return utilityStorageRepository.create(UtilityStorage.builder()
                .id(id)
                .name(createUtility.getName())
                .link(createUtility.getLink())
                .description(createUtility.getDescription())
                .build());
    }

    @Override
    public UtilityStorage get(UUID id) {
        return utilityStorageRepository.get(id);
    }

    @Override
    public List<UtilityStorage> search(String searchLine) {
        return utilityStorageRepository.search(searchLine);
    }

    @Override
    public UtilityStorage update(@NonNull UpdateUtilityArg updateUtilityArg) {
        return utilityStorageRepository.update(UtilityStorage.builder()
                .id(updateUtilityArg.getId())
                .name(updateUtilityArg.getName())
                .description(updateUtilityArg.getDescription())
                .link(updateUtilityArg.getLink())
                .build());
    }

    @Override
    public UtilityStorage delete(UUID id) {
        return utilityStorageRepository.delete(id);
    }

    @Override
    public List<UtilityStorage> getUtilityStorageList() {
        return utilityStorageRepository.getList();
    }
}
