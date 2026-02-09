package es.campusfp.webapp.repository;

import es.campusfp.webapp.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    
    List<Ruta> findByActivaTrue();
    
    List<Ruta> findByZona(String zona);
    
    @Query("SELECT r FROM Ruta r LEFT JOIN FETCH r.asignaciones WHERE r.id = :id")
    Optional<Ruta> findByIdWithAsignaciones(Long id);
}
