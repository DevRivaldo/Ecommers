package com.registro.usuarios.repositorio;

import com.registro.usuarios.modelo.Audifonos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudifonosRepository extends JpaRepository<Audifonos, Integer> {
    Audifonos findBynombre(String nombre);
    Audifonos findBydescripcion(String descripcion);
}
