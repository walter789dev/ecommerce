package com.example.ecommerce.services;

import com.example.ecommerce.entities.Localidad;
import com.example.ecommerce.repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalidadService extends BaseService<Localidad, Long>{

    @Autowired
    private LocalidadRepository localidadRepository;

    public LocalidadService(LocalidadRepository localidadRepository){
        super(localidadRepository);
    }

    public List<Localidad> findAllByProvincia(Long idProvincia) throws Exception {
        try {
            return localidadRepository.findAllByProvinciaId(idProvincia);
        }catch (Exception e){
            throw new Exception("No existen localidades para la provincia indicada", e);
        }
    }
}
