package com.example.ecommerce.controllers;

import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.services.CategoriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController extends BaseController<Categoria, Long>{
    public CategoriaController(CategoriaService categoriaService){
        super(categoriaService);
    }
}
