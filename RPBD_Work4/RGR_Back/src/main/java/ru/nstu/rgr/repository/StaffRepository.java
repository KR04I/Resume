package ru.nstu.rgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nstu.rgr.model.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
