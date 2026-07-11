package com.Tienda_Masc_REPO.Producto.DTO;

import java.util.List;

import lombok.Data;

@Data
public class MarcaDTO {

    private Integer idMarca;
    private String nombreMarca;
    private List<String> nombreProductos;
}