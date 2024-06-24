package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Laptop;
import com.registro.usuarios.servicio.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/laptops")
public class LaptopViewController {

    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public String getAllLaptops(Model model) {
        List<Laptop> laptops = laptopService.getAllLaptops();
        model.addAttribute("laptops", laptops);
        return "laptops";
    }

    @GetMapping("/new")
    public String createLaptopForm(Model model) {
        model.addAttribute("laptop", new Laptop());
        return "laptops-form";
    }

    @PostMapping
    public String createLaptop(@ModelAttribute Laptop laptop, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "laptops-form";
        }

        try {
            laptopService.createOrUpdateLaptop(laptop);
            redirectAttributes.addFlashAttribute("success", "La laptop se ha guardado exitosamente.");
            return "redirect:/laptops";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la laptop. Asegúrese de que todos los campos requeridos estén completos.");
            return "redirect:/laptops/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
            return "redirect:/laptops/new";
        }
    }

    @GetMapping("/edit/{id}")
    public String editLaptopForm(@PathVariable Long id, Model model) {
        Laptop laptop = laptopService.findById(id);
        model.addAttribute("laptop", laptop);
        return "laptops-form";
    }

    @PostMapping("/edit/{id}")
    public String updateLaptop(@PathVariable Long id, @ModelAttribute Laptop laptop, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "laptops-form";
        }

        try {
            laptop.setId(Math.toIntExact(id));
            laptopService.createOrUpdateLaptop(laptop);
            redirectAttributes.addFlashAttribute("success", "La laptop se ha actualizado exitosamente.");
            return "redirect:/laptops";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar la laptop. Asegúrese de que todos los campos requeridos estén completos.");
            return "redirect:/laptops/edit/" + id;
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
            return "redirect:/laptops/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteLaptop(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            laptopService.deleteLaptop(Math.toIntExact(id));
            redirectAttributes.addFlashAttribute("success", "La laptop se ha eliminado exitosamente.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error al eliminar la laptop. Por favor, inténtelo de nuevo más tarde.");
        }
        return "redirect:/laptops";
    }
}
