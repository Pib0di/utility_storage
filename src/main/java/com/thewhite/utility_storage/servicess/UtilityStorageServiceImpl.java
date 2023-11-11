package com.thewhite.utility_storage.servicess;

import com.thewhite.utility_storage.exception.NotFoundException;
import com.thewhite.utility_storage.models.UtilityStorage;
import com.thewhite.utility_storage.repository.UtilityStorageRepository;
import com.thewhite.utility_storage.servicess.argument.CreateUtilityStorageArg;
import com.thewhite.utility_storage.servicess.argument.UpdateUtilityStorageArg;
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
    private final UtilityStorageRepository utilityStorageRepository;

    public UtilityStorage createUtility(CreateUtilityStorageArg createUtility) throws NotFoundException {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        return utilityStorageRepository.createUtility(UtilityStorage.builder()
                .uuid(uuid)
                .name(createUtility.getName())
                .link(createUtility.getLink())
                .description(createUtility.getDescription())
                .build());
    }

    public UtilityStorage getUtility(UUID uuid) {
        return utilityStorageRepository.getUtility(uuid);
    }

    public List<UtilityStorage> search(String searchLine) {
        return utilityStorageRepository.search(searchLine);
    }

    public UtilityStorage updateUtility(@NonNull UpdateUtilityStorageArg updateUtilityStorageArg) {
        System.out.println("updateUtilityStorageArg => " + updateUtilityStorageArg);
        return utilityStorageRepository.updateUtility(UtilityStorage.builder()
                .uuid(updateUtilityStorageArg.getUuid())
                .name(updateUtilityStorageArg.getName())
                .description(updateUtilityStorageArg.getDescription())
                .link(updateUtilityStorageArg.getLink())
                .build());
    }

    public UtilityStorage deleteUtility(UUID uuid) {
        return utilityStorageRepository.deleteUtility(uuid);
    }


    public List<UtilityStorage> getUtilityStorageList() {
        return utilityStorageRepository.getUtilityStorageList();
    }
}
