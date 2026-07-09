package com.Tienda_Masc_REPO.Boleta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tienda_Masc_REPO.Boleta.DTO.MetodoPagoDTO;
import com.Tienda_Masc_REPO.Boleta.model.MetodoPago;
import com.Tienda_Masc_REPO.Boleta.services.MetodoPagoService;

@RestController
@RequestMapping("/api/v1/metodos-pago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @GetMapping
    public ResponseEntity<List<MetodoPagoDTO>> obtenerTodos() {
        return new ResponseEntity<>(metodoPagoService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearMetodoPago(@RequestBody MetodoPagoDTO metodoPagoDTO) { 
        try {
            MetodoPago metodoPago = new MetodoPago();
            metodoPago.setNombreMetodoPago(metodoPagoDTO.getNombreMetodoPago()); 
            MetodoPagoDTO nuevo = metodoPagoService.guardar(metodoPago);
            
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear método de pago: " + e.getMessage(), 
                                        HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idMetodoPago}")
    public ResponseEntity<?> metodoPagoPorId(@PathVariable Integer idMetodoPago){
        try{
            MetodoPagoDTO metodoPagos = metodoPagoService.obtenerPorId(idMetodoPago);
            return new ResponseEntity<>(metodoPagos, HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>("No se encontró el metodo de pago", HttpStatus.NOT_FOUND);
        }
    }
}
