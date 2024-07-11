package com.wwun.springboot.seguroauto.springboot_seguroauto.repositories;

import org.springframework.data.repository.CrudRepository;

import com.wwun.springboot.seguroauto.springboot_seguroauto.entities.Cotizacion;

public interface CotizacionRepository extends CrudRepository<Cotizacion, Integer>{
    //crud básico
    //no es necesario agregar nada por el momento
}
