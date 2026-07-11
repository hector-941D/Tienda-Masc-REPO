package com.Tienda_Masc_REPO.Cliente.DTO;

import lombok.Data;

@Data
public class ClienteDTO {

    private Integer idCliente;
    private String nombreCliente;
    private String rutCliente;
    private String boleta;
    private String Comuna;
    private String Region;
}
