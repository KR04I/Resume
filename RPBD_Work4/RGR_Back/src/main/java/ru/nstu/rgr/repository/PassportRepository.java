package ru.nstu.rgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nstu.rgr.model.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {
}
