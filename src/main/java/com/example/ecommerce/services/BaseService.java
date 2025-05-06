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
        try {
            return baseRepository.findAll();
        }catch (Exception e){
            throw new Exception("No hay entidades disponibles", e);
        }
    }

    public Optional<E> getById(ID id) throws Exception{
        try {
            return baseRepository.findById(id);
        }catch (Exception e){
            throw new Exception("No existe la entidad solicitada", e);
        }
    }


    public E create(E entity) throws Exception{
        try {
            return baseRepository.save(entity);
        }catch (Exception e){
            throw new Exception("No se puede crear la entidad", e);
        }
    }

    public E update(E entity, Long id) throws Exception{
        try {
            return baseRepository.save(entity);
        } catch (Exception e) {
            throw new Exception("No se puede actualizar la entidad", e);
        }
    }

    public void delete(ID id) throws Exception{
        try {
            baseRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("No se puede eliminar la entidad", e);
        }
    }
}
