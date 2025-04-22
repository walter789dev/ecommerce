package com.example.ecommerce.services;

import com.example.ecommerce.entities.PrecioProducto;
import com.example.ecommerce.repositories.PrecioProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class PrecioProductoService extends BaseService<PrecioProducto, Long>{
    public PrecioProductoService(PrecioProductoRepository precioRepository){
        super(precioRepository);
    }
}
