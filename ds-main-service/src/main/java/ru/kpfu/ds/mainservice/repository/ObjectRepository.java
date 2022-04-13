package ru.kpfu.ds.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.ds.mainservice.model.entity.Object;

public interface ObjectRepository extends JpaRepository<Object, Long> {
}
