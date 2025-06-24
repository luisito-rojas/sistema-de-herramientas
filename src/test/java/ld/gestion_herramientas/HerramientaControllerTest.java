package ld.gestion_herramientas;


import ld.gestion_herramientas.controller.HerramientaController;
import ld.gestion_herramientas.entity.Herramienta;
import ld.gestion_herramientas.service.HerramientaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HerramientaController.class)
class HerramientaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HerramientaService herramientaService;

    @Test
    void testVerDetallesHerramienta_Existente() throws Exception {
        Herramienta herramienta = new Herramienta();
        herramienta.setId(1L);
        herramienta.setModelo("Taladro Bosch");

        Mockito.when(herramientaService.findOne(1L)).thenReturn(herramienta);

        mockMvc.perform(get("/ver/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ver"))
                .andExpect(model().attributeExists("herramienta"))
                .andExpect(model().attribute("herramienta", herramienta))
                .andExpect(model().attribute("titulo", "Detalles de la Herramienta Taladro Bosch"));
    }

    @Test
    void testVerDetallesHerramienta_NoExistente() throws Exception {
        Mockito.when(herramientaService.findOne(99L)).thenReturn(null);

        mockMvc.perform(get("/ver/99"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/listar"))
                .andExpect(flash().attribute("error", "La herramienta no existe en la base de datos"));
    }
}
