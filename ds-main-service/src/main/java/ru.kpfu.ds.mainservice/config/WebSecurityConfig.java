package ru.kpfu.ds.mainservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final List<String> PERMITTED_URIS = List.of(
            "/v1/ui/**",
            "/**/swagger-ui.html",
            "/**/swagger-ui/**",
            "/**/v3/api-docs/**"
    );

    @Override
    public void configure(HttpSecurity http) throws Exception {
        var authorizeRequests = new LinkedList<>(PERMITTED_URIS);

        http.csrf().disable()
                .authorizeRequests().antMatchers(authorizeRequests.toArray(new String[0]))
                .permitAll();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
