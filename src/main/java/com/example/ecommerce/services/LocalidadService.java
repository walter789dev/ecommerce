package com.example.ecommerce.services;

import com.example.ecommerce.entities.Localidad;
import com.example.ecommerce.repositories.LocalidadRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalidadService extends BaseService<Localidad, Long>{
    public LocalidadService(LocalidadRepository localidadRepository){
        super(localidadRepository);
    }
}
