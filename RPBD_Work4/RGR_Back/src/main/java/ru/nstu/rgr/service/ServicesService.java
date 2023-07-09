package ru.nstu.rgr.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.nstu.rgr.model.Services;
import ru.nstu.rgr.repository.ServicesRepository;

@Service
@RequiredArgsConstructor
public class ServicesService extends BasePersistentService<Services>{

    @Getter(AccessLevel.PROTECTED)
    private final ServicesRepository repo;

    public Services edit(Long id, Services services){
        if (id == null){
            return null;
        }
        Services servicesFromDb = repo.findById(id).orElse(null);
        if (servicesFromDb == null) return null;
        BeanUtils.copyProperties(services, servicesFromDb, "id");
        return repo.save(servicesFromDb);
    }
}
