package com.registro.usuarios.repositorio;

import com.registro.usuarios.modelo.Audifonos;
import com.registro.usuarios.modelo.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepository extends JpaRepository<Laptop, Integer> {
    Audifonos findBymarca(String marca);
    Audifonos findBymodelo(String modelo);
}
