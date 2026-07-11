package com.Tienda_Masc_REPO.Producto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tienda_Masc_REPO.Producto.DTO.EspecieDTO;
import com.Tienda_Masc_REPO.Producto.Model.Especie;
import com.Tienda_Masc_REPO.Producto.Service.EspecieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name = "Especies", description = "Operaciones CRUD del microservicio Especies")
@RestController
@RequestMapping("/api/v1/especie")
public class EspecieController {
    
    @Autowired
    private EspecieService especieService;

    @Operation(summary = "Metodo para obtener todas las especies", description = "Devuelve la lista completa de especies registradas")
    @GetMapping
    public ResponseEntity<?> obtenerTodos(){
        List<EspecieDTO> especies = especieService.obtenerTodos();
        if(especies.isEmpty()){
            return new ResponseEntity<>("No hay especies en este momento", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(especies, HttpStatus.OK);
    }

    @Operation(summary = "Metodo para encontrar una especie", description = "Devuelve una especie utilizando su id")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            EspecieDTO especie = especieService.buscarPorId(id);
            return new ResponseEntity<>(especie, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>("No se encontro la especie " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Metodo para guardar una especie", description = "Registra una especie")
    @PostMapping
    public ResponseEntity<?> guardarEspecie(@Valid @RequestBody Especie especie){
        try{
            EspecieDTO guardada = especieService.guardar(especie);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Error al ingresar la especie " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Metodo para actualizar una especie", description = "Actualiza una especie segun su id")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEspecie(@PathVariable Integer id,@Valid @RequestBody Especie especie){
        try{
            EspecieDTO actualizada = especieService.actualizar(id, especie);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>("Error al actualizar la especie " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}