package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Audifonos;
import com.registro.usuarios.servicio.AudifonosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/audifonos")
public class AudifonosViewController {

    @Autowired
    private AudifonosService audifonosService;

    @GetMapping
    public String getAllAudifonos(Model model) {
        List<Audifonos> audifonos = audifonosService.getAllAudifonos();
        model.addAttribute("audifonos", audifonos);
        return "audifonos";
    }

    @GetMapping("/new")
    public String createAudifonoForm(Model model) {
        model.addAttribute("audifono", new Audifonos());
        return "audifonos-form";
    }

    @PostMapping
    public String createAudifono(@ModelAttribute Audifonos audifono, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "audifonos-form";
        }

        try {
            audifonosService.createOrUpdateAudifono(audifono);
            redirectAttributes.addFlashAttribute("success", "El audífono se ha guardado exitosamente.");
            return "redirect:/audifonos";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el audífono. Asegúrese de que todos los campos requeridos estén completos.");
            return "redirect:/audifonos/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
            return "redirect:/audifonos/new";
        }
    }

    @GetMapping("/edit/{id}")
    public String editAudifonoForm(@PathVariable Long id, Model model) {
        Audifonos audifono = audifonosService.findById(id);
        model.addAttribute("audifono", audifono);
        return "audifonos-form";
    }

    @PostMapping("/edit/{id}")
    public String updateAudifono(@PathVariable Long id, @ModelAttribute Audifonos audifono, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "audifonos-form";
        }

        try {
            audifono.setId(Math.toIntExact(id));
            audifonosService.createOrUpdateAudifono(audifono);
            redirectAttributes.addFlashAttribute("success", "El audífono se ha actualizado exitosamente.");
            return "redirect:/audifonos";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el audífono. Asegúrese de que todos los campos requeridos estén completos.");
            return "redirect:/audifonos/edit/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
            return "redirect:/audifonos/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteAudifono(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            audifonosService.deleteAudifono(Math.toIntExact(id));
            redirectAttributes.addFlashAttribute("success", "El audífono se ha eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al eliminar el audífono. Por favor, inténtelo de nuevo más tarde.");
        }
        return "redirect:/audifonos";
    }

    @GetMapping("/{id}")
    public String getAudifonoDetalles(@PathVariable Long id, Model model) {
        Audifonos audifono = audifonosService.findById(id);
        model.addAttribute("audifono", audifono);
        return "audifonos-detalles";
    }

}
