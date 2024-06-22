package com.registro.usuarios.servicio;


import com.registro.usuarios.modelo.Celular;
import com.registro.usuarios.repositorio.CelularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CelularService {
    @Autowired
    private CelularRepository celularRepository;

    public List<Celular> getAllCelulares() {
        return celularRepository.findAll();
    }

    public Celular getCelularById(Long id) {
        return celularRepository.findById(id).orElse(null);
    }

    public Celular saveCelular(Celular celular) {
        return celularRepository.save(celular);
    }

    public void deleteCelular(Long id) {
        celularRepository.deleteById(id);
    }

    public Celular updateCelular(Long id, Celular celular) {
        return null;
    }
}
