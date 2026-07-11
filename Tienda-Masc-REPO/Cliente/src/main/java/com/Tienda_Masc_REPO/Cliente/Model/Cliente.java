package com.Tienda_Masc_REPO.Cliente.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    
    @NotBlank(message = "El nombre no puede quedar vacío")
    @Size(min = 3, max = 50)
    @Column(name = "nombre_cliente", nullable = false)
    private String nombre;

    @NotBlank(message = "El RUT no puede quedar vacío")
    @Column(name = "rut_cliente", nullable = false, unique = true)
    private String rutCliente;

    @NotBlank(message = "La comuna no puede quedar vacía")
    @Column(name = "comuna_cliente", nullable = false)
    private String comunaCliente;

    @NotBlank(message = "La región no puede quedar vacía")
    @Column(name = "region_cliente", nullable = false)
    private String regionCliente;
}