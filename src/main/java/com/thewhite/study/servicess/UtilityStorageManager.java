package com.thewhite.study.servicess;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.thewhite.study.models.UtilityStorage;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilityStorageManager {
    public UtilityStorageManager(){}
    public UtilityStorageManager(String filePath){
        try (FileReader fileReader = new FileReader(filePath);
             JsonReader jsonReader = new JsonReader(fileReader)) {

            // чтение JSON и преобразование его в коллекцию
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<UtilityStorage>>(){}.getType();
            Collection<UtilityStorage> enums = gson.fromJson(jsonReader, collectionType);

            int id = 0;
            for (UtilityStorage anEnum : enums) {
                addUtility(id++, anEnum);
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка ввода/вывода: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Ошибка синтаксиса JSON: " + e.getMessage());
        }
    }

    public void addUtility(Integer utilityId, UtilityStorage utilityStorage) {
        utilityStorageMap.put(utilityId, utilityStorage);
    }

    public UtilityStorage getUtility(Integer utilityId) {
        return utilityStorageMap.get(utilityId);
    }
    void outputAtId(Integer utilityId){
        UtilityStorage utility = getUtility(utilityId);
        System.out.println("id: " + utility.id);
        System.out.println("name: " + utility.name);
        System.out.println("description: " + utility.description);
        System.out.println("link: " + utility.link);
        System.out.println();
    }

    List<Integer> search(String searchLine) {
        List<Integer> indexArray = new ArrayList<>();

        if (searchLine != null){
            for (Map.Entry<Integer, UtilityStorage> entry : utilityStorageMap.entrySet()) {
                Integer key = entry.getKey();
                UtilityStorage value = entry.getValue();

                Pattern pattern = Pattern.compile(Pattern.quote(searchLine), Pattern.CASE_INSENSITIVE);
                boolean found = pattern.matcher(value.name).find();
                if (found) {
                    indexArray.add(key);
                }
            }
        }

        return indexArray;
    }
    private Map<Integer, UtilityStorage> utilityStorageMap = new HashMap<>();
}
