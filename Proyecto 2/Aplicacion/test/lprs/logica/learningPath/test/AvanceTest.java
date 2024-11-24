package lprs.logica.learningPath.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.EncuestaRealizable;
import lprs.logica.contenido.realizable.PreguntaAbiertaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class AvanceTest {
	private LearningPath learningPathCorrespondiente;
	private LPRS lprsActual;
	private Estudiante estudiante;
	private Profesor profesor;
	private Avance avance;
	private EncuestaRealizable encuestaRealizable;
    private Encuesta encuestaBase;
	
	@BeforeEach
    public void setUp() throws Exception{
		lprsActual = new LPRS();

        lprsActual.getManejadorSesion().crearUsuario("Profesor Test", "5678", 2);
        lprsActual.getManejadorSesion().crearUsuario("Estudiante Test", "1234", 1);
        this.profesor = (Profesor) lprsActual.getManejadorSesion().getUsuarios().get("Profesor Test");
        this.estudiante = (Estudiante) lprsActual.getManejadorSesion().getUsuarios().get("Estudiante Test");
        ArrayList<String> keywords = new ArrayList<String>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", null, keywords);

        estudiante.inscribirLearningPath("0");
        learningPathCorrespondiente = lprsActual.getManejadorLP().getLearningPath("0");
        profesor.getLearningPathCreado("0").crearEncuesta("Encuesta Test", "Descripción Encuesta", "Objetivo", 30, true, "2024-12-31");
        encuestaBase = (Encuesta) profesor.getLearningPathCreado("0").getActividades().get(0);
        encuestaRealizable = (EncuestaRealizable) encuestaBase.crearActividadRealizable(estudiante);
        
        avance = new Avance("17/06/2022", learningPathCorrespondiente);
    }
	
	@AfterEach
    void tearDown() {
        profesor = null;
        avance = null;
        estudiante = null;
        learningPathCorrespondiente = null;
        lprsActual = null;
        encuestaRealizable=null;
        encuestaBase=null;
    }
	
	@Test
    void testgetFechaInicio() {
		assertEquals("17/06/2022", avance.getFechaInicio());
    }
	
	@Test
    void testsetFechaInicio() {
		avance.setFechaInicio("22/10/2020");
		assertEquals("22/10/2020", avance.getFechaInicio());
    }
	
	@Test
    void testgetFechaFin() {
		assertEquals(null, avance.getFechaFin());
    }
	
	@Test
    void testsetFechaFin() {
		avance.setFechaFin("22/10/2026");
		assertEquals("22/10/2026", avance.getFechaFin());
    }
	
	@Test
    void testgetTiempoDedicado() {
		assertEquals(0.0, avance.getTiempoDedicado());
    }
	
	@Test
    void testsetTiempoDedicado() {
		avance.setTiempoDedicado(10.5);
		assertEquals(10.5, avance.getTiempoDedicado());
    }
	
	@Test
    void testgetTasaExito() {
		assertEquals(0.0, avance.getTasaExito());
    }
	
	@Test
    void testsetTasaExito() {
		avance.setTasaExito(3.7);
		assertEquals(3.7, avance.getTasaExito());
    }
	
	@Test
    void testgetTasaFracaso() {
		assertEquals(0.0, avance.getTasaFracaso());
    }
	
	@Test
    void testsetTasaFracaso() {
		avance.setTasaFracaso(4.5);
		assertEquals(4.5, avance.getTasaFracaso());
    }
	
	@Test
    void testgetLearningPathCorrespondiente() {
		assertEquals(learningPathCorrespondiente, avance.getLearningPathCorrespondiente());
    }
	
	@Test
    void testsetLearningPathCorrespondiente() {
		ArrayList<String> keywords = new ArrayList<String>();
        keywords.add("test2");
        profesor.crearLearningPath("LP Test2", "Descripcion del Learning Path2", "Principiante", null, keywords);
        learningPathCorrespondiente = lprsActual.getManejadorLP().getLearningPath("1");
		avance.setLearningPathCorrespondiente(learningPathCorrespondiente);
		assertEquals(learningPathCorrespondiente, avance.getLearningPathCorrespondiente());
    }
	
	@Test
    void testaddActividadPendiente() {
		avance.addActividadPendiente(encuestaBase);
		ArrayList<Actividad> actividadesPendientes = learningPathCorrespondiente.getActividades();
		actividadesPendientes.add(encuestaBase);
		assertEquals(actividadesPendientes, avance.getActividadesPendientes());
    }
	
	@Test
    void testgetCantidadActividadesObligatorias() {
		assertEquals(1, avance.getCantidadActividadesObligatorias());
    }
	
	@Test
    void testsetCantidadActividadesObligatorias() {
		avance.setCantidadActividadesObligatorias(7);
		assertEquals(7, avance.getCantidadActividadesObligatorias());
    }
	
	@Test
    void testgetActividadesCompletadasLista() {
		ArrayList<Actividad> actividadesCompletadas = new ArrayList<Actividad>();
		assertEquals(actividadesCompletadas, avance.getActividadesCompletadasLista());
    }
	
	@Test
    void testsetActividadesCompletadasLista() {
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		actividades.add(encuestaBase);
		avance.setActividadesCompletadasLista(actividades);
		assertEquals(actividades, avance.getActividadesCompletadasLista());
    }
	
	@Test
    void testgetActividadesCompletadas() {
		HashMap<Actividad, ActividadRealizable> actividadesCompletadas = new HashMap<Actividad, ActividadRealizable>();
		assertEquals(actividadesCompletadas, avance.getActividadesCompletadas());
    }
	
	@Test
    void testgetActividadesCompletadasPorcentaje() {
		assertEquals(0.0, avance.getActividadesCompletadasPorcentaje());
    }
	
	@Test
    void testsetActividadesCompletadasPorcentaje() {
		avance.setActividadesCompletadasPorcentaje(4.6);
		assertEquals(4.6, avance.getActividadesCompletadasPorcentaje());
    }
	
	@Test
    void testgetActividadesPendientes() {
		ArrayList<Actividad> actividadesCompletadas = learningPathCorrespondiente.getActividades();
		assertEquals(actividadesCompletadas, avance.getActividadesPendientes());
    }
	
	@Test
    void testsetActividadesPendientes() {
		ArrayList<Actividad> actividadesCompletadas = learningPathCorrespondiente.getActividades();
		actividadesCompletadas.add(encuestaBase);
		avance.setActividadesPendientes(actividadesCompletadas);
		assertEquals(actividadesCompletadas, avance.getActividadesPendientes());
    }
	
	@Test
    void testsetActividadesCompletadas() {
		HashMap<Actividad, ActividadRealizable> actividadesCompletadas = new HashMap<Actividad, ActividadRealizable>();
		actividadesCompletadas.put(encuestaBase, encuestaRealizable);
		avance.setActividadesCompletadas(actividadesCompletadas);
		assertEquals(actividadesCompletadas, avance.getActividadesCompletadas());
    }
	
	@Test
    void testaddActividadRealizada() throws Exception{
		avance.addActividadPendiente(encuestaBase);
		avance.addActividadRealizada(encuestaRealizable);
		assertEquals(learningPathCorrespondiente.getActividades(), avance.getActividadesPendientes());
    }
}
