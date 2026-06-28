package com.Tienda_Masc_REPO.Boleta.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda_Masc_REPO.Boleta.DTO.MetodoEnvioDTO;
import com.Tienda_Masc_REPO.Boleta.model.MetodoEnvio;
import com.Tienda_Masc_REPO.Boleta.repository.MetodoEnvioRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class MetodoEnvioService {
    
    @Autowired
    private MetodoEnvioRepository metodoEnvioRepository;

    public List<MetodoEnvioDTO> obtenerTodos() {
        List<MetodoEnvioDTO> listaDTO = new ArrayList<>();
        for (MetodoEnvio metodoenvio : metodoEnvioRepository.findAll()) {
            listaDTO.add(convertirADTO(metodoenvio));
        }
        return listaDTO;
    }

    public MetodoEnvioDTO guardar(MetodoEnvio metodoenvio) {
        MetodoEnvio metodoEnvioGuardado = metodoEnvioRepository.save(metodoenvio);
        return convertirADTO(metodoEnvioGuardado);
    }

    public MetodoEnvioDTO convertirADTO(MetodoEnvio metodoenvio) {
        MetodoEnvioDTO metodoEnvioDTO = new MetodoEnvioDTO();
        metodoEnvioDTO.setIdMetodoEnvio(metodoenvio.getIdMetodoEnvio());
        metodoEnvioDTO.setTipoEnvio(metodoenvio.getTipoEnvio());

        return metodoEnvioDTO;
    }

    public MetodoEnvioDTO obtenerPorId(Integer idMetodoEnvio){
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(idMetodoEnvio).orElseThrow(() -> new RuntimeException("Metodo de envio no encontrado"));
        return convertirADTO(metodoEnvio);
    }
}
