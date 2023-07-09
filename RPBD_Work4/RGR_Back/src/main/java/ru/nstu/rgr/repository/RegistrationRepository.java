package ru.nstu.rgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nstu.rgr.model.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
