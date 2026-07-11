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
@Table(name = "metodos_envio")
public class MetodoEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetodoEnvio;

    @NotBlank(message = "El tipo de envio no puede estar vacio")
    @Column(name = "tipo_envio", nullable = false)
    private String tipoEnvio; 

    @Column(name = "costo_envio", nullable = false)
    private Integer costoEnvio; 
    
}
