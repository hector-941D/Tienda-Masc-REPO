package com.Tienda_Masc_REPO.Boleta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tienda_Masc_REPO.Boleta.DTO.BoletaEntradaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.BoletaSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.assemblers.BoletaModelAssembler;
import com.Tienda_Masc_REPO.Boleta.services.BoletaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {
    
    @Autowired
    private BoletaService boletaService;

    @Autowired
    private BoletaModelAssembler boletaAssembler;

    @GetMapping
    public ResponseEntity<?> todasLasBoletas(){
        List<BoletaSalidaDTO> boletas = boletaService.obtenerTodas();
        if(!boletas.isEmpty()){
            CollectionModel<EntityModel<BoletaSalidaDTO>> boletaConLinks = boletaAssembler.toCollectionModel(boletas);
            return new ResponseEntity<>(boletaConLinks, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay boletas", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{idBoleta}")
    public ResponseEntity<?> boletaPorId(@PathVariable Integer idBoleta){
        try{
            BoletaSalidaDTO boletas = boletaService.buscarPorId(idBoleta);

            EntityModel<BoletaSalidaDTO> boletaConLinks = boletaAssembler.toModel(boletas);
            return new ResponseEntity<>(boletaConLinks, HttpStatus.OK);

        }catch(RuntimeException e){
            return new ResponseEntity<>("No se encontro la boleta", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarBoleta(@Valid @RequestBody BoletaEntradaDTO boletaEntradaDTO){
        try {
            BoletaSalidaDTO nuevaBoleta = boletaService.crearBoleta(boletaEntradaDTO);

            EntityModel<BoletaSalidaDTO> boletaConLinks = boletaAssembler.toModel(nuevaBoleta);
            return new ResponseEntity<>(boletaConLinks, HttpStatus.CREATED);

        } catch(Exception e) {
            return new ResponseEntity<>("error al crear la boleta: " + e.getMessage(), 
                                        HttpStatus.BAD_REQUEST);
        }
    }
}
