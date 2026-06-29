package com.Tienda_Masc_REPO.Producto.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table (name = "producto")
public class Producto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idProductos;


    @Column (name = "nombre_productos", nullable = false)
    @Size (min = 1, max = 30, message = "El nombre del producto debe tener entre 1 y 30 caracteres")
    private String nombreProductos;

    @Column (name = "precio", nullable = false)
    private Integer precio;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private Tipo tipo;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "especie_id", nullable = false)
    private Especie especie;
}