package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Laptop;
import com.registro.usuarios.repositorio.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService {
    @Autowired
    private LaptopRepository laptopRepository;

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Laptop getLaptopById(Long id) {
        return laptopRepository.findById(id).orElse(null);
    }

    public Laptop saveLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public void deleteLaptop(Long id) {
        laptopRepository.deleteById(id);
    }

    public Laptop updateLaptop(Long id, Laptop laptop) {
        return null;
    }
}
