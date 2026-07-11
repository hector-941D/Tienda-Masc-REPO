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
@Table(name = "tipo")
public class Tipo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    @Column(name = "nombre_tipo", nullable = false)
    @Size (min = 1, max = 30, message = "El nombre del tipo de producto tiene que tener de 2 a 30 caracteres") 
    private String nombreDelTipo;
    
    @OneToMany(mappedBy = "tipo")
    @ToString.Exclude
    private List<Producto> productos;
}