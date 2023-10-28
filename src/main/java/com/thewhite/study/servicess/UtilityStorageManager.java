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
    private final Map<Integer, UtilityStorage> utilityStorageMap = new HashMap<>();
    int idGlobal = 0;

    public UtilityStorageManager() {}

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

    public void addUtility(UtilityStorage utilityStorage) {
        if (utilityStorage != null) {
            ++idGlobal;
            utilityStorage.id = idGlobal;
            utilityStorageMap.put(idGlobal, utilityStorage);
        }
    }

    public UtilityStorage getUtility(Integer utilityId) {
        return utilityStorageMap.get(utilityId);
    }

    public List<UtilityStorage> getUtilityStorageList (){
        return List.copyOf(utilityStorageMap.values());
    }

    public List<UtilityStorage> search(String searchLine) {
        List<UtilityStorage> indexArray = new ArrayList<>();
        if (searchLine != null && !searchLine.equals("")) {
            for (UtilityStorage utilityStorage : utilityStorageMap.values()) {
                Pattern pattern = Pattern.compile(Pattern.quote(searchLine), Pattern.CASE_INSENSITIVE);
                boolean found = pattern.matcher(utilityStorage.name).find();
                if (found) {
                    indexArray.add(utilityStorage);
                }
            }
        }

        return indexArray;
    }

}
