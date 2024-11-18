package lprs.logica.contenido.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.EncuestaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

class EncuestaTest {

    private Encuesta encuesta;
    private LearningPath learningPath;
    private PreguntaAbierta pregunta1;
    private PreguntaAbierta pregunta2;
    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        // Crear un estudiante ficticio
        estudiante = new Estudiante("Juan", "456", null);

        // Inicializar un objeto Encuesta antes de cada prueba
        encuesta = new Encuesta(
            "Encuesta de Satisfacción",
            "Descripción de la encuesta",
            "Evaluar la experiencia del usuario",
            30, // duración esperada en minutos
            false, // no obligatoria
            "2024-11-30", // fecha límite
            learningPath, // objeto LearningPath
            "Baja" // dificultad
        );

        // Inicializar preguntas de ejemplo
        pregunta1 = new PreguntaAbierta("¿Qué te gustó más del curso?");
        pregunta2 = new PreguntaAbierta("¿Qué mejorarías del curso?");
    }

    @AfterEach
    void tearDown() {
        // Limpiar las referencias después de cada prueba
        encuesta = null;
        learningPath = null;
        pregunta1 = null;
        pregunta2 = null;
        estudiante = null;
    }

    @Test
    void testGetPreguntasEncuesta() {
        assertNotNull(encuesta.getPreguntasEncuesta());
        assertTrue(encuesta.getPreguntasEncuesta().isEmpty());
    }

    @Test
    void testSetPreguntasEncuesta() {
        ArrayList<PreguntaAbierta> preguntas = new ArrayList<>();
        preguntas.add(pregunta1);
        preguntas.add(pregunta2);

        encuesta.setPreguntasEncuesta(preguntas);

        assertEquals(2, encuesta.getPreguntasEncuesta().size());
        assertTrue(encuesta.getPreguntasEncuesta().contains(pregunta1));
        assertTrue(encuesta.getPreguntasEncuesta().contains(pregunta2));
    }

    @Test
    void testAddPreguntaEncuesta() {
        encuesta.addPreguntaEncuesta(pregunta1);
        encuesta.addPreguntaEncuesta(pregunta2);

        assertEquals(2, encuesta.getPreguntasEncuesta().size());
        assertTrue(encuesta.getPreguntasEncuesta().contains(pregunta1));
        assertTrue(encuesta.getPreguntasEncuesta().contains(pregunta2));
    }

    @Test
    void testRemovePreguntaEncuesta() {
        encuesta.addPreguntaEncuesta(pregunta1);
        encuesta.addPreguntaEncuesta(pregunta2);

        encuesta.removePreguntaEncuesta(pregunta1);

        assertEquals(1, encuesta.getPreguntasEncuesta().size());
        assertFalse(encuesta.getPreguntasEncuesta().contains(pregunta1));
        assertTrue(encuesta.getPreguntasEncuesta().contains(pregunta2));
    }

    @Test
    void testCrearActividadRealizable() {
        ActividadRealizable actividadRealizable = encuesta.crearActividadRealizable(estudiante);

        assertNotNull(actividadRealizable);
        assertTrue(actividadRealizable instanceof EncuestaRealizable);
        assertEquals(estudiante, ((EncuestaRealizable) actividadRealizable).getEstudiante());
    }
}
