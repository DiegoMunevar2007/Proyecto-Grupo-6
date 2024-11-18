package lprs.logica.contenido.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.Seccion;

class SeccionTest {

    private Seccion seccion;

    @BeforeEach
    void setUp() {
        // Inicializamos un objeto Seccion antes de cada prueba
        seccion = new Seccion(1, "Título", "Tipo", "Descripción", "Contenido", 
                              "Ejemplo", "Explicación", "Pista", "Resultado esperado");
    }

    @AfterEach
    void tearDown() {
        // Limpiamos el objeto Seccion después de cada prueba
        seccion = null;
    }

    @Test
    void testGetNumero() {
        assertEquals(1, seccion.getNumero());
    }

    @Test
    void testSetNumero() {
        seccion.setNumero(5);
        assertEquals(5, seccion.getNumero());
    }

    @Test
    void testGetTitulo() {
        assertEquals("Título", seccion.getTitulo());
    }

    @Test
    void testSetTitulo() {
        seccion.setTitulo("Nuevo Título");
        assertEquals("Nuevo Título", seccion.getTitulo());
    }

    @Test
    void testGetTipo() {
        assertEquals("Tipo", seccion.getTipo());
    }

    @Test
    void testSetTipo() {
        seccion.setTipo("Práctica");
        assertEquals("Práctica", seccion.getTipo());
    }

    @Test
    void testGetDescripcion() {
        assertEquals("Descripción", seccion.getDescripcion());
    }

    @Test
    void testSetDescripcion() {
        seccion.setDescripcion("Nueva descripción");
        assertEquals("Nueva descripción", seccion.getDescripcion());
    }

    @Test
    void testGetContenido() {
        assertEquals("Contenido", seccion.getContenido());
    }

    @Test
    void testSetContenido() {
        seccion.setContenido("Nuevo contenido");
        assertEquals("Nuevo contenido", seccion.getContenido());
    }

    @Test
    void testGetEjemplo() {
        assertEquals("Ejemplo", seccion.getEjemplo());
    }

    @Test
    void testSetEjemplo() {
        seccion.setEjemplo("Nuevo ejemplo");
        assertEquals("Nuevo ejemplo", seccion.getEjemplo());
    }

    @Test
    void testGetExplicacion() {
        assertEquals("Explicación", seccion.getExplicacion());
    }

    @Test
    void testSetExplicacion() {
        seccion.setExplicacion("Nueva explicación");
        assertEquals("Nueva explicación", seccion.getExplicacion());
    }

    @Test
    void testGetPista() {
        assertEquals("Pista", seccion.getPista());
    }

    @Test
    void testSetPista() {
        seccion.setPista("Nueva pista");
        assertEquals("Nueva pista", seccion.getPista());
    }

    @Test
    void testGetResultadoEsperado() {
        assertEquals("Resultado esperado", seccion.getResultadoEsperado());
    }

    @Test
    void testSetResultadoEsperado() {
        seccion.setResultadoEsperado("Nuevo resultado esperado");
        assertEquals("Nuevo resultado esperado", seccion.getResultadoEsperado());
    }

    @Test
    void testTareaSeccionIsInitialized() {
        assertNotNull(seccion.tareaSeccion);
    }
}
