package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Laptop;
import com.registro.usuarios.repositorio.LaptopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Laptop getLaptopById(int id) {
        return laptopRepository.findById(id).orElse(null);
    }

    @Transactional
    public Laptop createOrUpdateLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    public void deleteLaptop(int id) {
        laptopRepository.deleteById(id);
    }

    public Laptop findById(Long id) {
        return laptopRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Laptop findById(int id) {
        return laptopRepository.findById(id).orElse(null);
    }
}
