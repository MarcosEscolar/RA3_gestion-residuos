package es.campusfp.webapp.repository;

import es.campusfp.webapp.model.Camion;
import es.campusfp.webapp.model.EstadoCamion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {
    
    List<Camion> findByEstado(EstadoCamion estado);
    
    List<Camion> findByActivoTrue();
    
    Optional<Camion> findByMatricula(String matricula);
    
    @Query("SELECT c FROM Camion c LEFT JOIN FETCH c.asignaciones WHERE c.id = :id")
    Optional<Camion> findByIdWithAsignaciones(Long id);
}
