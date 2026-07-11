package com.Tienda_Masc_REPO.Producto.DTO;

import java.util.List;

import lombok.Data;

@Data
public class TipoDTO {

    private Integer idTipo;
    private String nombreTipo;
    private List<String> nombreProductos;
}