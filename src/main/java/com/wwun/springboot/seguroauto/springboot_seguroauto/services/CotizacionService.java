package com.wwun.springboot.seguroauto.springboot_seguroauto.services;

import java.util.List;
import java.util.Optional;

import com.wwun.springboot.seguroauto.springboot_seguroauto.entities.Cotizacion;

public interface CotizacionService {
    Optional<Cotizacion> findById(int id);
    List<Cotizacion> findAll();
    Optional<Cotizacion> delete(int id);
    Cotizacion save(Cotizacion cotizacion);
    Optional<Cotizacion> update(int id, Cotizacion cotizacion);
}
