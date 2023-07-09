package ru.nstu.rgr.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.nstu.rgr.model.Visitor;
import ru.nstu.rgr.repository.VisitorRepository;

@Service
@RequiredArgsConstructor
public class VisitorService extends BasePersistentService<Visitor>{
    @Getter(AccessLevel.PROTECTED)
    private final VisitorRepository repo;

    public Visitor edit(Long id, Visitor visitor){
        if (id == null){
            return null;
        }
        Visitor visitorFromDb = repo.findById(id).orElse(null);
        if (visitorFromDb == null) return null;
        BeanUtils.copyProperties(visitor, visitorFromDb, "id");
        return repo.save(visitorFromDb);
    }
}
