package com.Tienda_Masc_REPO.Cliente.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comunas")
public class Comuna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComuna;

    @NotBlank(message = "El nombre de la comuna no puede quedar vacío")
    @Column(name = "nombre_comuna", nullable = false)
    private String nombreComuna;

    @NotNull(message = "El ID de la región es obligatorio")
    @Column(name = "region_id", nullable = false)
    private Integer regionId;
}