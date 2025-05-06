package com.example.ecommerce.services;

import com.example.ecommerce.entities.Provincia;
import com.example.ecommerce.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService extends BaseService<Provincia, Long>{

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public ProvinciaService(ProvinciaRepository provinciaRepository){
        super(provinciaRepository);
    }

    public List<Provincia> findAllByPais(Long idPais) throws Exception {
        try {
            return provinciaRepository.findAllByPaisId(idPais);
        }catch (Exception e){
            throw new Exception("No existen localidades del pais indicado", e);
        }
    }
}
