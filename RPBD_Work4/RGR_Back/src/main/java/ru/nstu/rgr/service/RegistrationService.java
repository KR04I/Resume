package ru.nstu.rgr.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.nstu.rgr.model.Registration;
import ru.nstu.rgr.repository.RegistrationRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService  extends BasePersistentService<Registration> {

    @Getter(AccessLevel.PROTECTED)
    private final RegistrationRepository repo;

    public Registration edit(Long id, Registration registration){
        if (id == null){
            return null;
        }
        Registration registrationFromDb = repo.findById(id).orElse(null);
        if (registrationFromDb == null) return null;
        BeanUtils.copyProperties(registration, registrationFromDb, "id");
        return repo.save(registrationFromDb);
    }
}
