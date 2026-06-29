package com.Tienda_Masc_REPO.Producto.DTO;

import lombok.Data;

@Data
public class ProductoDTO {

    private Integer idProducto;
    private String nombreProductos;
    private Integer precio;
    private String nombreTipo;
    private String nombreMarca;
    private String nombreEspecie;
}