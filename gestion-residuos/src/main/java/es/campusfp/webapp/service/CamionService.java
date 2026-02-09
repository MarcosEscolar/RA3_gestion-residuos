package es.campusfp.webapp.service;

import es.campusfp.webapp.model.Camion;
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
    
    @Transactional(readOnly = true)
    public List<Camion> findAll() {
        return camionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Camion> findByEstado(EstadoCamion estado) {
        return camionRepository.findByEstado(estado);
    }
    
    @Transactional(readOnly = true)
    public List<Camion> findActivos() {
        return camionRepository.findByActivoTrue();
    }
    
    @Transactional(readOnly = true)
    public Optional<Camion> findById(Long id) {
        return camionRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Camion> findByIdWithAsignaciones(Long id) {
        return camionRepository.findByIdWithAsignaciones(id);
    }
    
    public Camion save(Camion camion) {
        return camionRepository.save(camion);
    }
    
    public void deleteById(Long id) {
        camionRepository.deleteById(id);
    }
    
    public boolean existsByMatricula(String matricula) {
        return camionRepository.findByMatricula(matricula).isPresent();
    }
}
