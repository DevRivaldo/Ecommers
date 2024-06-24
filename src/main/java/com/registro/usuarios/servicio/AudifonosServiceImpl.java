/*package com.registro.usuarios.servicio;

import com.registro.usuarios.modelo.Audifonos;
import com.registro.usuarios.repositorio.AudifonosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AudifonosServiceImpl extends AudifonosService {

    @Autowired
    private AudifonosRepository audifonosRepository;

    @Override
    @Transactional
    public Audifonos createOrUpdateAudifono(Audifonos audifono) {
        audifonosRepository.save(audifono);
        return audifono;
    }

    @Override
    public Audifonos getAudiophoneById(int id) {
        Optional<Audifonos> optionalAudifono = audifonosRepository.findById((int) id);
        return optionalAudifono.orElse(null);
    }

    @Override
    public List<Audifonos> getAllAudiophones() {
        return audifonosRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAudifono(int id) {
        audifonosRepository.deleteById((int) id);
    }

    @Override
    public Audifonos findById(Long id) {
        Optional<Audifonos> optionalAudifono = audifonosRepository.findById(Math.toIntExact(id));
        return optionalAudifono.orElse(null);
    }
}
*/