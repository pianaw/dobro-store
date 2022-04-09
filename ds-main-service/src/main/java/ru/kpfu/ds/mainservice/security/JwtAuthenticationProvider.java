package ru.kpfu.ds.mainservice.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.constant.Constant;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.TokenType;
import ru.kpfu.ds.mainservice.util.jwt.extractor.JwtExtractor;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtExtractor jwtExtractor;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

        try {
            String jwt = jwtAuthentication.getName();
            CurrentUserDTO userDTO = jwtExtractor.extract(jwt, jwtAuthentication.getTokenType());
            jwtAuthentication.setAuthenticated(true);
            jwtAuthentication.setUserDTO(userDTO);
        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException(e);
        }

        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthentication.class.equals(aClass);
    }
}
