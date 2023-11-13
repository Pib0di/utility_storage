package com.thewhite.utilityStorage.servicess.UtilityStorage;

import com.thewhite.utilityStorage.action.argument.UtilityStorage.CreateUtilityArg;
import com.thewhite.utilityStorage.exception.NotFoundException;
import com.thewhite.utilityStorage.models.UtilityStorage;
import com.thewhite.utilityStorage.action.argument.UtilityStorage.UpdateUtilityArg;
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
