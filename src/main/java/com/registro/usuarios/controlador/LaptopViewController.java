package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Celular;
import com.registro.usuarios.modelo.Laptop;
import com.registro.usuarios.servicio.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
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
    public String createLaptop(
            @RequestParam("archivo") MultipartFile archivo,
            @Valid @ModelAttribute("laptop, ") Laptop  laptop,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return "Laptops-form";
            }

            String ruta = "D:\\2024 -1\\Desarrollo de aplicaciones en web\\PoryectoSB\\Ecommerce\\src\\main\\resources\\static\\img";

            int index = archivo.getOriginalFilename().lastIndexOf(".");
            String extension = archivo.getOriginalFilename().substring(index + 1);
            String nombreFoto = Calendar.getInstance().getTimeInMillis() + "." + extension;
            Path rutaAbsoluta = Paths.get(ruta + "\\" + nombreFoto);

            if (archivo.isEmpty()) {
                model.addAttribute("errorImagenMsg", "La imagen es requerida");
                return "Laptops-form";
            }

            if (!validarExtension(archivo)) {
                model.addAttribute("errorImagenMsg", "La extensión no es válida");
                return "Laptops-form";
            }

            if (archivo.getSize() >= 15000000) {
                model.addAttribute("errorImagenMsg", "El peso excede 15MB");
                return "Laptops-form";
            }

            Files.write(rutaAbsoluta, archivo.getBytes());
            laptop.setImagenUrl(nombreFoto);

            laptopService.createOrUpdateLaptop(laptop);
            redirectAttributes.addFlashAttribute("success", "El celular se ha guardado exitosamente.");
            return "redirect:/laptops";

        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el celular. Asegúrese de que todos los campos requeridos estén completos.");
            return "redirect:/laptops/new";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
            return "redirect:/laptops/new";
        }
    }




    // Método para validar la extensión del archivo
    private boolean validarExtension(MultipartFile archivo) {
        String[] extensionesValidas = {"jpg", "jpeg", "png", "gif"};
        String nombreArchivo = archivo.getOriginalFilename();
        if (nombreArchivo != null) {
            int index = nombreArchivo.lastIndexOf(".");
            if (index > 0) {
                String extension = nombreArchivo.substring(index + 1).toLowerCase();
                for (String ext : extensionesValidas) {
                    if (ext.equals(extension)) {
                        return true;  // La extensión es válida
                    }
                }
            }
        }
        return false;  // La extensión no es válida o no se proporcionó un archivo
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

    @GetMapping("/{id}")
    public String getLaptopDetalles(@PathVariable Long id, Model model) {
        Laptop laptop = laptopService.findById(id);
        model.addAttribute("laptop", laptop);
        return "laptops-detalles";
    }

}
