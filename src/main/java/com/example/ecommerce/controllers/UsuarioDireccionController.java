package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.UsuarioDireccion;
import com.example.ecommerce.services.UsuarioDireccionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios_direccion")
public class UsuarioDireccionController extends BaseController<UsuarioDireccion, Long>{
    public UsuarioDireccionController(UsuarioDireccionService usuarioDireccionService){
        super(usuarioDireccionService);
    }
}
