package com.registro.usuarios.controlador;

// src/main/java/com/example/ecommerce/controller/CelularController.java


import com.registro.usuarios.modelo.Celular;
import com.registro.usuarios.servicio.CelularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/celulares")
public class CelularController {

    @Autowired
    private CelularService celularService;

    @GetMapping
    public List<Celular> getAllCelulares() {
        return celularService.getAllCelulares();
    }

    @GetMapping("/{id}")
    public Celular getCelularById(@PathVariable Long id) {
        return celularService.getCelularById(id);
    }

    @PostMapping
    public Celular createCelular(@RequestBody Celular celular) {
        return celularService.saveCelular(celular);
    }

    @PutMapping("/{id}")
    public Celular updateCelular(@PathVariable Long id, @RequestBody Celular celular) {
        return celularService.updateCelular(id, celular);
    }

    @DeleteMapping("/{id}")
    public void deleteCelular(@PathVariable Long id) {
        celularService.deleteCelular(id);
    }
}
