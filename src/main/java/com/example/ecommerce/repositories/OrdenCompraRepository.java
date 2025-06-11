package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.OrdenCompra;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenCompraRepository extends BaseRepository<OrdenCompra, Long> {
   List<OrdenCompra> findAllByUsuarioId(Long id);

   @Query("SELECT oc FROM OrdenCompra oc WHERE oc.usuario.id = :id ORDER BY oc.id DESC LIMIT 1")
   OrdenCompra findByUsuarioId(Long id);
}
