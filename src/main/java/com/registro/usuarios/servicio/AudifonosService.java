package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Audifonos;
import com.registro.usuarios.repositorio.AudifonosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AudifonosService {

    @Autowired
    private AudifonosRepository audifonosRepository;

    public List<Audifonos> getAllAudifonos() {
        return audifonosRepository.findAll();
    }

    public Audifonos getAudifonoById(int id) {
        return audifonosRepository.findById(id).orElse(null);
    }

    @Transactional
    public Audifonos createOrUpdateAudifono(Audifonos audifono) {
        return audifonosRepository.save(audifono);
    }

    public void deleteAudifono(int id) {
        audifonosRepository.deleteById(id);
    }

    public Audifonos findById(Long id) {
        return audifonosRepository.findById(Math.toIntExact(id)).orElse(null);
    }

    public Audifonos findById(int id) {
        return audifonosRepository.findById(id).orElse(null);
    }
}
