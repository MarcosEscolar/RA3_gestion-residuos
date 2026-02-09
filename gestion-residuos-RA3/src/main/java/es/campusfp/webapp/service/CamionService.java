package es.campusfp.webapp.service;

import es.campusfp.webapp.model.Camiones;
import es.campusfp.webapp.model.EstadoCamion;
import es.campusfp.webapp.repository.CamionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CamionService {

    private final CamionRepository camionRepository;

    public List<Camiones> listarTodos() {
        return camionRepository.findAll();
    }

    public List<Camiones> listarPorEstado(EstadoCamion estado) {
        return camionRepository.findByEstado(estado);
    }

    public List<Camiones> listarActivos() {
        return camionRepository.findByActivoTrue();
    }

    public Optional<Camiones> buscarPorId(Long id) {
        return camionRepository.findById(id);
    }

    public Camiones guardar(Camiones camion) {
        return camionRepository.save(camion);
    }

    public void eliminar(Long id) {
        camionRepository.deleteById(id);
    }
}
