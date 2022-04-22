package ru.kpfu.ds.mainservice.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "ru.kpfu.ds.mainservice.model.entity")
@EnableJpaRepositories(basePackages = "ru.kpfu.ds.mainservice.repository")
@ComponentScan(basePackages = "ru.kpfu.ds.mainservice.service")
public class AppConfig {
}
