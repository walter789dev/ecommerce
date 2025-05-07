package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Base;
import com.example.ecommerce.services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseController<E extends Base, ID extends Serializable> {
    protected BaseService<E, ID> baseService;

    public BaseController(BaseService<E, ID> baseService){
        this.baseService = baseService;
    }

    @GetMapping()
    public ResponseEntity<List<E>> getAll() throws Exception {
        List<E> entities = baseService.getAll();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    public Optional<E> getById(@PathVariable ID id) throws Exception {
        return baseService.getById(id);
    }

    @PostMapping()
    public ResponseEntity<E> create(@RequestBody E entity) throws Exception {
        E entidadCreada = baseService.create(entity);
        return ResponseEntity.ok(entidadCreada);
    }

    @PutMapping()
    public ResponseEntity<E> update(@RequestBody E entity) throws Exception {
        E entidadAct = baseService.update(entity);
        return ResponseEntity.ok(entidadAct);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable ID id) throws Exception {
        baseService.delete(id);
    }
}
