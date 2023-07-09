package ru.nstu.rgr.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.nstu.rgr.model.Staff;
import ru.nstu.rgr.repository.StaffRepository;

@Service
@RequiredArgsConstructor
public class StaffService extends BasePersistentService<Staff>{

    @Getter(AccessLevel.PROTECTED)
    private final StaffRepository repo;

    public Staff edit(Long id, Staff staff){
        if (id == null){
            return null;
        }
        Staff staffFromDb = repo.findById(id).orElse(null);
        if (staffFromDb == null) return null;
        BeanUtils.copyProperties(staff, staffFromDb, "id");
        return repo.save(staffFromDb);
    }
}
