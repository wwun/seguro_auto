package com.wwun.springboot.seguroauto.springboot_seguroauto.controllers;

import java.net.http.HttpResponse;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wwun.springboot.seguroauto.springboot_seguroauto.entities.Cotizacion;
import com.wwun.springboot.seguroauto.springboot_seguroauto.services.CotizacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @PostMapping
    private ResponseEntity<?> crear(@Valid @RequestBody Cotizacion cotizacion, BindingResult result){
        if(result.hasFieldErrors()){    //para algo más general puede ser solo hasErrors()
            System.out.println("Error");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cotizacionService.save(cotizacion));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> ver(@PathVariable int id){
        Optional<Cotizacion> cotizacion = cotizacionService.findById(id);
        if(cotizacion.isPresent()){
            return ResponseEntity.ok(cotizacion.orElseThrow());
            //return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.notFound().build();
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        /*
        HttpStatus.OK (200): Este estado HTTP se utiliza para indicar que la solicitud ha sido exitosa y el servidor ha devuelto la respuesta solicitada. Es apropiado para respuestas a solicitudes GET, PUT, o POST cuando no se está creando un nuevo recurso pero se está devolviendo un recurso existente o actualizado

        Uso de ResponseEntity.ok vs ResponseEntity.status(HttpStatus.OK).build():

        ResponseEntity.ok() es más conciso y es la forma recomendada para devolver un HttpStatus.OK.
        ResponseEntity.status(HttpStatus.OK).build() es más verboso y generalmente no es necesario a menos que quieras personalizar la respuesta de alguna manera
        Notar que en uno no se está devolviendo el contenido de la cotización encontrada, solo se está devolviendo el estado HTTP. En cambio, el otro método está devolviendo el producto encontrado en la respuesta, lo cual es más útil para el cliente que hace la solicitud

        El uso de orElseThrow() en el código es una forma elegante de obtener el valor del Optional. Si el Optional está presente, devuelve el valor; si no, lanza una excepción, lo que en este contexto está bien ya que se sabe que el Optional está presente debido al if.
         */
    }

    @PostMapping
    private ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody Cotizacion cotizacion, BindingResult result){
        if(result.hasFieldErrors()){
            System.out.println("Error");
        }
        Optional<Cotizacion> cotizacionActualizada = cotizacionService.update(id, cotizacion);
        if(cotizacionActualizada.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(cotizacionActualizada.orElseThrow());
            /*
            HttpStatus.CREATED (201): Este estado HTTP se utiliza para indicar que una nueva entidad ha sido creada con éxito. Es comúnmente usado en respuestas a solicitudes POST donde un nuevo recurso es creado en el servidor
             */
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/delete/{id}")
    private ResponseEntity<?> delete(@PathVariable int id){
        Optional<Cotizacion> cotizacion = cotizacionService.delete(id);
        if(cotizacion.isPresent()){
            return ResponseEntity.ok(cotizacion.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
