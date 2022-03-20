package ru.kpfu.ds.mainservice.model.entity;

import lombok.*;
import ru.kpfu.ds.mainservice.model.enums.ClientLevel;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String patronymic;

    @Column(name = "level")
    private ClientLevel clientLevel;
}
