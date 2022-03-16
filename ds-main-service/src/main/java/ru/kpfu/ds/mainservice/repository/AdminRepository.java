package ru.kpfu.ds.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.ds.mainservice.model.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
