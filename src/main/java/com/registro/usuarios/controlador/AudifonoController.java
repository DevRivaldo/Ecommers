package com.registro.usuarios.controlador;

// src/main/java/com/example/ecommerce/controller/AudifonoController.java


import com.registro.usuarios.modelo.Audifono;
import com.registro.usuarios.servicio.AudifonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audifonos")
public class AudifonoController {

    @Autowired
    private AudifonoService audifonoService;

    @GetMapping
    public List<Audifono> getAllAudifonos() {
        return audifonoService.getAllAudifonos();
    }

    @GetMapping("/{id}")
    public Audifono getAudifonoById(@PathVariable Long id) {
        return audifonoService.getAudifonoById(id);
    }

    @PostMapping
    public Audifono createAudifono(@RequestBody Audifono audifono) {
        return audifonoService.saveAudifono(audifono);
    }

    @PutMapping("/{id}")
    public Audifono updateAudifono(@PathVariable Long id, @RequestBody Audifono audifono) {
        return audifonoService.updateAudifono(id, audifono);
    }

    @DeleteMapping("/{id}")
    public void deleteAudifono(@PathVariable Long id) {
        audifonoService.deleteAudifono(id);
    }

}
