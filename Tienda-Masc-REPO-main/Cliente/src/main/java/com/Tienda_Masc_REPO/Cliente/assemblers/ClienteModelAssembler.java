package com.Tienda_Masc_REPO.Cliente.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.Tienda_Masc_REPO.Cliente.ClienteDTO;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<ClienteDTO, EntityModel<ClienteDTO>> {

    @Override
    public EntityModel<ClienteDTO> toModel(ClienteDTO clienteDTO) {
        Integer idBoleta = clienteDTO.getIdBoleta(); 

        return EntityModel.of(clienteDTO,
                linkTo(methodOn(ClienteController.class).clientePorId(clienteDTO.getIdCliente())).withSelfRel(),
                linkTo(methodOn(BoletaController.class).boletaPorId(idBoleta)).withRel("boletas")
        );
    }
}
