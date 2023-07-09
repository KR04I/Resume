package ru.nstu.rgr.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.nstu.rgr.model.WorkingHours;
import ru.nstu.rgr.repository.WorkingHoursRepository;

@Service
@RequiredArgsConstructor
public class WorkingHoursService extends BasePersistentService<WorkingHours>{
    @Getter(AccessLevel.PROTECTED)
    private final WorkingHoursRepository repo;

    public WorkingHours edit(Long id, WorkingHours workinghours){
        if (id == null){
            return null;
        }
        WorkingHours workinghoursFromDb = repo.findById(id).orElse(null);
        if (workinghoursFromDb == null) return null;
        BeanUtils.copyProperties(workinghours, workinghoursFromDb, "id");
        return repo.save(workinghoursFromDb);
    }
}
