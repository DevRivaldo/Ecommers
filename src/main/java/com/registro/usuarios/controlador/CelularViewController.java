package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Celular;
import com.registro.usuarios.servicio.CelularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/celulares")
public class CelularViewController {

    @Autowired
    private CelularService celularService;

    @GetMapping
    public String getAllCelulares(Model model) {
        List<Celular> celulares = celularService.getAllCelulares();
        model.addAttribute("celulares", celulares);
        return "celulares";
    }

    @GetMapping("/new")
    public String createCelularForm(Model model) {
        model.addAttribute("celular", new Celular());
        return "celulars-form";
    }

    @PostMapping
    public String createCelular(@ModelAttribute Celular celular, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "celulars-form";
        }

        try {
            celularService.createOrUpdateCelular(celular);
            redirectAttributes.addFlashAttribute("success", "El celular se ha guardado exitosamente.");
            return "redirect:/celulares";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el celular. Asegúrese de que todos los campos requeridos estén completos.");
            return "redirect:/celulares/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
            return "redirect:/celulares/new";
        }
    }

    @GetMapping("/edit/{id}")
    public String editCelularForm(@PathVariable Long id, Model model) {
        Celular celular = celularService.findById(id);
        model.addAttribute("celular", celular);
        return "celulars-form";
    }

    @PostMapping("/edit/{id}")
    public String updateCelular(@PathVariable Long id, @ModelAttribute Celular celular, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "celulars-form";
        }

        try {
            celular.setId(Math.toIntExact(id));
            celularService.createOrUpdateCelular(celular);
            redirectAttributes.addFlashAttribute("success", "El celular se ha actualizado exitosamente.");
            return "redirect:/celulares";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar el celular. Asegúrese de que todos los campos requeridos estén completos.");
            return "redirect:/celulares/edit/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
            return "redirect:/celulares/edit/" + id;
        }
    }



    @GetMapping("/delete/{id}")
    public String deleteCelular(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            celularService.deleteCelular(Math.toIntExact(id));
            redirectAttributes.addFlashAttribute("success", "El celular se ha eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al eliminar el celular. Por favor, inténtelo de nuevo más tarde.");
        }
        return "redirect:/celulares";
    }

}
