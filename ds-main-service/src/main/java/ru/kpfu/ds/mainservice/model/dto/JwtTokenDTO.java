package ru.kpfu.ds.mainservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtTokenDTO {

    private String accessToken;
    private String refreshToken;
}
