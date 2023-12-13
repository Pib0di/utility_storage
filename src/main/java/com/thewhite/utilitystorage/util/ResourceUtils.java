package com.thewhite.utilitystorage.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

@UtilityClass
public class ResourceUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    public static <T> T parseJson(String resourcePath, Class<T> clazz) {
        ClassPathResource resource = new ClassPathResource(resourcePath);

        return mapper.readValue(resource.getInputStream(), clazz);
    }

    @SneakyThrows
    public static <T> T parseJson(String resourcePath, TypeReference<T> typeReference) {
        ClassPathResource resource = new ClassPathResource(resourcePath);

        return mapper.readValue(resource.getInputStream(), typeReference);
    }
}
