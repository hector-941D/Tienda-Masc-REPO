package com.Tienda_Masc_REPO.Cliente.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Tienda_Masc_REPO.Cliente.Model.Comuna;

@Repository
public interface ComunaRepository extends JpaRepository<Comuna, Integer> {
}