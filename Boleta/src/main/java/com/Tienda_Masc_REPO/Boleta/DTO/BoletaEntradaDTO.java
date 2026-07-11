package com.Tienda_Masc_REPO.Boleta.DTO;

import lombok.Data;

@Data
public class BoletaEntradaDTO {

    private Integer idCliente;
    private Integer idProducto;
    private Integer montoNeto;
    private Integer idMetodoPago;
    private Integer idMetodoEnvio;
    private String TipoEnvio;

}
