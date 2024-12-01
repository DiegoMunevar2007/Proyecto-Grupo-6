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
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

class EncuestaRealizableTest {

    private EncuestaRealizable encuestaRealizable;
    private Encuesta encuestaBase;
    private Estudiante estudiante;
    private LearningPath learningPath;
    private Profesor profesor;
    private LPRS lprsActual;

    @BeforeEach
    void setUp() throws Exception {
        lprsActual = new LPRS();
        
        lprsActual.getManejadorSesion().crearUsuario("Profesor Test", "5678", 2);
		lprsActual.getManejadorSesion().crearUsuario("Estudiante Test", "1234", 1);
        this.profesor = (Profesor) lprsActual.getManejadorSesion().getUsuarios().get("Profesor Test");
        this.estudiante = (Estudiante) lprsActual.getManejadorSesion().getUsuarios().get("Estudiante Test");
        ArrayList<String> keywords = new ArrayList<String>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", null, keywords);
        estudiante.inscribirLearningPath("0");
        profesor.getLearningPathCreado("0").crearEncuesta("Encuesta Test", "Descripción Encuesta", "Objetivo", 30, true, "2024-12-31");
        encuestaBase = (Encuesta) profesor.getLearningPathCreado("0").getActividades().get(0);
        encuestaRealizable = (EncuestaRealizable) encuestaBase.crearActividadRealizable(estudiante);
        
    }


    @AfterEach
    void tearDown() {
        encuestaRealizable = null;
        encuestaBase = null;
        estudiante = null;
        learningPath = null;
        lprsActual = null;
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
        assertTrue(exception.getMessage().contains("El estado "+ "En progreso" + "no es valido para la actividad" + "Encuesta Test"), "Debe lanzar una excepción con mensaje apropiado para estados inválidos.");
    }

    @Test
    void testObtenerPreguntas() {
        encuestaBase.addPreguntaEncuesta(new PreguntaAbierta("¿Cuál es tu objetivo en este curso?"));
        ArrayList<PreguntaAbierta> preguntas = encuestaRealizable.obtenerPreguntas();
        assertEquals(1, preguntas.size(), "Debe retornar todas las preguntas configuradas en la encuesta base.");
        assertEquals("¿Cuál es tu objetivo en este curso?", preguntas.get(0).getEnunciado());
    }
}
