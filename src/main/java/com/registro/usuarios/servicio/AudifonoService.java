package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Audifono;
import com.registro.usuarios.repositorio.AudifonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AudifonoService {
    @Autowired
    private AudifonoRepository audifonoRepository;

    public List<Audifono> getAllAudifonos() {
        return audifonoRepository.findAll();
    }

    public Audifono getAudifonoById(Long id) {
        return audifonoRepository.findById(id).orElseThrow(() -> null);
    }

    public Audifono saveAudifono(Audifono audifono) {
        return audifonoRepository.save(audifono);
    }

    public Audifono updateAudifono(Long id, Audifono audifonoDetails) {
        Audifono audifono = audifonoRepository.findById(id)
                .orElseThrow(() -> null);

        audifono.setMarca(audifonoDetails.getMarca());
        audifono.setModelo(audifonoDetails.getModelo());
        audifono.setPrecio(audifonoDetails.getPrecio());

        return audifonoRepository.save(audifono);
    }

    public void deleteAudifono(Long id) {
        audifonoRepository.deleteById(id);
    }
}