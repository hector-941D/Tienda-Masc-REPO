package com.Tienda_Masc_REPO.Boleta.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda_Masc_REPO.Boleta.DTO.MetodoPagoDTO;
import com.Tienda_Masc_REPO.Boleta.model.MetodoPago;
import com.Tienda_Masc_REPO.Boleta.repository.MetodoPagoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoDePagorepository;

    public List<MetodoPagoDTO> obtenerTodos() {
        try{
            List<MetodoPagoDTO> listaDto = new ArrayList<>();
            for (MetodoPago metodopago : metodoDePagorepository.findAll()) {
                listaDto.add(convertirADTO(metodopago));
            }
        
        log.info("Obteniendo todos los metodos de pago");
        return listaDto;
        } catch (Exception e) {
            log.error("Error al obtener los metodos de pago");
            throw e;
        }
    }

    public MetodoPagoDTO guardar(MetodoPago metodopago) {
        try {
            MetodoPago guardado = metodoDePagorepository.save(metodopago);
            log.info("Nuevo metodo de pago guardado");
            return convertirADTO(guardado);
        } catch (Exception e){
            log.error("Error al guardar nuevo metodo de pago!");
            throw e;
        }    
    }   

    public MetodoPagoDTO convertirADTO(MetodoPago metodopago) {
        try {
            MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();
            metodoPagoDTO.setIdMetodoPago(metodopago.getIdMetodoPago());
            metodoPagoDTO.setNombreMetodoPago(metodopago.getNombreMetodoPago());
            return metodoPagoDTO;
        } catch (Exception e) {
            log.error("Error al convertir a DTO en metodo de pago!");
            throw e;
        }    
    }

    public MetodoPagoDTO obtenerPorId(Integer idMetodoPago){
        try {
            MetodoPago metodoPago = metodoDePagorepository.findById(idMetodoPago).orElseThrow(() -> new RuntimeException("Metodo de pago no encontrado"));
            log.info("Obteniendo metodo de pago con id: {}", idMetodoPago);
            return convertirADTO(metodoPago);
        } catch (Exception e){
            log.warn("Error al obtener metodo de pago con id: {}", idMetodoPago);
            throw e;
        }
    }
}