package lprs.logica.contenido.pregunta.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.pregunta.PreguntaAbierta;

class PreguntaAbiertaTest {

    private PreguntaAbierta preguntaAbierta;

    @BeforeEach
    void setUp() {
        // Inicializar una PreguntaAbierta antes de cada prueba
        preguntaAbierta = new PreguntaAbierta("¿Cuál es la capital de Francia?");
    }

    @AfterEach
    void tearDown() {
        // Limpiar la referencia después de cada prueba
        preguntaAbierta = null;
    }

    @Test
    void testGetCalificado() {
        assertFalse(preguntaAbierta.getCalificado(), "La pregunta debería inicializarse como no calificada.");
    }

    @Test
    void testSetCalificado() {
        preguntaAbierta.setCalificado(true);
        assertTrue(preguntaAbierta.getCalificado(), "La pregunta debería estar marcada como calificada.");
    }

    @Test
    void testGetEnunciado() {
        assertEquals("¿Cuál es la capital de Francia?", preguntaAbierta.getEnunciado());
    }

    @Test
    void testSetEnunciado() {
        preguntaAbierta.setEnunciado("¿Cuál es la capital de Alemania?");
        assertEquals("¿Cuál es la capital de Alemania?", preguntaAbierta.getEnunciado());
    }
}

