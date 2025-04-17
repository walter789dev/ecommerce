package com.example.ecommerce.services;

import com.example.ecommerce.entities.Talle;
import com.example.ecommerce.repositories.TalleRepository;
import org.springframework.stereotype.Service;

@Service
public class TalleService extends BaseService<Talle, Long>{
    public TalleService(TalleRepository talleRepository) {
        super(talleRepository);
    }
}
