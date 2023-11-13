package com.thewhite.utilityStorage.repository;

import com.thewhite.utilityStorage.exception.NotFoundException;
import com.thewhite.utilityStorage.models.UtilityStorage;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Pattern;

@Component
public class UtilityStorageRepository {
    private final Map<UUID, UtilityStorage> utilityStorageMap = new HashMap<>();

    public UtilityStorage create(UtilityStorage utilityStorage) throws NotFoundException {
        UUID key = utilityStorage.getId();
        if (utilityStorageMap.containsKey(key)) {
            throw new NotFoundException("Значение по заданному UUID уже существует");
        }

        utilityStorageMap.put(key, utilityStorage);
        return utilityStorage;
    }

    public UtilityStorage get(UUID id) {
        return utilityStorageMap.get(id);
    }

    public List<UtilityStorage> search(String searchLine) {
        List<UtilityStorage> utilityStorageList = new ArrayList<>();
        Pattern pattern;
        if (searchLine != null && !searchLine.isEmpty()) {
            for (UtilityStorage utilityStorage : utilityStorageMap.values()) {
                pattern = Pattern.compile(Pattern.quote(searchLine), Pattern.CASE_INSENSITIVE);
                if (pattern.matcher(utilityStorage.getName()).find()) {
                    utilityStorageList.add(utilityStorage);
                }
            }
        }

        return utilityStorageList;
    }

    public UtilityStorage update(@NonNull UtilityStorage utilityStorage) {
        UUID key = utilityStorage.getId();

        if (!utilityStorageMap.containsKey(key)) {
            return null;
        }

        utilityStorageMap.put(key, utilityStorage);

        return utilityStorage;
    }

    public UtilityStorage delete(UUID id) {
        return utilityStorageMap.remove(id);
    }

    public List<UtilityStorage> getList() {
        return List.copyOf(utilityStorageMap.values());
    }

}
