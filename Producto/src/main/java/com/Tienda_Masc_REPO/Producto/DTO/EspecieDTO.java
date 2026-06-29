package com.Tienda_Masc_REPO.Producto.DTO;

import java.util.List;

import lombok.Data;

@Data
public class EspecieDTO {
    
    private Integer idEspecie;
    private String nombreEspecie;
    private List<String> nombreProductos;
}