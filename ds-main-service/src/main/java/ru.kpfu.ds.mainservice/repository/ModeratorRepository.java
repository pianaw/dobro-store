package ru.kpfu.ds.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.ds.mainservice.model.entity.Moderator;

public interface ModeratorRepository extends JpaRepository<Moderator, Long> {
}
