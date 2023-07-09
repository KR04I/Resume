package ru.nstu.rgr.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.nstu.rgr.model.Passport;
import ru.nstu.rgr.repository.PassportRepository;

@Service
@RequiredArgsConstructor
public class PassportService extends BasePersistentService<Passport>
{


        @Getter(AccessLevel.PROTECTED)
        private final PassportRepository repo;

        public Passport edit(Long id, Passport passport){
            if (id == null){
                return null;
            }
            Passport passportFromDb = repo.findById(id).orElse(null);
            if (passportFromDb == null) return null;
            BeanUtils.copyProperties(passport, passportFromDb, "id");
            return repo.save(passportFromDb);
        }
}

