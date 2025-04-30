package com.example.ecommerce.services;

import com.example.ecommerce.entities.Provincia;
import com.example.ecommerce.repositories.ProvinciaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService extends BaseService<Provincia, Long>{
    public ProvinciaService(ProvinciaRepository provinciaRepository){
        super(provinciaRepository);
    }
}
