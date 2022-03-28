package ru.kpfu.ds.mainservice.util.jwt.extractor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtExtractor {

    private final Map<UserRole, JwtDecoder> jwtDecoderMap;

    public JwtExtractor(List<JwtDecoder> jwtDecoders) {
        jwtDecoderMap = jwtDecoders.stream()
                .collect(Collectors.toMap(JwtDecoder::getUserRole, Function.identity()));
    }

    public CurrentUserDTO extract(String jwt) {
        return getDecoder(jwt).verifyAndDecode(jwt);
    }

    private JwtDecoder getDecoder(String jwt) {
        DecodedJWT decodedJWT = JWT.decode(jwt);

        UserRole role = UserRole.valueOf(decodedJWT.getClaim("role").asString());
        return jwtDecoderMap.get(role);
    }
}
