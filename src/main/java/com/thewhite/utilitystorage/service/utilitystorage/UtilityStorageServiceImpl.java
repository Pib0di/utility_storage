package com.thewhite.utilitystorage.service.utilitystorage;

import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import com.thewhite.utilitystorage.service.utilitystorage.argument.UpdateUtilityArgument;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
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
    public UtilityStorage create(CreateUtilityArgument createUtility) throws NotFoundException {
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
    public UtilityStorage update(@NonNull UpdateUtilityArgument updateUtilityArgument) {
        return utilityStorageRepository.update(UtilityStorage.builder()
                .id(updateUtilityArgument.getId())
                .name(updateUtilityArgument.getName())
                .description(updateUtilityArgument.getDescription())
                .link(updateUtilityArgument.getLink())
                .build());
    }

    @Override
    public UtilityStorage delete(UUID id) {
        return utilityStorageRepository.delete(id);
    }

}
