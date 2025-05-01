package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Direccion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DireccionRepository extends BaseRepository<Direccion, Long> {
    List<Direccion> findAllByLocalidadId(Long id);
}
