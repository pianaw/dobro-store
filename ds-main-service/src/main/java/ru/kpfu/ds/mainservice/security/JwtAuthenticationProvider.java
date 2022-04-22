package ru.kpfu.ds.mainservice.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.exception.DobrostoreTokenExpiredException;
import ru.kpfu.ds.mainservice.model.exception.DobrostoreTokenVerificationException;
import ru.kpfu.ds.mainservice.util.jwt.extractor.JwtDecoderFactory;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtDecoderFactory jwtDecoderFactory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;

        try {
            String jwt = jwtAuthentication.getName();
            CurrentUserDTO userDTO = jwtDecoderFactory.extract(jwt, jwtAuthentication.getTokenType());
            jwtAuthentication.setAuthenticated(true);
            jwtAuthentication.setUserDTO(userDTO);
        } catch (TokenExpiredException e) {
            throw new DobrostoreTokenExpiredException("Token is expired");
        } catch (JWTVerificationException e) {
            throw new DobrostoreTokenVerificationException("Token is not verified");
        }

        return jwtAuthentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthentication.class.equals(aClass);
    }
}
