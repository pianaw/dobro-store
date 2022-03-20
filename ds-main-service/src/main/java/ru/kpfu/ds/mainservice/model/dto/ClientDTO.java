package ru.kpfu.ds.mainservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.ds.mainservice.model.enums.ClientLevel;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO {
    private String firstName;
    private String lastName;
    private String patronymic;
    private ClientLevel clientLevel;
}
