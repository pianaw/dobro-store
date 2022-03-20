package ru.kpfu.ds.mainservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientFormDTO {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String patronymic;
}
