package com.example.ecommerce.repositories;

import com.example.ecommerce.entities.Descuento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DescuentoRepository extends BaseRepository<Descuento, Long> {
   List<Descuento> findAllByFechaInicioBeforeAndFechaFinAfter(Date inicio, Date fin);

   List<Descuento> findAllByPorcentaje(double porcentaje);
}
