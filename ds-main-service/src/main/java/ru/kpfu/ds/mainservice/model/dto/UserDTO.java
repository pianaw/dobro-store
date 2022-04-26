package ru.kpfu.ds.mainservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private UserEmailPasswordDTO credentials;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserRole role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long ownerId;
}
