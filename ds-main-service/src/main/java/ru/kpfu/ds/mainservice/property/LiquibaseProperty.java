package ru.kpfu.ds.mainservice.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("spring.datasource.liquibase")
public class LiquibaseProperty {
    private String defaultSchema;
    private String changeLog;
}
