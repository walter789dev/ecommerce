package com.example.ecommerce.services;

import com.example.ecommerce.entities.Precio;
import com.example.ecommerce.repositories.PrecioRepository;
import org.springframework.stereotype.Service;

@Service
public class PrecioService extends BaseService<Precio, Long> {
   public PrecioService(PrecioRepository precioRepository) {
      super(precioRepository);
   }
}
