package ru.kpfu.ds.mainservice.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.kpfu.ds.mainservice.model.constant.Constant;
import ru.kpfu.ds.mainservice.model.enums.TokenType;
import ru.kpfu.ds.mainservice.security.JwtAuthentication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RefreshTokenFilter extends OncePerRequestFilter {

    private final TokenType tokenType = TokenType.REFRESH_TOKEN;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(tokenType.getName());

        if (token != null) {
            token = token.replaceAll(Constant.BEARER + " ", "");
            JwtAuthentication jwtAuthentication = new JwtAuthentication(token, tokenType);
            authenticationManager.authenticate(jwtAuthentication);
            SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
        }

        filterChain.doFilter(request, response);
    }
}
