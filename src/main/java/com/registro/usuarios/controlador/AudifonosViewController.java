package com.registro.usuarios.controlador;

import com.registro.usuarios.modelo.Audifonos;
import com.registro.usuarios.servicio.AudifonosService;
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
    public String createAudifono(
            @RequestParam("archivo") MultipartFile archivo,
            @Valid @ModelAttribute("audifono") Audifonos audifono,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                return "views/formulario/audifono";
            }

            String ruta = "D:\\2024 -1\\Desarrollo de aplicaciones en web\\PoryectoSB\\Ecommerce\\src\\main\\resources\\static\\img";

            int index = archivo.getOriginalFilename().indexOf(".");
            String extension = "." + archivo.getOriginalFilename().substring(index + 1);
            String nombreFoto = Calendar.getInstance().getTimeInMillis() + extension;
            Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto);

            if (archivo.isEmpty()) {
                model.addAttribute("errorImagenMsg", "La imagen es requerida");
                return "views/formulario/audifono";
            }

            if (!this.validarExtension(archivo)) {
                model.addAttribute("errorImagenMsg", "La extensión no es válida");
                return "views/formulario/audifono";
            }

            if (archivo.getSize() >= 15000000) {
                model.addAttribute("errorImagenMsg", "El peso excede 15MB");
                return "views/formulario/audifono";
            }

            Files.write(rutaAbsoluta, archivo.getBytes());
            audifono.setImagenUrl(nombreFoto);

            this.audifonosService.createOrUpdateAudifono(audifono);
            redirectAttributes.addFlashAttribute("success", "El audífono se ha guardado exitosamente.");
            return "redirect:/audifonos";

        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el audífono. Asegúrese de que todos los campos requeridos estén completos.");
            return "redirect:/audifonos/nuevo";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtelo de nuevo más tarde.");
            return "redirect:/audifonos/nuevo";
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
