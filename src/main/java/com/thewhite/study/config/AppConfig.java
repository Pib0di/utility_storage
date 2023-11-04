package com.thewhite.study.config;

import com.thewhite.study.servicess.UtilityStorageManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UtilityStorageManager utilityStorageManagerBean(@Value("${data.file.path}") String filePath) {
        UtilityStorageManager utilityStorageManager = new UtilityStorageManager();
        utilityStorageManager.readData(filePath);
        return utilityStorageManager;
    }
}