package com.example.ecommerce.services;

import com.example.ecommerce.entities.Descuento;
import com.example.ecommerce.repositories.DescuentoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DescuentoService extends BaseService<Descuento, Long>{

    @Autowired
    private DescuentoRepository descuentoRepository;

    public DescuentoService(DescuentoRepository descuentoRepository){
        super(descuentoRepository);
    }

    public List<Descuento> findAllByFecha() throws Exception {
        try {
            Date now = new Date();
            return descuentoRepository.findAllByFechaInicioBeforeAndFechaFinAfter(now, now);
        }catch (Exception e){
            throw new Exception("No hay descuentos disponibles", e);
        }
    }

   public List<Descuento> findAllByPorcentaje(double porcentaje) throws Exception {
        try {
            return descuentoRepository.findAllByPorcentaje(porcentaje);
        }catch (Exception e){
            throw new Exception("No hay descuentos del porcentaje solicitado", e);
        }
   }
}
