package com.example.ecommerce.services;

import com.example.ecommerce.entities.Base;
import com.example.ecommerce.repositories.BaseRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<E extends Base, ID extends Serializable> {
    protected BaseRepository<E, ID> baseRepository;

    public BaseService(BaseRepository<E, ID> baseRepository){
        this.baseRepository = baseRepository;
    }

    public List<E> getAll() throws Exception {
        return baseRepository.findAll();
    }

    public Optional<E> getById(ID id) throws Exception{
        return baseRepository.findById(id);
    }

    public E create(E entity) throws Exception{
        return baseRepository.save(entity);
    }

    public E update(E entity) throws Exception{
        return baseRepository.save(entity);
    }

    public void delete(ID id){
        baseRepository.deleteById(id);
    }
}
