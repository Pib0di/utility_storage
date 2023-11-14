package com.thewhite.utilitystorage.servicess.utilitystorage;

import com.thewhite.utilitystorage.action.argument.UtilityStorage.CreateUtilityArg;
import com.thewhite.utilitystorage.exception.NotFoundException;
import com.thewhite.utilitystorage.models.UtilityStorage;
import com.thewhite.utilitystorage.action.argument.UtilityStorage.UpdateUtilityArg;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface UtilityStorageService {

    UtilityStorage create(CreateUtilityArg createUtility) throws NotFoundException;

    UtilityStorage get(UUID id);

    List<UtilityStorage> search(String searchLine);

    UtilityStorage update(@NonNull UpdateUtilityArg updateUtility);

    UtilityStorage delete(UUID id);


    List<UtilityStorage> getUtilityStorageList();
}
