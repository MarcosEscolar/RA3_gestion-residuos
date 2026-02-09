package es.campusfp.webapp.repository;

import es.campusfp.webapp.model.Camiones;
import es.campusfp.webapp.model.EstadoCamion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CamionRepository extends JpaRepository<Camiones, Long> {
    
    List<Camiones> findByEstado(EstadoCamion estado);
    
    List<Camiones> findByActivoTrue();
}
