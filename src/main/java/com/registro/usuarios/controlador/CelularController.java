package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Celular;
import com.registro.usuarios.servicio.CelularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/celular-form")
public class CelularController {

    @Autowired
    private CelularService celularService;

    @GetMapping
    public List<Celular> getAllCelulares() {
        return celularService.getAllCelulares();
    }

    @GetMapping("/{id}")
    public Celular getCelularById(@PathVariable int id) {
        return celularService.getCelularById(id);
    }

    @PostMapping
    public Celular createCelular(@RequestBody Celular celular) {
        return celularService.createOrUpdateCelular(celular);
    }

    @PutMapping("/{id}")
    public Celular updateCelular(@PathVariable int id, @RequestBody Celular celular) {
        celular.setId(id);
        return celularService.createOrUpdateCelular(celular);
    }

    @DeleteMapping("/{id}")
    public void deleteCelular(@PathVariable int id) {
        celularService.deleteCelular(id);
    }

}
