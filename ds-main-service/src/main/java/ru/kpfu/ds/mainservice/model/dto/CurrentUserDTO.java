package ru.kpfu.ds.mainservice.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CurrentUserDTO {

    private UserRole role;
    private String email;
    private UUID redisId;
    private Long ownerId;

}
