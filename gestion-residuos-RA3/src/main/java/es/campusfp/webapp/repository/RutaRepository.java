package es.campusfp.webapp.repository;

import es.campusfp.webapp.model.Rutas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepository extends JpaRepository<Rutas, Long> {
    
    List<Rutas> findByActivaTrue();
    
    List<Rutas> findByZona(String zona);
}
