package lprs.logica.contenido.realizable.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.EncuestaRealizable;
import lprs.logica.contenido.realizable.PreguntaAbiertaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;

class EncuestaRealizableTest {

    private EncuestaRealizable encuestaRealizable;
    private Encuesta encuestaBase;
    private Estudiante estudiante;
    private LearningPath learningPath;

    @BeforeEach
    void setUp() {
        // Crear una lista de objetivos para el LearningPath
        ArrayList<String> objetivos = new ArrayList<>();
        objetivos.add("Entender conceptos básicos");
        objetivos.add("Resolver problemas avanzados");
        Profesor profesor = new Profesor("Profesor Test", "5678", null);

        // Crear el objeto LearningPath
        learningPath = new LearningPath(
            "LP123",                        // ID
            "LP Test",                      // Título
            "Descripción del LearningPath", // Descripción
            "Media",                        // Nivel de dificultad
            objetivos,                      // Lista de objetivos
            profesor,                       // Profesor creador
            null                            // Referencia a LPRS (puede ser null si no se usa)
        );

        // Crear el estudiante y asociar un avance
        estudiante = new Estudiante("Estudiante Test", "1234", null);
        Avance avance = new Avance("",learningPath);
        estudiante.addAvance(avance); // Método para agregar el avance al estudiante

        // Crear la encuesta base y la encuesta realizable
        encuestaBase = new Encuesta("Encuesta Test", "Descripción Encuesta", "Objetivo", 30, true, "2024-12-31", learningPath, "Media");
        encuestaRealizable = new EncuestaRealizable(encuestaBase, estudiante);
    }


    @AfterEach
    void tearDown() {
        encuestaRealizable = null;
        encuestaBase = null;
        estudiante = null;
        learningPath = null;
    }

    @Test
    void testRealizarActividad() throws ActividadPreviaException {
        // Configurar preguntas en la encuesta base
        encuestaBase.addPreguntaEncuesta(new PreguntaAbierta("¿Qué opinas del curso?"));
        encuestaBase.addPreguntaEncuesta(new PreguntaAbierta("¿Cómo mejorarías el contenido?"));

        // Realizar actividad
        ArrayList<PreguntaAbierta> preguntas = encuestaRealizable.realizarActividad();
        assertEquals(2, preguntas.size(), "El número de preguntas realizadas debe coincidir con las preguntas de la encuesta base.");
    }

    @Test
    void testGuardarActividad() {
        // Configurar respuestas
        ArrayList<PreguntaAbiertaRealizable> respuestas = new ArrayList<>();
        respuestas.add(new PreguntaAbiertaRealizable("Muy bueno",new PreguntaAbierta("¿Qué opinas del curso?")));
        respuestas.add(new PreguntaAbiertaRealizable("Más ejemplos prácticos",new PreguntaAbierta("¿Cómo mejorarías el contenido?")));

        encuestaRealizable.guardarActividad(respuestas);
        assertEquals(2, encuestaRealizable.getPreguntasRealizadas().size(), "El número de respuestas guardadas debe coincidir con las enviadas.");
    }

    @Test
    void testEnviarActividad() {
        // Configurar respuestas
        ArrayList<PreguntaAbiertaRealizable> respuestas = new ArrayList<>();
        respuestas.add(new PreguntaAbiertaRealizable("Muy bueno",new PreguntaAbierta("¿Qué opinas del curso?")));
        respuestas.add(new PreguntaAbiertaRealizable("Más ejemplos prácticos",new PreguntaAbierta("¿Cómo mejorarías el contenido?") ));

        Profesor profesor = new Profesor("Profesor Test", "5678", null);
        learningPath.setProfesorCreador(profesor);

        encuestaRealizable.enviarActividad(respuestas);

        assertEquals("Completado", encuestaRealizable.getEstado(), "El estado de la encuesta debe ser 'Completado' después de enviar.");
        assertTrue(profesor.getActividadesPendientes().contains(encuestaRealizable), "La actividad debe estar en la lista de actividades pendientes del profesor.");
    }

    @Test
    void testSetEstadoValido() {
        assertDoesNotThrow(() -> encuestaRealizable.setEstado("Completado"));
        assertEquals("Completado", encuestaRealizable.getEstado());
    }

    @Test
    void testSetEstadoInvalido() {
        Exception exception = assertThrows(EstadoException.class, () -> encuestaRealizable.setEstado("En progreso"));
        assertTrue(exception.getMessage().contains("Estado inválido"), "Debe lanzar una excepción con mensaje apropiado para estados inválidos.");
    }

    @Test
    void testObtenerPreguntas() {
        encuestaBase.addPreguntaEncuesta(new PreguntaAbierta("¿Cuál es tu objetivo en este curso?"));
        ArrayList<PreguntaAbierta> preguntas = encuestaRealizable.obtenerPreguntas();
        assertEquals(1, preguntas.size(), "Debe retornar todas las preguntas configuradas en la encuesta base.");
        assertEquals("¿Cuál es tu objetivo en este curso?", preguntas.get(0).getEnunciado());
    }
}
