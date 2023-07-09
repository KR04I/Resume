package ru.nstu.rgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nstu.rgr.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
