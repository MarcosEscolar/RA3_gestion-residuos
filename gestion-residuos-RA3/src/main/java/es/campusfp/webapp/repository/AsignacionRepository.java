package es.campusfp.webapp.repository;

import es.campusfp.webapp.model.Asignaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignaciones, Long> {
    
    List<Asignaciones> findByCamionId(Long camionId);
    
    List<Asignaciones> findByRutaId(Long rutaId);
}
