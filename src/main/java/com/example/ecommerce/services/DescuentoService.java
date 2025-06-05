package com.example.ecommerce.services;

import com.example.ecommerce.entities.Descuento;
import com.example.ecommerce.repositories.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DescuentoService extends BaseService<Descuento, Long> {

   @Autowired
   private DescuentoRepository descuentoRepository;

   public DescuentoService(DescuentoRepository descuentoRepository) {
      super(descuentoRepository);
   }

   public List<Descuento> findAllByFecha() throws Exception {
      try {
         Date now = new Date();
         return descuentoRepository.findAllByFechaInicioBeforeAndFechaFinAfter(now, now);
      } catch (Exception e) {
         throw new Exception("No hay descuentos disponibles", e);
      }
   }

   public List<Descuento> findAllByPorcentaje(double porcentaje) throws Exception {
      try {
         return descuentoRepository.findAllByPorcentaje(porcentaje);
      } catch (Exception e) {
         throw new Exception("No hay descuentos del porcentaje solicitado", e);
      }
   }

   @Override
   @Transactional
   public void delete(Long id) throws Exception {
      try {
         Optional<Descuento> optional = descuentoRepository.findById(id);
         if (optional.isPresent()) {
            Descuento descuento = optional.get();
            descuento.setActivo(!descuento.isActivo());
            descuentoRepository.save(descuento);
         } else {
            throw new Exception("Descuento no encontrado");
         }
      } catch (Exception e) {
         throw new Exception(e.getMessage());
      }
   }
}
