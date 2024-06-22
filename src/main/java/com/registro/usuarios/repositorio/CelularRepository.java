package com.registro.usuarios.repositorio;

import com.registro.usuarios.modelo.Celular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CelularRepository extends JpaRepository<Celular, Long> {
}
