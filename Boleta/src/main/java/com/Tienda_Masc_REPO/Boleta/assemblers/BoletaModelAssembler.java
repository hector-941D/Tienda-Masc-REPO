package com.Tienda_Masc_REPO.Boleta.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.Tienda_Masc_REPO.Boleta.DTO.BoletaSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.controller.BoletaController;

@Component
public class BoletaModelAssembler implements RepresentationModelAssembler<BoletaSalidaDTO, EntityModel<BoletaSalidaDTO>> {

    @Override
    public EntityModel<BoletaSalidaDTO> toModel(BoletaSalidaDTO boletaSalidaDTO) {

        Integer idInteger = boletaSalidaDTO.getIdBoleta();

        return EntityModel.of(boletaSalidaDTO,
                linkTo(methodOn(BoletaController.class).boletaPorId(idInteger)).withSelfRel(),
                linkTo(methodOn(BoletaController.class).todasLasBoletas()).withRel("boletas")
        );
    }
}
