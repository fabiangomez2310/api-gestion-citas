package com.fabiangomez.apigestioncitas.api_gestion_citas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fabiangomez.apigestioncitas.api_gestion_citas.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByNumeroDocumento(String numeroDocumento);
}
