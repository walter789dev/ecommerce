package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Imagen;
import com.example.ecommerce.services.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/imagenes")
public class ImagenController extends BaseController<Imagen, Long> {
   public ImagenController(ImagenService imagenService) {
      super(imagenService);
   }

}
