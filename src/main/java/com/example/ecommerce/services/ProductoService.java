package com.example.ecommerce.services;

import com.example.ecommerce.entities.Producto;
import com.example.ecommerce.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService extends BaseService<Producto, Long> {

   @Autowired
   private ProductoRepository productoRepository;

   public ProductoService(ProductoRepository productoRepository) {
      super(productoRepository);
   }
}
