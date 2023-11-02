package com.thewhite.study.config;

import com.thewhite.study.servicess.UserInteraction;
import com.thewhite.study.servicess.UtilityStorageManager;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig  {
    @Value("${data.file.path}")
    private String filePath;

    @PostConstruct
    public void init() {
        UserInteraction userInteraction = new UserInteraction();
    }

    @Bean
    public UtilityStorageManager utilityStorageManagerBean(){
        UtilityStorageManager utilityStorageManager = new UtilityStorageManager();
        utilityStorageManager.readData(filePath);
        return utilityStorageManager;
    }
}