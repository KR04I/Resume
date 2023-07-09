package ru.nstu.rgr.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BasePersistentService<T> {

    protected abstract <Repo extends JpaRepository<T, Long>> Repo getRepo();

    public T save(T entity){
        return getRepo().save(entity);
    }

    public List<T> findAll(){
        return getRepo().findAll();
    }

    public void delete(Long id){
        getRepo().deleteById(id);
    }
}
