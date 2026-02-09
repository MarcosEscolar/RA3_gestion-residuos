package es.campusfp.webapp.service;

import es.campusfp.webapp.model.Ruta;
import es.campusfp.webapp.repository.RutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RutaService {
    
    private final RutaRepository rutaRepository;
    
    @Transactional(readOnly = true)
    public List<Ruta> findAll() {
        return rutaRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Ruta> findActivas() {
        return rutaRepository.findByActivaTrue();
    }
    
    @Transactional(readOnly = true)
    public List<Ruta> findByZona(String zona) {
        return rutaRepository.findByZona(zona);
    }
    
    @Transactional(readOnly = true)
    public Optional<Ruta> findById(Long id) {
        return rutaRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Optional<Ruta> findByIdWithAsignaciones(Long id) {
        return rutaRepository.findByIdWithAsignaciones(id);
    }
    
    public Ruta save(Ruta ruta) {
        return rutaRepository.save(ruta);
    }
    
    public void deleteById(Long id) {
        rutaRepository.deleteById(id);
    }
}
