package com.registro.usuarios.controlador;

// src/main/java/com/example/ecommerce/controller/LaptopController.java


import com.registro.usuarios.modelo.Laptop;
import com.registro.usuarios.servicio.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public List<Laptop> getAllLaptops() {
        return laptopService.getAllLaptops();
    }

    @GetMapping("/{id}")
    public Laptop getLaptopById(@PathVariable Long id) {
        return laptopService.getLaptopById(id);
    }

    @PostMapping
    public Laptop createLaptop(@RequestBody Laptop laptop) {
        return laptopService.saveLaptop(laptop);
    }

    @PutMapping("/{id}")
    public Laptop updateLaptop(@PathVariable Long id, @RequestBody Laptop laptop) {
        return laptopService.updateLaptop(id, laptop);
    }

    @DeleteMapping("/{id}")
    public void deleteLaptop(@PathVariable Long id) {
        laptopService.deleteLaptop(id);
    }
}
