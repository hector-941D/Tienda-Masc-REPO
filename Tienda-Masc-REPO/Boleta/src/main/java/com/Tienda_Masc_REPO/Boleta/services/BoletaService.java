package com.Tienda_Masc_REPO.Boleta.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda_Masc_REPO.Boleta.DTO.BoletaEntradaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.BoletaSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.ClienteSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.DTO.ProductoSalidaDTO;
import com.Tienda_Masc_REPO.Boleta.model.Boleta;
import com.Tienda_Masc_REPO.Boleta.model.MetodoEnvio;
import com.Tienda_Masc_REPO.Boleta.model.MetodoPago;
import com.Tienda_Masc_REPO.Boleta.repository.BoletaRepository;
import com.Tienda_Masc_REPO.Boleta.repository.MetodoEnvioRepository;
import com.Tienda_Masc_REPO.Boleta.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class BoletaService {

    @Autowired
    private BoletaValidaciones boletaValidaciones;

    @Autowired
    private BoletaRepository boletaRepository;

    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public BoletaSalidaDTO crearBoleta(BoletaEntradaDTO entradaDTO) {
        try {
            
            String ultimoFolio = boletaRepository.findFirstByOrderByIdBoletaDesc()
                    .map(Boleta::getNumeroFolio)
                    .orElse("000000");
            int siguienteNumero = Integer.parseInt(ultimoFolio) + 1;
            
            Boleta boleta = new Boleta();
            boleta.setNumeroFolio(String.format("%06d", siguienteNumero));
            log.trace("Folio generado para la nueva boleta: {}", boleta.getNumeroFolio());

            int neto = entradaDTO.getMontoNeto();
            int iva = (int) Math.round(neto * 0.19); 
            
            boleta.setMontoNeto(neto);
            boleta.setMontoIva(iva);
            boleta.setMontoTotal(neto + iva);
        
            MetodoPago pago = metodoPagoRepository.findById(entradaDTO.getIdMetodoPago())
                    .orElseThrow(() -> new RuntimeException("Metodo de pago no encontrado"));
            boleta.setMetodoPago(pago);

            MetodoEnvio envio = metodoEnvioRepository.findById(entradaDTO.getIdMetodoEnvio())
                    .orElseThrow(() -> new RuntimeException("Metodo de envio no encontrado"));
            boleta.setMetodoEnvio(envio);
            
            boleta.setIdCliente(entradaDTO.getIdCliente());
            boleta.setIdProducto(entradaDTO.getIdProducto());

            Boleta boletaGuardada = boletaRepository.save(boleta);
            log.info("Nueva boleta creada con exito! Folio: {}, ID: {}", boletaGuardada.getNumeroFolio(), boletaGuardada.getIdBoleta());
            
            BoletaSalidaDTO boletasalidaDTO = boletaValidaciones.convertirADTO(boletaGuardada);
            
            if (boletaGuardada.getIdCliente() != null) {
                ClienteSalidaDTO clienteExterno = boletaValidaciones.obtenerClienteExterno(boletaGuardada.getIdCliente());
                
                ClienteSalidaDTO clienteResumen = new ClienteSalidaDTO();
                clienteResumen.setIdCliente(clienteExterno.getIdCliente());
                clienteResumen.setNombreCliente(clienteExterno.getNombreCliente());
                clienteResumen.setRunCliente(clienteExterno.getRunCliente());
                clienteResumen.setRegion(clienteExterno.getRegion());
                clienteResumen.setComuna(clienteExterno.getComuna());

                boletasalidaDTO.setCliente(clienteResumen);
            }


            if (boletaGuardada.getIdProducto() != null) {
                ProductoSalidaDTO productoExterno = boletaValidaciones.obtenerProductoExterno(boletaGuardada.getIdProducto());
                
                ProductoSalidaDTO productoResumen = new ProductoSalidaDTO();
                productoResumen.setIdProducto(productoExterno.getIdProducto());
                productoResumen.setNombreProductos(productoExterno.getNombreProductos());
                productoResumen.setMarca(productoExterno.getMarca());
                productoResumen.setPrecio(productoExterno.getPrecio());
                productoResumen.setTipo(productoExterno.getTipo());
                productoResumen.setEspecie(productoExterno.getEspecie());

                boletasalidaDTO.setProducto(productoResumen);
            }
            
            return boletasalidaDTO;
        } catch (Exception e) {
            log.error("Error al intentar crear la boleta!");
            throw e;
        }
    }

    public List<BoletaSalidaDTO> obtenerTodas() {
        try {
            List<BoletaSalidaDTO> boletas = new ArrayList<>();
            for (Boleta boleta : boletaRepository.findAll()) {
                boletas.add(boletaValidaciones.convertirADTO(boleta));
            }
            log.info("Obteniendo todas la boletas registradas. Cantidad: {}", boletas.size());
            return boletas;
        } catch (Exception e) {
            log.error("Error al buscar la lista de boletas");
            throw e;
        }
    }

    public BoletaSalidaDTO buscarPorId(Integer idBoleta) {
        try {
            Boleta boleta = boletaRepository.findById(idBoleta)
                    .orElseThrow(() -> new RuntimeException("Boleta no encontrada"));
            
            log.info("Buscando por id la boleta {}", idBoleta);
            return boletaValidaciones.convertirADTO(boleta);
        } catch (Exception e) {
            log.error("Error el intentar buscar por id la boleta {}", idBoleta);
            throw e;
        }
    }
}

