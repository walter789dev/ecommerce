package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Provincia;
import com.example.ecommerce.services.ProvinciaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "provincias")
public class ProvinciaController extends BaseController<Provincia, Long>{
    public ProvinciaController(ProvinciaService provinciaService){
        super(provinciaService);
    }
}
