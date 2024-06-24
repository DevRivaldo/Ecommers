package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Celular;
import com.registro.usuarios.repositorio.CelularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CelularService {

    @Autowired
    private CelularRepository celularRepository;

    public List<Celular> getAllCelulares() {
        return celularRepository.findAll();
    }

    public Celular getCelularById(int id) {
        return celularRepository.findById(id).orElse(null);
    }

    @Transactional
    public Celular createOrUpdateCelular(Celular celular) {
        return celularRepository.save(celular);
    }

    public void deleteCelular(int id) {
        celularRepository.deleteById(id);
    }

    public Celular findById(Long id) {
        return celularRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Celular findById(int id) {
        return celularRepository.findById(id).orElse(null);
    }
}
