package com.Tienda_Masc_REPO.Cliente.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Tienda_Masc_REPO.Cliente.DTO.ClienteDTO;
import com.Tienda_Masc_REPO.Cliente.Model.Cliente;

import reactor.core.publisher.Mono;

@Service
public class ClienteValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Boolean validarNullVacio(Cliente cliente){
        if(cliente == null || cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()){
            return false;
        }
        return true;
    }

    public ClienteDTO obtenerboleta(Integer idCliente){
        ClienteDTO clienteDto = new ClienteDTO();
        try {
            ClienteDTO clienteRecuperado = webClientBuilder.build()
                .get()
                .uri("http://clientes/api/v1/clientes/buscar-por-cliente/" + idCliente) 
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(ClienteDTO.class)
                .block();

            if (clienteRecuperado != null) {
                return clienteRecuperado;
            }
            
            clienteDto.setIdCliente(idCliente);
            clienteDto.setNombreCliente("nombre sin asignar");
            clienteDto.setRutCliente("rut sin asignar"); 
            
            return clienteDto; // CORREGIDO: Faltaba este return si 'clienteRecuperado' era null

        } catch (Exception e) {
            clienteDto.setIdCliente(0);
            clienteDto.setNombreCliente(String.valueOf(idCliente));
            clienteDto.setRutCliente("no se pudo conectar con el cliente");
            return clienteDto;
        }
    }
}