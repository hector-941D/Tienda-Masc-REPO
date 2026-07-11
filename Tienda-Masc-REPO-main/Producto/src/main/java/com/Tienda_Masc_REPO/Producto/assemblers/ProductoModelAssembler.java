package com.Tienda_Masc_REPO.Producto.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.Tienda_Masc_REPO.Producto.Controller.ProductoControllerV2;
import com.Tienda_Masc_REPO.Producto.DTO.ProductoDTO;

@Component
public class ProductoModelAssembler implements RepresentationModelAssembler<ProductoDTO, EntityModel<ProductoDTO>>{

    @Override
    public EntityModel<ProductoDTO> toModel(ProductoDTO producto){
        return EntityModel.of(producto,
            linkTo(methodOn(ProductoControllerV2.class).buscarPorId(producto.getIdProducto())).withSelfRel(),
            linkTo(methodOn(ProductoControllerV2.class).obtenerTodosProductos()).withRel("productos"));
    }
}