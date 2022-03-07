package ru.kpfu.ds.mainservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.ds.mainservice.model.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
