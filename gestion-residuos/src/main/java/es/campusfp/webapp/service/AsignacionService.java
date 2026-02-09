package es.campusfp.webapp.service;

import es.campusfp.webapp.model.Asignacion;
import es.campusfp.webapp.model.Camion;
import es.campusfp.webapp.model.Ruta;
import es.campusfp.webapp.repository.AsignacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AsignacionService {
    
    private final AsignacionRepository asignacionRepository;
    private final CamionService camionService;
    private final RutaService rutaService;
    
    @Transactional(readOnly = true)
    public List<Asignacion> findAll() {
        return asignacionRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Asignacion> findAllWithDetails() {
        return asignacionRepository.findAllWithDetails();
    }
    
    @Transactional(readOnly = true)
    public List<Asignacion> findByCamionId(Long camionId) {
        return asignacionRepository.findByCamionId(camionId);
    }
    
    @Transactional(readOnly = true)
    public List<Asignacion> findByRutaId(Long rutaId) {
        return asignacionRepository.findByRutaId(rutaId);
    }
    
    @Transactional(readOnly = true)
    public Optional<Asignacion> findById(Long id) {
        return asignacionRepository.findById(id);
    }
    
    public Asignacion crearAsignacion(Long camionId, Long rutaId, LocalDate fechaAsignacion) {
        Camion camion = camionService.findById(camionId)
                .orElseThrow(() -> new IllegalArgumentException("Camión no encontrado"));
        
        Ruta ruta = rutaService.findById(rutaId)
                .orElseThrow(() -> new IllegalArgumentException("Ruta no encontrada"));
        
        Asignacion asignacion = new Asignacion();
        asignacion.setCamion(camion);
        asignacion.setRuta(ruta);
        asignacion.setFechaAsignacion(fechaAsignacion != null ? fechaAsignacion : LocalDate.now());
        
        return asignacionRepository.save(asignacion);
    }
    
    public void deleteById(Long id) {
        asignacionRepository.deleteById(id);
    }
}
