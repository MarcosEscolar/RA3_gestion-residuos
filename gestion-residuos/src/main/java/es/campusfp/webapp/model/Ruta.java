package es.campusfp.webapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rutas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 100)
    private String zona;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "dia_semana", nullable = false)
    private DiaSemana diaSemana;
    
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;
    
    @Column(name = "hora_fin", nullable = false)
    private LocalTime horaFin;
    
    @Column(nullable = false)
    private Boolean activa = true;
    
    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asignacion> asignaciones = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        if (activa == null) {
            activa = true;
        }
    }
    
    // Métodos auxiliares para gestionar la relación bidireccional
    public void addAsignacion(Asignacion asignacion) {
        asignaciones.add(asignacion);
        asignacion.setRuta(this);
    }
    
    public void removeAsignacion(Asignacion asignacion) {
        asignaciones.remove(asignacion);
        asignacion.setRuta(null);
    }
}
