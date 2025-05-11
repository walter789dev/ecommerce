package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Pais;
import com.example.ecommerce.services.PaisService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paises")
public class PaisController extends BaseController<Pais, Long>{
    public PaisController(PaisService paisService){
        super(paisService);
    }
}
