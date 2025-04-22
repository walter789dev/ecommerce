package com.example.ecommerce.services;

import com.example.ecommerce.entities.Categoria;
import com.example.ecommerce.repositories.CategoriaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService extends BaseService<Categoria, Long>{
    public CategoriaService(CategoriaRepository categoriaRepository){
        super(categoriaRepository);
    }
}
