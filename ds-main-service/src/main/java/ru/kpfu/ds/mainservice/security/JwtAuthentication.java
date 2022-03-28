package ru.kpfu.ds.mainservice.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class JwtAuthentication implements Authentication {

    private final String token;
    private boolean isAuthenticated;
    private CurrentUserDTO userDTO;

    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = userDTO.getRole().toString();
        return Collections.singletonList(new SimpleGrantedAuthority(userRole));
    }

    @Override
    public Object getCredentials() {
        return userDTO;
    }

    @Override
    public Object getDetails() {
        return userDTO;
    }

    @Override
    public Object getPrincipal() {
        return userDTO;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        isAuthenticated = true;
    }

    @Override
    public String getName() {
        return token;
    }
}
