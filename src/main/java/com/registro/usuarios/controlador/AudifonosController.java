package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Audifonos;
import com.registro.usuarios.servicio.AudifonosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audifonos-form")
public class AudifonosController {

    @Autowired
    private AudifonosService audifonosService;

    @GetMapping
    public List<Audifonos> getAllAudifonos() {
        return audifonosService.getAllAudifonos();
    }

    @GetMapping("/{id}")
    public Audifonos getAudifonoById(@PathVariable int id) {
        return audifonosService.getAudifonoById(id);
    }

    @PostMapping
    public Audifonos createAudifono(@RequestBody Audifonos audifonos) {
        return audifonosService.createOrUpdateAudifono(audifonos);
    }

    @PutMapping("/{id}")
    public Audifonos updateAudifono(@PathVariable int id, @RequestBody Audifonos audifonos) {
        audifonos.setId(id);
        return audifonosService.createOrUpdateAudifono(audifonos);
    }

    @DeleteMapping("/{id}")
    public void deleteAudifono(@PathVariable int id) {
        audifonosService.deleteAudifono(id);
    }
}


