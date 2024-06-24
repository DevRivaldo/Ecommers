package com.registro.usuarios.repositorio;

import com.registro.usuarios.modelo.Audifonos;
import com.registro.usuarios.modelo.Celular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelularRepository extends JpaRepository<Celular, Integer> {
    Audifonos findBymarca(String marca);
    Audifonos findBymodelo(String modelo);
}

