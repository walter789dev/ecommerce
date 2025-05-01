package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Localidad;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadRepository extends BaseRepository<Localidad, Long>{
    List<Localidad> findAllByProvinciaId(Long id);
}
