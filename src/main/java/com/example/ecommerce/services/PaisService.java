package com.example.ecommerce.services;

import com.example.ecommerce.entities.Pais;
import com.example.ecommerce.repositories.PaisRepository;
import org.springframework.stereotype.Service;

@Service
public class PaisService extends BaseService<Pais, Long>{
    public PaisService(PaisRepository paisRepository){
        super(paisRepository);
    }
}
