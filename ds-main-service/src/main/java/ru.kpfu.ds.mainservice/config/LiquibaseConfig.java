package ru.kpfu.ds.mainservice.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import ru.kpfu.ds.mainservice.property.LiquibaseProperty;
import ru.kpfu.ds.mainservice.service.SchemaCreatorService;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {

    @Bean
    @DependsOn("SchemaCreatorService")
    public SpringLiquibase liquibase(LiquibaseProperty liquibaseProperties, DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setChangeLog(liquibaseProperties.getChangeLog());
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        return springLiquibase;
    }

    @Bean("SchemaCreatorService")
    public SchemaCreatorService schemaCreatorService(LiquibaseProperty liquibaseProperties, DataSource dataSource) {
        return new SchemaCreatorService(liquibaseProperties.getDefaultSchema(), dataSource);
    }
}
