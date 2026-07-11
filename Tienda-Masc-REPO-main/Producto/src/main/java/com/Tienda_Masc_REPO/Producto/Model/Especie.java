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
@Table (name = "especie")
public class Especie {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idEspecies;

    @Column(name="nombre_especie", nullable=false)
    @Size (min = 1, max = 70, message = "El nombre de la especie tiene que tener de 2 a 70 caracteres")
    private String nombreEspecie;

    @OneToMany(mappedBy = "especie")
    @ToString.Exclude
    private List<Producto> productos;
}