package ru.kpfu.ds.mainservice.util.jwt.extractor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.kpfu.ds.mainservice.model.dto.CurrentUserDTO;
import ru.kpfu.ds.mainservice.model.enums.TokenType;

@Component
@RequiredArgsConstructor
public class JwtExtractor {

    private final JwtDecoderFactory jwtDecoderFactory;

    public CurrentUserDTO extract(String jwt, TokenType tokenType) {
        return jwtDecoderFactory.getDecoder(jwt).verifyAndDecode(jwt, tokenType);
    }
}
