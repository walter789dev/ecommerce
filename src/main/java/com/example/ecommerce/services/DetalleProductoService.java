package com.example.ecommerce.services;

import com.example.ecommerce.dto.DetalleProductoFiltroDTO;
import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.Stock;
import com.example.ecommerce.repositories.DetalleProductoRepository;
import com.example.ecommerce.specification.DetalleProductoSpecification;
import jakarta.persistence.EntityNotFoundException;
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

   public List<DetalleProducto> findAllActive() throws Exception {
      try {
         return detalleProductoRepository.findAllActive();
      } catch (Exception e) {
         throw new Exception(e.getMessage());
      }
   }

   public List<String> getAllColoresByProductoId(Long productoId) {
      return detalleProductoRepository.findColoresByProductoId(productoId);
   }

   public List<DetalleProducto> getAllProductsFilter(DetalleProductoFiltroDTO filtro) {
      return detalleProductoRepository.findAll(DetalleProductoSpecification.filterDetalles(filtro));
   }

   public List<Stock> getAllStocksByDetalleProducto(Long detalleProductoId) {
      DetalleProducto detalleProducto = detalleProductoRepository.findById(detalleProductoId)
            .orElseThrow(() -> new EntityNotFoundException("DetalleProducto no encontrado"));
      return detalleProducto.getStocks();
   }

   @Override
   @Transactional
   public void delete(Long id) throws Exception {
      try {
         Optional<DetalleProducto> optional = detalleProductoRepository.findById(id);
         if (optional.isPresent()) {
            DetalleProducto detalleProducto = optional.get();
            detalleProducto.setActivo(false);
            detalleProductoRepository.save(detalleProducto);
         } else {
            throw new Exception("Producto no encontrado");
         }
      } catch (Exception e) {
         throw new Exception(e.getMessage());
      }
   }
}
