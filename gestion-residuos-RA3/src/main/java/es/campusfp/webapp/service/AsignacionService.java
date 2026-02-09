package es.campusfp.webapp.service;

import es.campusfp.webapp.model.Asignaciones;
import es.campusfp.webapp.repository.AsignacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AsignacionService {

    private final AsignacionRepository asignacionRepository;

    public List<Asignaciones> listarTodas() {
        return asignacionRepository.findAll();
    }

    public List<Asignaciones> listarPorCamion(Long camionId) {
        return asignacionRepository.findByCamionId(camionId);
    }

    public List<Asignaciones> listarPorRuta(Long rutaId) {
        return asignacionRepository.findByRutaId(rutaId);
    }

    public Optional<Asignaciones> buscarPorId(Long id) {
        return asignacionRepository.findById(id);
    }

    public Asignaciones crear(Asignaciones asignacion) {
        return asignacionRepository.save(asignacion);
    }

    public void eliminar(Long id) {
        asignacionRepository.deleteById(id);
    }
}
