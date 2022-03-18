package ru.kpfu.ds.mainservice.config;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ds.app.auth.jwt.moderator.secret}")
    private String moderatorSecret;

    @Value("${ds.app.auth.jwt.admin.secret}")
    private String adminSecret;

    @Value("${ds.app.auth.jwt.client.secret}")
    private String clientSecret;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Algorithm clientAlgorithm() {
        return Algorithm.HMAC256(clientSecret);
    }

    @Bean
    public Algorithm adminAlgorithm() {
        return Algorithm.HMAC256(adminSecret);
    }

    @Bean
    public Algorithm moderatorAlgorithm() {
        return Algorithm.HMAC256(moderatorSecret);
    }
}
