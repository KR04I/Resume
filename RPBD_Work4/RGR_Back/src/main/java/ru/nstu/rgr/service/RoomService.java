package ru.nstu.rgr.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.nstu.rgr.model.Room;
import ru.nstu.rgr.repository.RoomRepository;


@Service
@RequiredArgsConstructor
public class RoomService extends BasePersistentService<Room>{

    @Getter(AccessLevel.PROTECTED)
    private final RoomRepository repo;

    public Room edit(Long id, Room room){
        if (id == null){
            return null;
        }
        Room roomFromDb = repo.findById(id).orElse(null);
        if (roomFromDb == null) return null;
        BeanUtils.copyProperties(room, roomFromDb, "id");
        return repo.save(roomFromDb);
    }
}
