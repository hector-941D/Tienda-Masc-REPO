package com.Tienda_Masc_REPO.Boleta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "metodos_pago")
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetodoPago;

    @NotBlank(message = "El nombre del metodo de pago no puede estar vacio")
    @Column(name = "nombre_metodo_pago", nullable = false)
    private String nombreMetodoPago;  
    
}
