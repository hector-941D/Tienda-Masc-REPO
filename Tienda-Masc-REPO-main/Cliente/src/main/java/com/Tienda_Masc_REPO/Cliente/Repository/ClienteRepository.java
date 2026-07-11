package com.Tienda_Masc_REPO.Cliente.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tienda_Masc_REPO.Cliente.Model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByRutCliente(String rutCliente);
    
}