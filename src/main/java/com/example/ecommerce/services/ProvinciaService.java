package com.example.ecommerce.services;

import com.example.ecommerce.entities.Provincia;
import com.example.ecommerce.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService extends BaseService<Provincia, Long> {

   @Autowired
   private ProvinciaRepository provinciaRepository;

   public ProvinciaService(ProvinciaRepository provinciaRepository) {
      super(provinciaRepository);
   }
}
