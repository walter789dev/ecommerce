package com.example.ecommerce.services;

import com.example.ecommerce.entities.Direccion;
import com.example.ecommerce.repositories.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionService extends BaseService<Direccion, Long>{

    @Autowired
    private DireccionRepository direccionRepository;

    public DireccionService(DireccionRepository direccionRepository){
        super(direccionRepository);
    }

    public List<Direccion> findAllByLocalidad(Long idLocalidad) throws Exception {
        try {
            return direccionRepository.findAllByLocalidadId(idLocalidad);
        }catch (Exception e){
            throw new Exception("No hay direcciones para la localidad indicada", e);
        }
    }
}
