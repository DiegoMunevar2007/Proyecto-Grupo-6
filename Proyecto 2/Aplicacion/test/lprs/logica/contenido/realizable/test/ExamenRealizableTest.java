package lprs.logica.contenido.realizable.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.Examen;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.EncuestaRealizable;
import lprs.logica.contenido.realizable.ExamenRealizable;
import lprs.logica.contenido.realizable.PreguntaAbiertaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ExamenRealizableTest {
	
	private Examen actividadBase;
	private ExamenRealizable examenRealizable;
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
        profesor.getLearningPathCreado("0").crearExamen("Examen Test", "Descripción Examen", "Objetivo", 30, true, "2024-12-31");
        actividadBase = (Examen) profesor.getLearningPathCreado("0").getActividades().get(0);
        examenRealizable = (ExamenRealizable) actividadBase.crearActividadRealizable(estudiante);
	}
	
	 @AfterEach
    void tearDown() {
		examenRealizable = null;
        actividadBase = null;
        estudiante = null;
        learningPath = null;
        lprsActual = null;
    }
	 
	 @Test
    void testgetActividadBase() {
        assertEquals(actividadBase, examenRealizable.getActividadBase());
    }
	 
	 @Test
    void testgetPreguntas() {
        assertEquals(new ArrayList<PreguntaAbiertaRealizable>(), examenRealizable.getPreguntas());
    }
	 
	 @Test
    void testgetPreguntasRealizadas() {
        assertEquals(new ArrayList<PreguntaAbiertaRealizable>(), examenRealizable.getPreguntasRealizadas());
    }
	 
	 @Test
    void testsetPreguntasRealizadas() {
		ArrayList<PreguntaAbiertaRealizable> pregRealizadas =new ArrayList<PreguntaAbiertaRealizable>();
		PreguntaAbierta abiertaBase = new PreguntaAbierta("emoji favorito:");
		PreguntaAbiertaRealizable abierta = new PreguntaAbiertaRealizable(":)", abiertaBase);
		pregRealizadas.add(abierta);
		examenRealizable.setPreguntasRealizadas(pregRealizadas);
        assertEquals(pregRealizadas, examenRealizable.getPreguntasRealizadas());
    }
	 
	 @Test
    void testsetActividadBase() {
		Examen examenBase = new Examen("Examen Test", "Descripción Examen", "Objetivo", 30, true, "2024-12-31", learningPath, "principiante");
		examenRealizable.setActividadBase(examenBase);
        assertEquals(examenBase, examenRealizable.getActividadBase());
    }
}
