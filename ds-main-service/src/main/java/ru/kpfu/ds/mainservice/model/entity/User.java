package ru.kpfu.ds.mainservice.model.entity;

import lombok.Getter;
import lombok.Setter;
import ru.kpfu.ds.mainservice.model.enums.UserRole;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column
    private String email;

    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "redis_id")
    private UUID redisId;
}
