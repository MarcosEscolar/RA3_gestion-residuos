package es.campusfp.webapp.repository;

import es.campusfp.webapp.model.Asignacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsignacionRepository extends JpaRepository<Asignacion, Long> {
    
    List<Asignacion> findByCamionId(Long camionId);
    
    List<Asignacion> findByRutaId(Long rutaId);
    
    @Query("SELECT a FROM Asignacion a JOIN FETCH a.camion JOIN FETCH a.ruta")
    List<Asignacion> findAllWithDetails();
}
