package com.Tienda_Masc_REPO.Producto.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table (name = "marca")
public class Marca {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idMarca;

    @Column(name = "nombre_marca", nullable = false)
    @Size (min = 2, max = 30, message = "El nombre de la marca tiene que tener de 2 a 30 caracteres")
    private String nombreMarca;

    @OneToMany(mappedBy = "marca")
    @ToString.Exclude
    private List<Producto> productos;
}