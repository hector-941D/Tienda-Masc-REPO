package com.Tienda_Masc_REPO.Boleta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.Tienda_Masc_REPO.Boleta.DTO.BoletaSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.ClienteSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.ProductoSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.model.Boleta;

import reactor.core.publisher.Mono;

@Service
public class BoletaValidaciones {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public ClienteSalidaDTO obtenerClienteExterno(Integer idCliente) {
        ClienteSalidaDTO clienteExternoDto = new ClienteSalidaDTO();
        try {
            ClienteSalidaDTO clienteRecuperado = webClientBuilder.build()
                    .get()
                    .uri("http://clientes/api/v1/clientes/buscar-por-cliente/" + idCliente)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                    .bodyToMono(ClienteSalidaDTO.class)
                    .block(); 

            if (clienteRecuperado != null) {
                return clienteRecuperado;
            }
            clienteExternoDto.setIdCliente(idCliente);
            clienteExternoDto.setNombreCliente("nombre sin asignar");
            clienteExternoDto.setRunCliente("run sin asignar");
            clienteExternoDto.setComuna("comuna sin asignar");
            clienteExternoDto.setRegion("region sin asignar");

            return clienteExternoDto;

        } catch (Exception e) {
            clienteExternoDto.setIdCliente(idCliente);
            clienteExternoDto.setNombreCliente("no se pudo conectar con el Cliente");
            clienteExternoDto.setRunCliente("no se pudo conectar con el Cliente");
            clienteExternoDto.setComuna("no se pudo conectar con el Cliente");
            clienteExternoDto.setRegion("no se pudo conectar con el Cliente");

            return clienteExternoDto;
        }
    }

    public ProductoSalidaDTO obtenerProductoExterno(Integer idProducto){
        ProductoSalidaDTO productoExternoDto = new ProductoSalidaDTO();
        try {
            ProductoSalidaDTO productoRecuperado = webClientBuilder.build()
                .get()
                .uri("http://productos/api/v1/productos/buscar-por-producto/" + idProducto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToMono(ProductoSalidaDTO.class)
                .block();

            if (productoRecuperado != null) {
                return productoRecuperado;
            }
            productoExternoDto.setIdProducto(idProducto);
            productoExternoDto.setNombreProductos("nombre sin asignar");
            productoExternoDto.setPrecio(0);
            productoExternoDto.setEspecie("especie sin asignar");
            productoExternoDto.setMarca("marca sin asignar");
            productoExternoDto.setTipo("tipo sin asignar");

            return productoExternoDto;

        } catch (Exception e) {
            productoExternoDto.setIdProducto(idProducto);
            productoExternoDto.setNombreProductos("no se a podido conectar con producto");
            productoExternoDto.setPrecio(0);
            productoExternoDto.setEspecie(null);
            productoExternoDto.setMarca(null);
            productoExternoDto.setTipo(null);
            return productoExternoDto;
        }
    }

    public BoletaSalidaDTO convertirADTO (Boleta boleta){
        BoletaSalidaDTO boletasalidaDTO = new BoletaSalidaDTO();
        boletasalidaDTO.setIdBoleta(boleta.getIdBoleta());
        boletasalidaDTO.setNumeroFolio(boleta.getNumeroFolio());
        boletasalidaDTO.setFechaEmision(boleta.getFechaEmision());
        boletasalidaDTO.setHoraEmision(boleta.getHoraEmision());
        boletasalidaDTO.setMontoNeto(boleta.getMontoNeto());
        boletasalidaDTO.setMontoIva(boleta.getMontoIva());
        boletasalidaDTO.setMontoTotal(boleta.getMontoTotal());
        
        return boletasalidaDTO;
    }
}
