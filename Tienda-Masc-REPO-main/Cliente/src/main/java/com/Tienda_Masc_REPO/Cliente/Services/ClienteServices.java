package com.Tienda_Masc_REPO.Cliente.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda_Masc_REPO.Cliente.DTO.ClienteDTO;
import com.Tienda_Masc_REPO.Cliente.Model.Cliente;
import com.Tienda_Masc_REPO.Cliente.Repository.ClienteRepository;

@Service
public class ClienteServices {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<ClienteDTO> obtenerTodas(){
        List<ClienteDTO> clientes = new ArrayList<>();
        for(Cliente cliente : clienteRepository.findAll()){
            clientes.add(convertirADTO(cliente)); 
        }
        return clientes;
    }

    public ClienteDTO guardarCliente(Cliente cliente) {
        if (cliente.getIdCliente() == null){
            throw new RuntimeException("El id del cliente es obligatorio");
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            throw new RuntimeException("El nombre del cliente es obligatorio.");
        }

        if (cliente.getRutCliente() == null || cliente.getRutCliente().trim().isEmpty()) {
            throw new RuntimeException("El Rut del cliente es obligatorio.");
        }

        Optional<Cliente> clienteExistente = clienteRepository.findByRutCliente(cliente.getRutCliente());
        if (clienteExistente.isPresent()) {
            throw new RuntimeException("Ya existe un cliente registrado con el RUT: " + cliente.getRutCliente());
        }

        cliente.setNombre(cliente.getNombre().toUpperCase().trim());
        cliente.setRutCliente(cliente.getRutCliente().toUpperCase().trim());

        Cliente clienteGuardado = clienteRepository.save(cliente);
        return convertirADTO(clienteGuardado);
    }

    public ClienteDTO buscarPorId(Integer idCliente){
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return convertirADTO(cliente);
    }

    private ClienteDTO convertirADTO (Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdCliente(cliente.getIdCliente());
        clienteDTO.setNombreCliente(cliente.getNombre());
        clienteDTO.setRutCliente(cliente.getRutCliente());
        
        return clienteDTO;
    }
}