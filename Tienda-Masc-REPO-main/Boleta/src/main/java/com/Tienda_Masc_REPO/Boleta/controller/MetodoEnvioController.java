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

import com.Tienda_Masc_REPO.Boleta.DTO.MetodoEnvioDTO;
import com.Tienda_Masc_REPO.Boleta.model.MetodoEnvio;
import com.Tienda_Masc_REPO.Boleta.services.MetodoEnvioService;

@RestController
@RequestMapping("/api/v1/metodos-envio")
public class MetodoEnvioController {

    @Autowired
    private MetodoEnvioService metodoEnvioService;

    @GetMapping
    public ResponseEntity<List<MetodoEnvioDTO>> obtenerTodos() {
        return new ResponseEntity<>(metodoEnvioService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearMetodoEnvio(@RequestBody MetodoEnvio metodoEnvio) {
        try {
            MetodoEnvioDTO nuevo = metodoEnvioService.guardar(metodoEnvio);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear metodo de evio: " + e.getMessage(), 
                                         HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{idMetodoPago}")
    public ResponseEntity<?> metodoEnvioPorId(@PathVariable Integer idMetodoEnvio){
        try{
            MetodoEnvioDTO metodoEnvios = metodoEnvioService.obtenerPorId(idMetodoEnvio);
            return new ResponseEntity<>(metodoEnvios, HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>("No se encontro el metodo de envio", HttpStatus.NOT_FOUND);
        }
    }
}
