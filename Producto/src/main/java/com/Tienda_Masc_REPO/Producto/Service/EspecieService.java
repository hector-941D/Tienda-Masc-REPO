package com.Tienda_Masc_REPO.Producto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda_Masc_REPO.Producto.DTO.EspecieDTO;
import com.Tienda_Masc_REPO.Producto.Model.Especie;
import com.Tienda_Masc_REPO.Producto.Model.Producto;
import com.Tienda_Masc_REPO.Producto.Repository.EspecieRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    private EspecieDTO convertirADTO(Especie especie){
        EspecieDTO dto = new EspecieDTO();
        dto.setIdEspecie(especie.getIdEspecies());
        dto.setNombreEspecie(especie.getNombreEspecie());
        if (especie.getProductos() != null) {
            dto.setNombreProductos(
                especie.getProductos().stream()
                    .map(Producto::getNombreProductos)
                    .toList()
                );
            }
        return dto;
    }

    public List<EspecieDTO> obtenerTodos(){
        log.info("Obteniendo todas las especies");
        return especieRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public EspecieDTO buscarPorId(Integer id){
        log.info("Buscando especie con ID {}", id);
        Especie especie = especieRepository.findById(id)
                .orElseThrow(() -> {log.warn("No se encontró la especie con ID {}", id);
                    return new RuntimeException("Especie no encontrada");
                });
        log.info("Especie '{}' encontrada correctamente", especie.getNombreEspecie());
        return convertirADTO(especie);
    }

    public EspecieDTO guardar(Especie especie){
        log.info("Guardando especie '{}'", especie.getNombreEspecie());
        Especie guardar = especieRepository.save(especie);
        log.info("Especie guardada con ID {}", guardar.getIdEspecies());
        return convertirADTO(guardar);
    }

    public EspecieDTO actualizar(Integer id, Especie especie){
        log.info("Actualizando especie con ID {}", id);
        Especie especieExistente = especieRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró la especie con ID {}", id);
                    return new RuntimeException("Especie no encontrada");
                });

        if(especie.getNombreEspecie() != null){
            especieExistente.setNombreEspecie(especie.getNombreEspecie());
        }
        Especie actualizado = especieRepository.save(especieExistente);
        log.info("Especie '{}' actualizada correctamente", actualizado.getNombreEspecie());
        return convertirADTO(actualizado);
    }
}