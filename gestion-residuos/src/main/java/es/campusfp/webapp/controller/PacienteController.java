package es.campusfp.webapp.controller;

import es.campusfp.webapp.model.Camion;
import es.campusfp.webapp.model.EstadoCamion;
import es.campusfp.webapp.service.AsignacionService;
import es.campusfp.webapp.service.CamionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/camiones")
@RequiredArgsConstructor
public class PacienteController {
    
    private final CamionService camionService;
    private final AsignacionService asignacionService;
    
    @GetMapping
    public String listarCamiones(Model model) {
        List<Camion> camiones = camionService.findAll();
        
        // Contar rutas por camión
        Map<Long, Long> rutasPorCamion = new HashMap<>();
        for (Camion camion : camiones) {
            long numRutas = asignacionService.findByCamionId(camion.getId()).size();
            rutasPorCamion.put(camion.getId(), numRutas);
        }
        
        model.addAttribute("camiones", camiones);
        model.addAttribute("rutasPorCamion", rutasPorCamion);
        return "productos/camiones";
    }
    
    @GetMapping("/nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("camion", new Camion());
        model.addAttribute("estados", EstadoCamion.values());
        return "productos/camion-form";
    }
    
    @GetMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Camion camion = camionService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Camión no encontrado"));
        model.addAttribute("camion", camion);
        model.addAttribute("estados", EstadoCamion.values());
        return "productos/camion-form";
    }
    
    @PostMapping("/guardar")
    @PreAuthorize("hasRole('ADMIN')")
    public String guardarCamion(@ModelAttribute Camion camion) {
        camionService.save(camion);
        return "redirect:/camiones";
    }
    
    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String eliminarCamion(@PathVariable Long id) {
        camionService.deleteById(id);
        return "redirect:/camiones";
    }
}
