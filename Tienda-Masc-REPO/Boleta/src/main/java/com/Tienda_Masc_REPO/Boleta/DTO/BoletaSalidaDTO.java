package com.Tienda_Masc_REPO.Boleta.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoletaSalidaDTO extends RepresentationModel<BoletaSalidaDTO>{

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
