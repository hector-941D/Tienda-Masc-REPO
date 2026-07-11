package com.Tienda_Masc_REPO.Producto.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tienda_Masc_REPO.Producto.Model.Tipo;

@Repository
public interface TipoRepository extends JpaRepository<Tipo, Integer> {

}