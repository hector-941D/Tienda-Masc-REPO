package com.Tienda_Masc_REPO.Cliente.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Tienda_Masc_REPO.Cliente.DTO.ComunaDTO;
import com.Tienda_Masc_REPO.Cliente.Model.Comuna;
import com.Tienda_Masc_REPO.Cliente.Repository.ComunaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    private ComunaDTO convertirADTO(Comuna comuna){
        ComunaDTO dto = new ComunaDTO();
        dto.setIdComuna(comuna.getIdComuna());
        dto.setNombreComuna(comuna.getNombreComuna());
        if(comuna.getRegionId() != null){
            dto.setNombreRegion("Región ID: " + comuna.getRegionId());
        }
        return dto;
    }

    public List<ComunaDTO> obtenerTodos(){
        return comunaRepository.findAll().stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ComunaDTO buscarPorId(Integer id){
        Comuna comuna = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        return convertirADTO(comuna);
    }

    public Comuna guardar(Comuna comuna){
        return comunaRepository.save(comuna);
    }

    public Comuna actualizar(Integer id, Comuna comuna){
        Comuna comunaExistente = comunaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
        if(comuna.getNombreComuna() != null){
            comunaExistente.setNombreComuna(comuna.getNombreComuna());
        }
        if(comuna.getRegionId() != null){
            comunaExistente.setRegionId(comuna.getRegionId());
        }
        return comunaRepository.save(comunaExistente);
    }

    public String eliminar(Integer id){
        try{
            Comuna comuna = comunaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Comuna no encontrada"));
            comunaRepository.delete(comuna);
            return "Comuna eliminada correctamente";
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }
}