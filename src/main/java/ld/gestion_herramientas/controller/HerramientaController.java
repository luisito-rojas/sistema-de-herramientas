package ld.gestion_herramientas.controller;

import com.lowagie.text.DocumentException;
import ld.gestion_herramientas.entity.Herramienta;
import ld.gestion_herramientas.repository.HerramientaRepository;
import ld.gestion_herramientas.service.HerramientaService;
import ld.gestion_herramientas.util.paginacion.PageRender;
import ld.gestion_herramientas.util.reportes.HerramientaExporterExcel;
import ld.gestion_herramientas.util.reportes.HerramientaExporterPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class HerramientaController {

    @Autowired
    private HerramientaService herramientaService;
    @Autowired
    private HerramientaRepository herramientaRepository;


    //======================================================================================================================================================
    @GetMapping("/ver/{id}")
    public String verDetallesDelaHerramienta(@PathVariable(value = "id") Long id, Map<String,Object> modelo, RedirectAttributes flash) {
        Herramienta herramienta = herramientaService.findOne(id);
        if(herramienta == null) {
            flash.addFlashAttribute("error", "La herramienta no existe en la base de datos");
            return "redirect:/listar";
        }

        modelo.put("herramienta",herramienta);
        modelo.put("titulo", "Detalles de la Herramienta ");
        return "ver";
    }

    //======================================================================================================================================================

    @GetMapping({"/", "/listar"})
    public String listarHerramienta(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "termino", required = false) String termino,
                                    Model modelo) {
        Pageable pageRequest = PageRequest.of(page, 8);
        Page<Herramienta> herramientas;

        if (termino != null && !termino.isEmpty()) {
            herramientas = herramientaService.buscarPorTermino(termino, pageRequest);
            modelo.addAttribute("termino", termino);
        } else {
            herramientas = herramientaService.findAll(pageRequest);
        }

        PageRender<Herramienta> pageRender = new PageRender<>("/listar", herramientas);

        modelo.addAttribute("titulo", "Listado de Herramientas");
        modelo.addAttribute("herramientas", herramientas);
        modelo.addAttribute("page", pageRender);

        return "listar";
    }

    //======================================================================================================================================================

    @GetMapping("/form")
    public String mostrarFormularioDeRegistrarHerramienta(Map<String,Object> modelo) {
        Herramienta herramienta = new Herramienta();
        modelo.put("herramienta", herramienta);
        modelo.put("titulo", "Registro de Herramientas");
        return "form";
    }

    //======================================================================================================================================================
    @PostMapping("/form")
    public String guardarHerramienta(@Valid Herramienta herramienta, BindingResult result, Model modelo, RedirectAttributes flash, SessionStatus status) {
        if(result.hasErrors()) {
            modelo.addAttribute("titulo", "Registro de Herramienta");
            return "form";
        }

        String mensaje = (herramienta.getId() != null) ? "La herramienta ha sido editada con exito" : "Herramienta registrada con exito";

        herramientaService.save(herramienta);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);
        return "redirect:/listar";
    }

    //======================================================================================================================================================
    @GetMapping("/form/{id}")
    public String editarHerramienta(@PathVariable(value = "id") Long id,Map<String, Object> modelo,RedirectAttributes flash) {
        Herramienta herramienta = null;
        if(id > 0) {
            herramienta = herramientaService.findOne(id);
            if(herramienta == null) {
                flash.addFlashAttribute("error", "El ID de la herramienta no existe en la base de datos");
                return "redirect:/listar";
            }
        }
        else {
            flash.addFlashAttribute("error", "El ID de la herramienta no puede ser cero");
            return "redirect:/listar";
        }

        modelo.put("herramienta",herramienta);
        modelo.put("titulo", "Editar Herramienta");
        return "form";
    }

    //======================================================================================================================================================

    @GetMapping("/eliminar/{id}")
    public String eliminarHerramienta(@PathVariable(value = "id") Long id,RedirectAttributes flash) {
        if(id > 0) {
            herramientaService.delete(id);
            flash.addFlashAttribute("success", "Herramienta eliminado con exito");
        }
        return "redirect:/listar";
    }

    //======================================================================================================================================================

    @GetMapping("/exportarPDF")
    public void exportarListadoDeEmpleadosEnPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Herramientas_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        List<Herramienta> herramientas = herramientaService.findAll();

        HerramientaExporterPDF exporter = new HerramientaExporterPDF(herramientas);
        exporter.exportar(response);
    }



    @GetMapping("/exportarExcel")
    public void exportarListadoDeEmpleadosEnExcel(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/octet-stream");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "Content-Disposition";
        String valor = "attachment; filename=Herramientas_" + fechaActual + ".xlsx";

        response.setHeader(cabecera, valor);


        List<Herramienta> herramientas = herramientaService.findAll();

        HerramientaExporterExcel exporter = new HerramientaExporterExcel(herramientas);
        exporter.exportar(response);
    }

//====================================== Pruebas Unitarias =====================================


//================================================================================================
}
