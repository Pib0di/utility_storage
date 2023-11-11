package com.thewhite.utility_storage.servicess;

import com.thewhite.utility_storage.exception.NotFoundException;
import com.thewhite.utility_storage.models.UtilityStorage;
import com.thewhite.utility_storage.servicess.argument.CreateUtilityStorageArg;
import com.thewhite.utility_storage.servicess.argument.UpdateUtilityStorageArg;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface UtilityStorageService {

    UtilityStorage createUtility(CreateUtilityStorageArg createUtility) throws NotFoundException;

    UtilityStorage getUtility(UUID uuid);

    List<UtilityStorage> search(String searchLine);

    UtilityStorage updateUtility(@NonNull UpdateUtilityStorageArg updateUtility);

    UtilityStorage deleteUtility(UUID uuid);


    List<UtilityStorage> getUtilityStorageList();
}
