package es.campusfp.webapp.controller;

import es.campusfp.webapp.model.Camiones;
import es.campusfp.webapp.service.CamionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/camiones")
@RequiredArgsConstructor
public class PacienteController {

    private final CamionService camionService;

    @GetMapping
    public String listarCamiones(Model model) {
        List<Camiones> camiones = camionService.listarTodos();
        
        // Agregar el número de rutas asignadas a cada camión
        camiones.forEach(camion -> {
            int numRutas = camion.getAsignaciones() != null ? camion.getAsignaciones().size() : 0;
            model.addAttribute("numRutas_" + camion.getId(), numRutas);
        });
        
        model.addAttribute("camiones", camiones);
        return "productos/camiones";
    }

    @GetMapping("/{id}")
    public String verDetalleCamion(@PathVariable Long id, Model model) {
        return camionService.buscarPorId(id)
                .map(camion -> {
                    model.addAttribute("camion", camion);
                    return "productos/camion-detalle";
                })
                .orElse("redirect:/camiones");
    }
}
