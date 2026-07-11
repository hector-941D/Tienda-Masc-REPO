package com.Tienda_Masc_REPO.Producto.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
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

import com.Tienda_Masc_REPO.Producto.DTO.ProductoDTO;
import com.Tienda_Masc_REPO.Producto.Model.Producto;
import com.Tienda_Masc_REPO.Producto.Service.ProductoService;
import com.Tienda_Masc_REPO.Producto.assemblers.ProductoModelAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Productos", description = "Operaciones CRUD del microservicio Producto")
@RestController
@RequestMapping("/api/v2/productos")
public class ProductoControllerV2 {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoModelAssembler assembler;

    @Operation(summary = "Metodo para obtener todos los productos registrados", description = "Devuelve la lista completa de productos registrados")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO>>> obtenerTodosProductos(){
        List<EntityModel<ProductoDTO>> productos = productoService.obtenerTodos()
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(CollectionModel.of(
                productos,
                linkTo(methodOn(ProductoControllerV2.class).obtenerTodosProductos()).withSelfRel()
        ));
    }

    @Operation(summary = "Metodo para buscar un producto segun su id", description = "Devuelve un producto segun su id")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> buscarPorId(@PathVariable Integer id){
        try{    
            ProductoDTO producto = productoService.buscarPorId(id);
            return ResponseEntity.ok(assembler.toModel(producto));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Metodo para guardar un producto", description = "Registra un producto")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> guardarProducto(@Valid @RequestBody Producto producto){
        try{
            ProductoDTO guardarProducto = productoService.guardarProducto(producto);
            return ResponseEntity
                    .created(linkTo(methodOn(ProductoControllerV2.class).buscarPorId(guardarProducto.getIdProducto())).toUri())
                    .body(assembler.toModel(guardarProducto));
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Metodo para actualizar un producto segun su id", description = "Actualiza buscandolo por su id")
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<ProductoDTO>> actualizarProducto(@PathVariable Integer id,@Valid @RequestBody Producto producto){
        try{
            ProductoDTO actualizado = productoService.actualizarProducto(id, producto);
            return ResponseEntity.ok(
                assembler.toModel(actualizado));
        }catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }   

    @Operation(summary = "Metodo para eliminar un producto", description = "Borra un producto utilizando su id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Integer id){
        String resultado = productoService.eliminarProducto(id);
        if(resultado.contains("exitosamente")){
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
        return new ResponseEntity<>("Error, No se encontro el producto a borrar ", HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Metodo para buscar un producto por su tipo", description = "Devuelve todos los productos existentes que tengan un tipo en comun")
    @GetMapping(value = "/tipo/{nombreTipo}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<ProductoDTO>>> buscarPorTipo(@PathVariable String nombreTipo){
        List<EntityModel<ProductoDTO>> productos = productoService.buscarPorTipo(nombreTipo)
                .stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(
            CollectionModel.of(productos,linkTo(methodOn(ProductoControllerV2.class).buscarPorTipo(nombreTipo)).withSelfRel()));
    }
}