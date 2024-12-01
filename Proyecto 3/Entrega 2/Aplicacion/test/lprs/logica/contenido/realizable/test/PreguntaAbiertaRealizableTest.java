package lprs.logica.contenido.realizable.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.PreguntaAbiertaRealizable;

public class PreguntaAbiertaRealizableTest {

    private PreguntaAbierta preguntaBase;
    private PreguntaAbiertaRealizable preguntaAbiertaRealizable;

    @BeforeEach
    public void setUp() {
        // Initialize a PreguntaAbierta before each test
        preguntaBase = new PreguntaAbierta("¿Cuál es tu color favorito?");
        preguntaAbiertaRealizable = new PreguntaAbiertaRealizable("Azul", preguntaBase);
    }

    @Test
    public void testGetRespuesta() {
        assertEquals("Azul", preguntaAbiertaRealizable.getRespuesta());
    }

    @Test
    public void testSetRespuesta() {
        preguntaAbiertaRealizable.setRespuesta("Rojo");
        assertEquals("Rojo", preguntaAbiertaRealizable.getRespuesta());
    }

    @Test
    public void testGetPreguntaBase() {
        assertEquals(preguntaBase, preguntaAbiertaRealizable.getPreguntaBase());
    }

    @Test
    public void testSetPreguntaBase() {
        PreguntaAbierta nuevaPreguntaBase = new PreguntaAbierta("¿Cuál es tu animal favorito?");
        preguntaAbiertaRealizable.setPreguntaBase(nuevaPreguntaBase);
        assertEquals(nuevaPreguntaBase, preguntaAbiertaRealizable.getPreguntaBase());
    }
}