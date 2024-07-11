package com.wwun.springboot.seguroauto.springboot_seguroauto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wwun.springboot.seguroauto.springboot_seguroauto.entities.Cotizacion;
import com.wwun.springboot.seguroauto.springboot_seguroauto.repositories.CotizacionRepository;

@Service
public class CotizacionServiceImpl implements CotizacionService{

    @Autowired
    CotizacionRepository cotizacionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Cotizacion> findById(int id) {
        return cotizacionRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cotizacion> findAll() {
        return (List<Cotizacion>)cotizacionRepository.findAll();
    }

    @Override
    public Optional<Cotizacion> delete(int id) {
        Optional<Cotizacion> cotizacionOptional = cotizacionRepository.findById(id);
        cotizacionOptional.ifPresent((cotizacionDb) -> {
            cotizacionRepository.delete(cotizacionDb);
        });
        return cotizacionOptional;
    }

    @Override
    public Cotizacion save(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }

    @Override
    public Optional<Cotizacion> update(int id, Cotizacion cotizacion) {
        Optional<Cotizacion> cotizacionOptional = findById(id);
        if(cotizacionOptional.isPresent()){
            Cotizacion cotizacionTemporal = cotizacionOptional.orElseThrow();
            cotizacionTemporal.setMarca(cotizacion.getMarca());
            cotizacionTemporal.setTipo(cotizacion.getTipo());
            cotizacionTemporal.setYear(cotizacion.getYear());
            cotizacionTemporal.setTotal(cotizacion.getTotal());

            return Optional.of(cotizacionRepository.save(cotizacionTemporal));
        }
        return cotizacionOptional;
    }

}
