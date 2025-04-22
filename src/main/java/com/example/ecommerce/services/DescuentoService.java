package com.example.ecommerce.services;

import com.example.ecommerce.entities.Descuento;
import com.example.ecommerce.repositories.DescuentoRepository;
import org.springframework.stereotype.Service;

@Service
public class DescuentoService extends BaseService<Descuento, Long>{
    public DescuentoService(DescuentoRepository descuentoRepository){
        super(descuentoRepository);
    }
}
