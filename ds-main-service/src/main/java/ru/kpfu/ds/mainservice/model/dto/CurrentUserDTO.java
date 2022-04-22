package ru.kpfu.ds.mainservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUserDTO {

    @JsonIgnore
    private UUID jti;

    @JsonIgnore
    private UUID redisId;

    private Long ownerId;
    private UserRole role;
    private String email;
}
