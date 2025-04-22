package com.example.ecommerce.services;

import com.example.ecommerce.entities.OrdenCompra;
import com.example.ecommerce.repositories.OrdenCompraRepository;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraService extends BaseService<OrdenCompra, Long>{
    public OrdenCompraService(OrdenCompraRepository ordenCompraRepository){
        super(ordenCompraRepository);
    }
}
