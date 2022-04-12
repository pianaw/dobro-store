package ru.kpfu.ds.mainservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import ru.kpfu.ds.mainservice.model.enums.ClientLevel;
import ru.kpfu.ds.mainservice.model.enums.ObjectType;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    private List<Object> objects;
}
