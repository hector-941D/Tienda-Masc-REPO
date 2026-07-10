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
        try {
            List<MetodoEnvioDTO> listaDTO = new ArrayList<>();
            for (MetodoEnvio metodoenvio : metodoEnvioRepository.findAll()) {
                listaDTO.add(convertirADTO(metodoenvio));
            }
            log.info("Obteniendo todos los metodos de envio. Total: {}", listaDTO.size());
            return listaDTO;
        } catch (Exception e) {
            log.error("Error al intentar obtener los metodos de envio");
            throw e; 
        }
    }

    public MetodoEnvioDTO guardar(MetodoEnvio metodoenvio) {
        try {
            MetodoEnvio metodoEnvioGuardado = metodoEnvioRepository.save(metodoenvio);
            log.info("Metodo de envio guardado con exito! id asignado: {}", metodoEnvioGuardado.getIdMetodoEnvio());
            return convertirADTO(metodoEnvioGuardado);
        } catch (Exception e) {
            log.error("Error al intentar guardar el metodo de envio: {}", metodoenvio.getTipoEnvio());
            throw e;
        }
    }

    public MetodoEnvioDTO obtenerPorId(Integer idMetodoEnvio) {
        try {
            MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(idMetodoEnvio)
                    .orElseThrow(() -> new RuntimeException("Metodo de envio no encontrado"));
            
            log.info("Buscando metodo de envio con id: {}", idMetodoEnvio);
            return convertirADTO(metodoEnvio);
        } catch (Exception e) {
            log.error("Error al buscar el metodo de envio con id: {}", idMetodoEnvio);
            throw e;
        }
    }

    public MetodoEnvioDTO convertirADTO(MetodoEnvio metodoenvio) {
        MetodoEnvioDTO metodoEnvioDTO = new MetodoEnvioDTO();
        metodoEnvioDTO.setIdMetodoEnvio(metodoenvio.getIdMetodoEnvio());
        metodoEnvioDTO.setTipoEnvio(metodoenvio.getTipoEnvio());
        return metodoEnvioDTO;
    }
}
