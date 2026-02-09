package es.campusfp.webapp.controller;

import es.campusfp.webapp.model.Asignacion;
import es.campusfp.webapp.service.AsignacionService;
import es.campusfp.webapp.service.CamionService;
import es.campusfp.webapp.service.RutaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/asignaciones")
@RequiredArgsConstructor
public class AsignacionController {
    
    private final AsignacionService asignacionService;
    private final CamionService camionService;
    private final RutaService rutaService;
    
    @GetMapping
    public String listarAsignaciones(Model model) {
        List<Asignacion> asignaciones = asignacionService.findAllWithDetails();
        model.addAttribute("asignaciones", asignaciones);
        return "productos/asignaciones";
    }
    
    @GetMapping("/nueva")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("camiones", camionService.findActivos());
        model.addAttribute("rutas", rutaService.findActivas());
        model.addAttribute("fechaActual", LocalDate.now());
        return "productos/asignacion-form";
    }
    
    @PostMapping("/crear")
    public String crearAsignacion(
            @RequestParam Long camionId,
            @RequestParam Long rutaId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaAsignacion) {
        
        asignacionService.crearAsignacion(camionId, rutaId, fechaAsignacion);
        return "redirect:/asignaciones";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarAsignacion(@PathVariable Long id) {
        asignacionService.deleteById(id);
        return "redirect:/asignaciones";
    }
}
