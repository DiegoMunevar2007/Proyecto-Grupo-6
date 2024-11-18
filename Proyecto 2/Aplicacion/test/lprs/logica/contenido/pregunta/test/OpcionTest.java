package lprs.logica.contenido.pregunta.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.pregunta.Opcion;

class OpcionTest {

    private Opcion opcion;

    @BeforeEach
    void setUp() {
        // Inicializar un objeto Opcion antes de cada prueba
        opcion = new Opcion("Opción 1", "Explicación de la opción 1");
    }

    @AfterEach
    void tearDown() {
        // Limpiar la referencia después de cada prueba
        opcion = null;
    }

    @Test
    void testGetOpcion() {
        assertEquals("Opción 1", opcion.getOpcion());
    }

    @Test
    void testSetOpcion() {
        opcion.setOpcion("Nueva Opción");
        assertEquals("Nueva Opción", opcion.getOpcion());
    }

    @Test
    void testGetExplicacion() {
        assertEquals("Explicación de la opción 1", opcion.getExplicacion());
    }

    @Test
    void testSetExplicacion() {
        opcion.setExplicacion("Nueva Explicación");
        assertEquals("Nueva Explicación", opcion.getExplicacion());
    }
}
