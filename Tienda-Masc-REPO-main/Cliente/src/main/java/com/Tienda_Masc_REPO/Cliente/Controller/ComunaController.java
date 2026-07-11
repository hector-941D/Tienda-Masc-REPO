package com.Tienda_Masc_REPO.Cliente.Controller;

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

import com.Tienda_Masc_REPO.Cliente.DTO.ComunaDTO;
import com.Tienda_Masc_REPO.Cliente.Model.Comuna;
import com.Tienda_Masc_REPO.Cliente.Services.ComunaService;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<ComunaDTO>> obtenerTodos(){
        List<ComunaDTO> comunas = comunaService.obtenerTodos();
        if(comunas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comunas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> buscarPorId(@PathVariable Integer id){
        try{
            ComunaDTO comuna = comunaService.buscarPorId(id);
            return new ResponseEntity<>(comuna, HttpStatus.OK);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Comuna> guardar(@RequestBody Comuna comuna){
        try{
            Comuna guardada = comunaService.guardar(comuna);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}