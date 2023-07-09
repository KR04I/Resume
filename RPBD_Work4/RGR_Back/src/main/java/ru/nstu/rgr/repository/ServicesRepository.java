package ru.nstu.rgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nstu.rgr.model.Services;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {
}
