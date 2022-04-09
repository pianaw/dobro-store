package ru.kpfu.ds.mainservice.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.kpfu.ds.mainservice.model.enums.UserRole;
import ru.kpfu.ds.mainservice.security.filter.AccessTokenFilter;
import ru.kpfu.ds.mainservice.security.JwtAuthenticationProvider;
import ru.kpfu.ds.mainservice.security.filter.RefreshTokenFilter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ds.app.auth.jwt.moderator.secret}")
    private String moderatorSecret;

    @Value("${ds.app.auth.jwt.admin.secret}")
    private String adminSecret;

    @Value("${ds.app.auth.jwt.client.secret}")
    private String clientSecret;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    private static final List<String> PERMITTED_URIS = List.of(
            "/api/v1/auth",
            "/api/v1/ui/**",
            "/**/swagger-ui.html",
            "/**/swagger-ui/**",
            "/**/v3/api-docs/**"
    );

    @Override
    public void configure(HttpSecurity http) throws Exception {
        var authorizeRequests = new LinkedList<>(PERMITTED_URIS);

        http.csrf().disable()
                .authorizeRequests().antMatchers(authorizeRequests.toArray(new String[0])).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/user/client/**").hasAnyAuthority(UserRole.CLIENT.name()).accessDecisionManager(accessDecisionManager())
                .antMatchers("/api/v1/user/admin/**").hasAnyAuthority(UserRole.ADMIN.name()).accessDecisionManager(accessDecisionManager())
                .antMatchers("/api/v1/user/moderator/**").hasAnyAuthority(UserRole.MODERATOR.name()).accessDecisionManager(accessDecisionManager())
                .anyRequest().authenticated()
                .and()
                .addFilterAt(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(refreshTokenFilter(), AccessTokenFilter.class);


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

    @Bean
    public FilterRegistrationBean<AccessTokenFilter> authAccessTokenFilter() {
        FilterRegistrationBean<AccessTokenFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(accessTokenFilter());
        registrationBean.addUrlPatterns("/api/v1/user/**");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RefreshTokenFilter> authRefrbeshTokenFilter() {
        FilterRegistrationBean<RefreshTokenFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(refreshTokenFilter());
        registrationBean.addUrlPatterns("/api/v1/auth/refresh");

        return registrationBean;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(jwtAuthenticationProvider);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AccessTokenFilter accessTokenFilter() {
        return new AccessTokenFilter();
    }

    @Bean
    public RefreshTokenFilter refreshTokenFilter() {
        return new RefreshTokenFilter();
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<? extends Object>> decisionVoters
                = Arrays.asList(
                        new WebExpressionVoter(),
                new RoleVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }
}
