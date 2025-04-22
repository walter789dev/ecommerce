package com.example.ecommerce.services;

import com.example.ecommerce.entities.Detalle;
import com.example.ecommerce.repositories.DetalleRepository;
import org.springframework.stereotype.Service;

@Service
public class DetalleService extends BaseService<Detalle, Long>{
    public DetalleService(DetalleRepository detalleRepository){
        super(detalleRepository);
    }
}
