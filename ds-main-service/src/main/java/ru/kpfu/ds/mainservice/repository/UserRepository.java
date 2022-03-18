package ru.kpfu.ds.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.ds.mainservice.model.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
