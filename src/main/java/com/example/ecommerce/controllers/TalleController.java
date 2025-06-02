package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Talle;
import com.example.ecommerce.services.TalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/talles")
public class TalleController extends BaseController<Talle, Long> {

   @Autowired
   private TalleService talleService;

   public TalleController(TalleService talleService) {
      super(talleService);
   }

   @GetMapping("/alfabetico")
   public ResponseEntity<List<Talle>> findByNamesAlphabeticOnly() throws Exception {
      try {
         List<Talle> talles = talleService.findByNamesAlphabeticOnly();
         return ResponseEntity.ok(talles);
      } catch (Exception e) {
         throw new Exception("No existen talles alfabeticos", e);
      }
   }

   @GetMapping("/numerico")
   public ResponseEntity<List<Talle>> findByNamesNumericOnly() throws Exception {
      try {
         List<Talle> talles = talleService.findByNamesNumericOnly();
         return ResponseEntity.ok(talles);
      } catch (Exception e) {
         throw new Exception("No existen talles numericos", e);
      }
   }
}
