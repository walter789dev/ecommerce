package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Usuario;
import com.example.ecommerce.services.UsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController extends BaseController<Usuario, Long>{
    public UsuarioController(UsuarioService usuarioService){
        super(usuarioService);
    }
}
