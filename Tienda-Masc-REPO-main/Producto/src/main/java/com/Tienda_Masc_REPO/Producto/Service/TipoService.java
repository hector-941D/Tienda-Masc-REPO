package com.Tienda_Masc_REPO.Producto.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Tienda_Masc_REPO.Producto.DTO.TipoDTO;
import com.Tienda_Masc_REPO.Producto.Model.Producto;
import com.Tienda_Masc_REPO.Producto.Model.Tipo;
import com.Tienda_Masc_REPO.Producto.Repository.TipoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    public TipoDTO convertirADTO(Tipo tipo) {
        TipoDTO tipoDTO = new TipoDTO();
        tipoDTO.setIdTipo(tipo.getIdTipo());
        tipoDTO.setNombreTipo(tipo.getNombreDelTipo());
        if (tipo.getProductos() != null) {
            tipoDTO.setNombreProductos(
                tipo.getProductos().stream()
                    .map(Producto::getNombreProductos)
                    .toList()
                );
            }

        return tipoDTO;
    }

    public List<TipoDTO> obtenerTodosTipos() {
        log.info("Obteniendo todos los tipos");
        return tipoRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public TipoDTO buscarPorId(Integer id){
        log.info("Buscando tipo con ID {}", id);
        Tipo tipo = tipoRepository.findById(id)
            .orElseThrow(() -> {
                log.warn("No se encontró el tipo con ID {}", id);
                return new RuntimeException("Tipo no encontrado");
            });
        log.info("Tipo '{}' encontrado correctamente", tipo.getNombreDelTipo());
        return convertirADTO(tipo);
    }
    
    public TipoDTO guardarTipo(Tipo tipos) {    
        log.info("Guardando tipo '{}'", tipos.getNombreDelTipo());
        Tipo tipo = tipoRepository.save(tipos);
        log.info("Tipo guardado con ID {}", tipo.getIdTipo());
        return convertirADTO(tipo);
    }

    public TipoDTO actualizarTipo(Integer id, Tipo tipos){
        log.info("Actualizando tipo con ID {}", id);
        Tipo tipo = tipoRepository.findById(id)
            .orElseThrow(() -> {
                log.warn("No se encontró el tipo con ID {}", id);
                return new RuntimeException("Tipo no encontrado");
            });
        if(tipos.getNombreDelTipo() != null){
            tipo.setNombreDelTipo(tipos.getNombreDelTipo());
        }
        Tipo actualizado = tipoRepository.save(tipo);
        log.info("Tipo '{}' actualizado correctamente", actualizado.getNombreDelTipo());
        return convertirADTO(actualizado);
    }
}