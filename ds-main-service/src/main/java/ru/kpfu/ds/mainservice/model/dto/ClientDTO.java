package ru.kpfu.ds.mainservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.ds.mainservice.model.enums.ClientLevel;

@Data
@Getter
@Setter
public class ClientDTO {

    private String firstName;
    private String lastName;
    private String patronymic;
    private ClientLevel clientLevel;
}
