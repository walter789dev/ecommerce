package com.example.ecommerce.services;

import com.example.ecommerce.entities.UsuarioDireccion;
import com.example.ecommerce.repositories.UsuarioDireccionRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDireccionService extends BaseService<UsuarioDireccion, Long>{
    public UsuarioDireccionService(UsuarioDireccionRepository usuarioDireccionRepository){
        super(usuarioDireccionRepository);
    }
}
