package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Provincia;
import com.example.ecommerce.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/provincias")
public class ProvinciaController extends BaseController<Provincia, Long> {

   @Autowired
   private ProvinciaService provinciaService;

   public ProvinciaController(ProvinciaService provinciaService) {
      super(provinciaService);
   }
}
