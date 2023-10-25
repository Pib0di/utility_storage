package com.thewhite.study.servicess;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.thewhite.study.models.UtilityStorage;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Pattern;

public class UtilityStorageManager {
    public UtilityStorageManager(String filePath) {
        readData(filePath);
    }

    public void readData(String filePath) {
        try (FileReader fileReader = new FileReader(filePath);
             JsonReader jsonReader = new JsonReader(fileReader)) {

            // чтение JSON и преобразование его в коллекцию
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<UtilityStorage>>() {}.getType();
            Collection<UtilityStorage> utilityStorageCollection = gson.fromJson(jsonReader, collectionType);

            for (UtilityStorage utilityStorage : utilityStorageCollection) {
                addUtility(utilityStorage);
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка ввода/вывода: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Ошибка синтаксиса JSON: " + e.getMessage());
        }
    }

    int idGlobal = 0;
    public void addUtility(UtilityStorage utilityStorage) {
        if (utilityStorage != null) {
            ++idGlobal;
            utilityStorage.id = idGlobal;
            utilityStorageMap.put(idGlobal, utilityStorage);
        }
    }

    public UtilityStorage getUtility(Integer utilityId) {
        for (UtilityStorage utilityStorage : utilityStorageMap.values()) {
            if (utilityStorage.id == utilityId) {
                return utilityStorage;
            }
        }
        return null;
    }

    public Integer getUtilitySize() {
        return utilityStorageMap.size();
    }
    public List<UtilityStorage> getUtilityStorageList (){
        List<UtilityStorage> utilityStorageList = utilityStorageMap.values().stream().toList();
        return utilityStorageList;
    }

    public List<UtilityStorage> search(String searchLine) {
        List<UtilityStorage> indexArray = new ArrayList<>();
        if (searchLine != null && searchLine != "") {
            for (UtilityStorage utilityStorage : utilityStorageMap.values()) {
                Pattern pattern = Pattern.compile(Pattern.quote(searchLine), Pattern.CASE_INSENSITIVE);
                Boolean found = pattern.matcher(utilityStorage.name).find();
                if (found) {
                    indexArray.add(utilityStorage);
                }
            }
        }

        return indexArray;
    }

    private Map<Integer, UtilityStorage> utilityStorageMap = new HashMap<>();
}
