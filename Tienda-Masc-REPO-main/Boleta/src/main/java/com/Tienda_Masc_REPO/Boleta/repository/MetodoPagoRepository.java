package com.Tienda_Masc_REPO.Boleta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tienda_Masc_REPO.Boleta.model.MetodoPago;

@Repository
public interface MetodoPagoRepository extends JpaRepository<MetodoPago, Integer> {
    
}
