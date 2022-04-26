package ru.kpfu.ds.mainservice.util.bcrypt;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Named("BCryptUtil")
@RequiredArgsConstructor
public class BCryptUtil {

    private final PasswordEncoder passwordEncoder;

    @Named("generateHashPassword")
    public String generateHashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean match(String password, String hash) {
        return !passwordEncoder.matches(password, hash);
    }
}
