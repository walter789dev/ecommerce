package com.example.ecommerce.services;

import com.example.ecommerce.entities.DetalleProducto;
import com.example.ecommerce.entities.Producto;
import com.example.ecommerce.repositories.DetalleProductoRepository;
import com.example.ecommerce.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService extends BaseService<Producto, Long> {
   public ProductoService(ProductoRepository productoRepository) {
      super(productoRepository);
   }
}
