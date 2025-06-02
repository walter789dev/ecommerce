package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Talle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalleRepository extends BaseRepository<Talle, Long> {
   @Query(value = "SELECT * FROM talles e WHERE e.name REGEXP '^[A-Za-z]+$'", nativeQuery = true)
   List<Talle> findByNamesAlphabeticOnly();

   @Query(value = "SELECT * FROM talles e WHERE e.name REGEXP '^[0-9]+$'", nativeQuery = true)
   List<Talle> findByNamesNumericOnly();
}
