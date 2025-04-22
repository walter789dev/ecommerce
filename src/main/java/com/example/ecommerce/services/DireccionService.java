package com.example.ecommerce.services;

import com.example.ecommerce.entities.Direccion;
import com.example.ecommerce.repositories.DireccionRepository;
import org.springframework.stereotype.Service;

@Service
public class DireccionService extends BaseService<Direccion, Long>{
    public DireccionService(DireccionRepository direccionRepository){
        super(direccionRepository);
    }
}
