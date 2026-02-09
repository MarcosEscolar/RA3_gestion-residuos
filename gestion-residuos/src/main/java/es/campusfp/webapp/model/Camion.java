package es.campusfp.webapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "camiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Camion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, length = 10)
    private String matricula;
    
    @Column(nullable = false, length = 100)
    private String modelo;
    
    @Column(name = "capacidad_kg", nullable = false, precision = 10, scale = 2)
    private BigDecimal capacidadKg;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCamion estado = EstadoCamion.DISPONIBLE;
    
    @Column(name = "fecha_alta", nullable = false)
    private LocalDate fechaAlta;
    
    @Column(nullable = false)
    private Boolean activo = true;
    
    @OneToMany(mappedBy = "camion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Asignacion> asignaciones = new ArrayList<>();
    
    @PrePersist
    protected void onCreate() {
        if (fechaAlta == null) {
            fechaAlta = LocalDate.now();
        }
        if (activo == null) {
            activo = true;
        }
        if (estado == null) {
            estado = EstadoCamion.DISPONIBLE;
        }
    }
    
    // Métodos auxiliares para gestionar la relación bidireccional
    public void addAsignacion(Asignacion asignacion) {
        asignaciones.add(asignacion);
        asignacion.setCamion(this);
    }
    
    public void removeAsignacion(Asignacion asignacion) {
        asignaciones.remove(asignacion);
        asignacion.setCamion(null);
    }
}
