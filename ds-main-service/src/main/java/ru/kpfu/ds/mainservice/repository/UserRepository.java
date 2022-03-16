package ru.kpfu.ds.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.ds.mainservice.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
