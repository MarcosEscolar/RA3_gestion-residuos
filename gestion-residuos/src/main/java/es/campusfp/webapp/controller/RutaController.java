package es.campusfp.webapp.controller;

import es.campusfp.webapp.model.DiaSemana;
import es.campusfp.webapp.model.Ruta;
import es.campusfp.webapp.service.AsignacionService;
import es.campusfp.webapp.service.RutaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rutas")
@RequiredArgsConstructor
public class RutaController {
    
    private final RutaService rutaService;
    private final AsignacionService asignacionService;
    
    @GetMapping
    public String listarRutas(Model model) {
        List<Ruta> rutas = rutaService.findAll();
        
        // Contar camiones por ruta
        Map<Long, Long> camionesPorRuta = new HashMap<>();
        for (Ruta ruta : rutas) {
            long numCamiones = asignacionService.findByRutaId(ruta.getId()).size();
            camionesPorRuta.put(ruta.getId(), numCamiones);
        }
        
        model.addAttribute("rutas", rutas);
        model.addAttribute("camionesPorRuta", camionesPorRuta);
        return "productos/rutas";
    }
    
    @GetMapping("/nueva")
    @PreAuthorize("hasRole('ADMIN')")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("ruta", new Ruta());
        model.addAttribute("diasSemana", DiaSemana.values());
        return "productos/ruta-form";
    }
    
    @GetMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Ruta ruta = rutaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ruta no encontrada"));
        model.addAttribute("ruta", ruta);
        model.addAttribute("diasSemana", DiaSemana.values());
        return "productos/ruta-form";
    }
    
    @PostMapping("/guardar")
    @PreAuthorize("hasRole('ADMIN')")
    public String guardarRuta(@ModelAttribute Ruta ruta) {
        rutaService.save(ruta);
        return "redirect:/rutas";
    }
    
    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String eliminarRuta(@PathVariable Long id) {
        rutaService.deleteById(id);
        return "redirect:/rutas";
    }
}
