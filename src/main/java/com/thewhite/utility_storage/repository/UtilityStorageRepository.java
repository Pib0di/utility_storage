package com.thewhite.utility_storage.repository;

import com.thewhite.utility_storage.exception.NotFoundException;
import com.thewhite.utility_storage.models.UtilityStorage;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;

@Component
public class UtilityStorageRepository {
    private final Map<UUID, UtilityStorage> utilityStorageMap = new HashMap<>();

    public UtilityStorage createUtility(UtilityStorage utilityStorage) throws NotFoundException {
        UUID key = utilityStorage.getUuid();
        if (!utilityStorageMap.containsKey(key) && utilityStorageMap.put(key, utilityStorage) == null) {
            return utilityStorage;
        } else {
            throw new NotFoundException("Значение по заданному UUID уже существует");
        }
    }

    public UtilityStorage getUtility(UUID uuid) {
        return utilityStorageMap.get(uuid);
    }

    public List<UtilityStorage> search(String searchLine) {
        List<UtilityStorage> utilityStorageList = new ArrayList<>();

        if (searchLine != null && !searchLine.isEmpty()) {
            for (UtilityStorage utilityStorage : utilityStorageMap.values()) {
                Pattern pattern = Pattern.compile(Pattern.quote(searchLine), Pattern.CASE_INSENSITIVE);
                if (pattern.matcher(utilityStorage.getName()).find()) {
                    utilityStorageList.add(utilityStorage);
                }
            }
        }

        return utilityStorageList;
    }

    public UtilityStorage updateUtility(@NonNull UtilityStorage utilityStorage) {
        UUID key = utilityStorage.getUuid();
        if (utilityStorageMap.containsKey(key) && utilityStorageMap.put(key, utilityStorage) != null) {
            return utilityStorage;
        } else {
            return null;
        }
    }

    public UtilityStorage deleteUtility(UUID uuid) {
        return utilityStorageMap.remove(uuid);
    }

    public void clear() {
        utilityStorageMap.clear();
    }

    public List<UtilityStorage> getUtilityStorageList() {
        return List.copyOf(utilityStorageMap.values());
    }

}
