package com.example.ecommerce.services;

import com.example.ecommerce.dto.DetalleColoresDTO;
import com.example.ecommerce.dto.DetalleProductoFiltroDTO;
import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.repositories.DetalleProductoRepository;
import com.example.ecommerce.specification.DetalleProductoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleProductoService extends BaseService<DetalleProducto, Long> {

   @Autowired
   private DetalleProductoRepository detalleProductoRepository;

   public DetalleProductoService(DetalleProductoRepository detalleProductoRepository) {
      super(detalleProductoRepository);
   }

   @Override
   public List<DetalleProducto> getAll() throws Exception {
      try {
         return detalleProductoRepository.findAllActive();
      } catch (Exception e) {
         throw new Exception(e.getMessage());
      }
   }

   @Override
   @Transactional
   public void delete(Long id) throws Exception {
      try {
         Optional<DetalleProducto> optional = detalleProductoRepository.findById(id);
         if (optional.isPresent()) {
            DetalleProducto detalleProducto = optional.get();
            detalleProducto.setActivo(!detalleProducto.isActivo());
            detalleProductoRepository.save(detalleProducto);
         } else {
            throw new Exception("Producto no encontrado");
         }
      } catch (Exception e) {
         throw new Exception(e.getMessage());
      }
   }

   public List<DetalleColoresDTO> getAllColoresByProductoId(Long productoId) {
      return detalleProductoRepository.findColoresAndIdsByProductoId(productoId);
   }

   public List<DetalleProducto> getAllProductsFilter(DetalleProductoFiltroDTO filtro) {
      return detalleProductoRepository.findAll(DetalleProductoSpecification.filterDetalles(filtro));
   }

   public List<DetalleProducto> getAllDetallesByProductoId(Long productoId) {
      return detalleProductoRepository.findAllByProductoIdAndActivo(productoId);
   }
}
