package com.Tienda_Masc_REPO.Producto.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Tienda_Masc_REPO.Producto.DTO.TipoDTO;
import com.Tienda_Masc_REPO.Producto.Model.Tipo;
import com.Tienda_Masc_REPO.Producto.Service.TipoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name = "Tipos", description = "Operaciones CRUD del microservicio Tipos")
@RestController
@RequestMapping("/api/v1/tipo")

public class TipoController {

    @Autowired
    private TipoService tipoService;

    @Operation(summary = "Metodo para obtener todos los tipos registrados", description = "Devuelve la lista completa de tipos registrados")
    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        List<TipoDTO> tipos = tipoService.obtenerTodosTipos();
        if (tipos.isEmpty()) {
            return new ResponseEntity<>("No hay tipos en este momento", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tipoService.obtenerTodosTipos(), HttpStatus.OK);
    }

    @Operation(summary = "Metodo para obtener un tipo por ID", description = "Devuelve un tipo segun su id")
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        try{
            TipoDTO tipo = tipoService.buscarPorId(id);
            return new ResponseEntity<>(tipo, HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("No se encontro el tipo de producto ", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Metodo para registrar nuevos tipos", description = "Ingresa tipos nuevos al sistema")
    @PostMapping
    public ResponseEntity<?> guardarTipo(@Valid @RequestBody Tipo tipo) {
        try {
            TipoDTO nuevo = tipoService.guardarTipo(tipo);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al ingresar el tipo " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Metodo para actualizar un registrados", description = "Permite la modificacion de un tipo segun su id")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTipo(@PathVariable Integer id, @Valid @RequestBody Tipo tipo){
        try{
            TipoDTO tipoActualizado = tipoService.actualizarTipo(id, tipo);
            return new ResponseEntity<>(tipoActualizado, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>("Error, no se pudo encontrar el tipo ",HttpStatus.NOT_FOUND);
        }
    }
}