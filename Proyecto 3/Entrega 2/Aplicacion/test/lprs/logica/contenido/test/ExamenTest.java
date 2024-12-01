package lprs.logica.contenido.test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.Examen;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.ExamenRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class ExamenTest {

    private Examen examen;
    private LearningPath learningPath;
    private PreguntaAbierta pregunta1;
    private PreguntaAbierta pregunta2;
    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        // Crear un estudiante ficticio
        estudiante = new Estudiante("pepe", "123", null);

        // Inicializar un objeto Examen antes de cada prueba
        examen = new Examen(
            "Examen Final",
            "Descripción del examen",
            "Objetivo del examen",
            90, // duración esperada en minutos
            true, // obligatoria
            "2024-12-31", // fecha límite
            learningPath, // objeto LearningPath
            "Alta" // dificultad
        );

        // Inicializar preguntas de ejemplo
        pregunta1 = new PreguntaAbierta("¿Cuál es la capital de Francia?");
        pregunta2 = new PreguntaAbierta("Define el término 'ecosistema'");
    }

    @AfterEach
    void tearDown() {
        // Limpiar las referencias después de cada prueba
        examen = null;
        learningPath = null;
        pregunta1 = null;
        pregunta2 = null;
        estudiante = null;
    }

    @Test
    void testGetPreguntasExamen() {
        assertNotNull(examen.getPreguntasExamen());
        assertTrue(examen.getPreguntasExamen().isEmpty());
    }

    @Test
    void testSetPreguntasExamen() {
        ArrayList<PreguntaAbierta> preguntas = new ArrayList<>();
        preguntas.add(pregunta1);
        preguntas.add(pregunta2);

        examen.setPreguntasExamen(preguntas);

        assertEquals(2, examen.getPreguntasExamen().size());
        assertTrue(examen.getPreguntasExamen().contains(pregunta1));
        assertTrue(examen.getPreguntasExamen().contains(pregunta2));
    }

    @Test
    void testAddPreguntaExamen() {
        examen.addPreguntaExamen(pregunta1);
        examen.addPreguntaExamen(pregunta2);

        assertEquals(2, examen.getPreguntasExamen().size());
        assertTrue(examen.getPreguntasExamen().contains(pregunta1));
        assertTrue(examen.getPreguntasExamen().contains(pregunta2));
    }

    @Test
    void testRemovePreguntaExamen() {
        examen.addPreguntaExamen(pregunta1);
        examen.addPreguntaExamen(pregunta2);

        examen.removePreguntaExamen(pregunta1);

        assertEquals(1, examen.getPreguntasExamen().size());
        assertFalse(examen.getPreguntasExamen().contains(pregunta1));
        assertTrue(examen.getPreguntasExamen().contains(pregunta2));
    }

    @Test
    void testCrearActividadRealizable() {
        ActividadRealizable actividadRealizable = examen.crearActividadRealizable(estudiante);

        assertNotNull(actividadRealizable);
        assertTrue(actividadRealizable instanceof ExamenRealizable);
        assertEquals(estudiante, ((ExamenRealizable) actividadRealizable).getEstudiante());
    }
}
