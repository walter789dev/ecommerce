package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Provincia;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends BaseRepository<Provincia, Long>{
    List<Provincia> findAllByPaisId(Long id);
}
