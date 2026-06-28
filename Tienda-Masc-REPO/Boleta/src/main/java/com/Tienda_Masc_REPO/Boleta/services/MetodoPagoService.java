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
        List<MetodoPagoDTO> listaDto = new ArrayList<>();
        for (MetodoPago metodopago : metodoDePagorepository.findAll()) {
            listaDto.add(convertirADTO(metodopago));
        }
        return listaDto;
    }

    public MetodoPagoDTO guardar(MetodoPago metodopago) {
        MetodoPago guardado = metodoDePagorepository.save(metodopago);
        return convertirADTO(guardado);
    }

    public MetodoPagoDTO convertirADTO(MetodoPago metodopago) {
        MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();
        metodoPagoDTO.setIdMetodoPago(metodopago.getIdMetodoPago());
        metodoPagoDTO.setNombreMetodoPago(metodopago.getNombreMetodoPago());
        return metodoPagoDTO;
    }

    public MetodoPagoDTO obtenerPorId(Integer idMetodoPago){
        MetodoPago metodoPago = metodoDePagorepository.findById(idMetodoPago).orElseThrow(() -> new RuntimeException("Metodo de pago no encontrado"));
        return convertirADTO(metodoPago);
    }
}