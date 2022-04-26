package ru.kpfu.ds.mainservice.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class FullClientDTO extends BaseClientDTO {

    private UserDTO user;
}
