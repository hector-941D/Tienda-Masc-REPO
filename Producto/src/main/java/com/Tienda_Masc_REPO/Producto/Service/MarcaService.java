package com.Tienda_Masc_REPO.Producto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda_Masc_REPO.Producto.DTO.MarcaDTO;
import com.Tienda_Masc_REPO.Producto.Model.Especie;
import com.Tienda_Masc_REPO.Producto.Model.Marca;
import com.Tienda_Masc_REPO.Producto.Model.Producto;
import com.Tienda_Masc_REPO.Producto.Repository.MarcaRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    private MarcaDTO convertirADTO(Marca marca){
        MarcaDTO dto = new MarcaDTO();
        dto.setIdMarca(marca.getIdMarca());
        dto.setNombreMarca(marca.getNombreMarca());
        if (marca.getProductos() != null) {
            dto.setNombreProductos(
                marca.getProductos().stream()
                    .map(Producto::getNombreProductos)
                    .toList()
                );
            }
        return dto;
    }

    public List<MarcaDTO> obtenerTodos(){
        log.info("Obteniendo todas las marcas");
        return marcaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public MarcaDTO buscarPorId(Integer id){
        log.info("Buscando marca con ID {}", id);
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró la marca con ID {}", id);
                    return new RuntimeException("Marca no encontrada");
                });
        log.info("Marca '{}' encontrada correctamente", marca.getNombreMarca());
        return convertirADTO(marca);
    }

    public MarcaDTO guardar(Marca marcas){
        log.info("Guardando marca '{}'", marcas.getNombreMarca());
        Marca marca = marcaRepository.save(marcas);
        log.info("Marca guardada con ID {}", marca.getIdMarca());

        return convertirADTO(marca);
    }

    public MarcaDTO actualizar(Integer id, Marca marca){
        log.info("Actualizando marca con ID {}", id);
        Marca marcaExistente = marcaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró la marca con ID {}", id);
                    return new RuntimeException("Marca no encontrada");
                });
        if(marca.getNombreMarca() != null){
            marcaExistente.setNombreMarca(marca.getNombreMarca());
        }
        Marca actualizado = marcaRepository.save(marcaExistente);
        log.info("Marca '{}' actualizada correctamente", actualizado.getNombreMarca());
        return convertirADTO(actualizado);
    }
}