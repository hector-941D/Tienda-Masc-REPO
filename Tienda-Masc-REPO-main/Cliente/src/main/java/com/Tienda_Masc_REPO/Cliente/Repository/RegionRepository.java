package com.Tienda_Masc_REPO.Cliente.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Tienda_Masc_REPO.Cliente.Model.Cliente;
import com.Tienda_Masc_REPO.Cliente.Model.Region;


@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
        Optional<Cliente> findByRunCliente(String rutCliente);
}
