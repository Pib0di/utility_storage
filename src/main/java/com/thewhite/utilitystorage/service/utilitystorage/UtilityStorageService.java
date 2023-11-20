package com.thewhite.utilitystorage.service.utilitystorage;

import com.thewhite.utilitystorage.service.utilitystorage.argument.CreateUtilityArgument;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.model.utilityStorage.UtilityStorage;
import com.thewhite.utilitystorage.service.utilitystorage.argument.UpdateUtilityArgument;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface UtilityStorageService {

    UtilityStorage create(CreateUtilityArgument createUtility) throws NotFoundException;

    UtilityStorage get(UUID id);

    List<UtilityStorage> search(String searchLine);

    UtilityStorage update(@NonNull UpdateUtilityArgument updateUtility);

    UtilityStorage delete(UUID id);


    List<UtilityStorage> getUtilityStorageList();
}
