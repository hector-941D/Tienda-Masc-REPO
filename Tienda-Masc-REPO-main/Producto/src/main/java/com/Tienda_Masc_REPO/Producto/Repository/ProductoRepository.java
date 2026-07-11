package com.Tienda_Masc_REPO.Producto.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tienda_Masc_REPO.Producto.Model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByTipoNombreDelTipo(String nombreDelTipo);
}