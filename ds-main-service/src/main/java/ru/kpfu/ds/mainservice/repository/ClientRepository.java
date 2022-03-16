package ru.kpfu.ds.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.ds.mainservice.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
