package com.Tienda_Masc_REPO.Cliente.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class BoletaSalidaDTO {

    private Integer idBoleta;
    private String numeroFolio;
    private Integer montoNeto;
    private Integer montoIva;
    private Integer montoTotal;
    private ClienteSalidaDTO cliente;
    private ProductoSalidaDTO producto;
    private LocalDate fechaEmision;
    private LocalTime horaEmision;
    private MetodoEnvioDTO metodoEnvio;
    private MetodoPagoDTO metodoPago;

}
