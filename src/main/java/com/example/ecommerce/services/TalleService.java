package com.example.ecommerce.services;

import com.example.ecommerce.entities.Talle;
import com.example.ecommerce.repositories.TalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalleService extends BaseService<Talle, Long> {

   @Autowired
   private TalleRepository talleRepository;

   public TalleService(TalleRepository talleRepository) {
      super(talleRepository);
   }

   public List<Talle> findByNamesAlphabeticOnly() throws Exception {
      try {
         return talleRepository.findByNamesAlphabeticOnly();
      } catch (Exception e) {
         throw new Exception("No existen talles alfabeticos", e);
      }
   }

   public List<Talle> findByNamesNumericOnly() throws Exception {
      try {
         return talleRepository.findByNamesNumericOnly();
      } catch (Exception e) {
         throw new Exception("No existen talles numericos", e);
      }
   }
}
