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

import com.Tienda_Masc_REPO.Producto.DTO.MarcaDTO;
import com.Tienda_Masc_REPO.Producto.Model.Marca;
import com.Tienda_Masc_REPO.Producto.Service.MarcaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Marcas", description = "Operaciones CRUD del microservicio Marcas")
@RestController
@RequestMapping("api/v1/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @Operation(summary = "Metodo para obtener todas las marcas registradas", description = "Devuelve la lista completa de las marcas registradas")
    @GetMapping
    public ResponseEntity<?> obtenerTodos(){
        List<MarcaDTO> marcas = marcaService.obtenerTodos();
        if(marcas.isEmpty()){
            return new ResponseEntity<>("no hay marcas en este momento ", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(marcas, HttpStatus.OK);
    }

    @Operation(summary = "Metodo para buscar una marca por su id", description = "Devuelve un marca buscado segun su id")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            MarcaDTO marca = marcaService.buscarPorId(id);
            return new ResponseEntity<>(marca, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>("No se encontro la marca ", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Metodo para guardar una marca", description = "Registra una marca")
    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Marca marca){
        try{
            MarcaDTO guardada = marcaService.guardar(marca);
            return new ResponseEntity<>(guardada, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Error al ingresar la marca " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Metodo para actualizar una marca", description = "Actualiza una marca segun su id")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar( @PathVariable Integer id, @Valid @RequestBody Marca marca){
        try{
            MarcaDTO actualizada = marcaService.actualizar(id, marca);
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>("No se encontro la marca ",HttpStatus.NOT_FOUND);
        }
    }
}