package com.Tienda_Masc_REPO.Boleta.model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "boletas")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBoleta;

    @Column(name = "numero_folio", nullable = false, unique = true)
    @Positive(message = "El numero de folio debe ser positivo (mayor que 0)")
    private String numeroFolio;

    @Column(name = "fecha_emision", nullable = false, updatable = false)
    @PastOrPresent(message = "La fecha de emision no puede ser futura")
    private LocalDate fechaEmision;

    @Column(name = "hora_emision", nullable = false, updatable = false)
    @PastOrPresent(message = "La hora de emision no puede ser futura")
    private LocalTime horaEmision;

    @Column(name = "monto_neto", nullable = false)
    @Positive(message = "El monto neto solo puede ser positivo")
    @Digits(integer = 10, fraction = 2, message = "Maximo 10 enteros y 2 decimales")
    private Integer montoNeto;

    @Column(name = "monto_iva", nullable = false)
    @Positive(message = "El monto neto solo puede ser positivo")
    @Digits(integer = 10, fraction = 2, message = "Maximo 10 enteros y 2 decimales")
    private Integer montoIva;

    @Column(name = "monto_total", nullable = false)
    @Positive(message = "El monto total solo puede ser positivo")
    @Digits(integer = 10, fraction = 2, message = "Maximo 10 enteros y 2 decimales")
    private Integer montoTotal;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "id_metodo_envio", nullable = false)
    private MetodoEnvio metodoEnvio;
    
    private Integer idCliente;

    private Integer idProducto;

    @PrePersist
    protected void crearFecha() {
        this.fechaEmision = LocalDate.now();
        this.horaEmision = LocalTime.now();
    }
}
