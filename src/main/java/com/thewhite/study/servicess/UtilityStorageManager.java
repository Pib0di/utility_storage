package com.thewhite.study.servicess;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thewhite.study.models.UtilityStorage;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class UtilityStorageManager {
    private final Map<Integer, UtilityStorage> utilityStorageMap = new HashMap<>();
    int idGlobal = 0;

    public void readData(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<UtilityStorage> utilityStorageList = objectMapper.readValue(fileReader, new TypeReference<>() {});

            for (UtilityStorage utilityStorage : utilityStorageList) {
                addUtility(utilityStorage);
            }
        } catch (JsonParseException e) {
            System.err.println("Ошибка синтаксиса JSON: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Произошла ошибка ввода/вывода: " + e.getMessage());
        }
    }

    public void addUtility(UtilityStorage utilityStorage) {
        if (utilityStorage != null) {
            ++idGlobal;
            utilityStorage.setId(idGlobal);
            utilityStorageMap.put(idGlobal, utilityStorage);
        }
    }

    public UtilityStorage getUtility(Integer utilityId) {
        return utilityStorageMap.get(utilityId);
    }

    public List<UtilityStorage> getUtilityStorageList() {
        return List.copyOf(utilityStorageMap.values());
    }

    public List<UtilityStorage> search(String searchLine) {
        List<UtilityStorage> utilityStorageList = new ArrayList<>();

        if (searchLine != null && !searchLine.equals("")) {
            for (UtilityStorage utilityStorage : utilityStorageMap.values()) {
                Pattern pattern = Pattern.compile(Pattern.quote(searchLine), Pattern.CASE_INSENSITIVE);
                boolean found = pattern.matcher(utilityStorage.getName()).find();
                if (found) {
                    utilityStorageList.add(utilityStorage);
                }
            }
        }

        return utilityStorageList;
    }

}
