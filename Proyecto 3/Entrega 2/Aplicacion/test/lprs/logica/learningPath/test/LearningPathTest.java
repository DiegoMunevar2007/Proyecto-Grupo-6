package lprs.logica.learningPath.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.Examen;
import lprs.logica.contenido.QuizMultiple;
import lprs.logica.contenido.QuizVerdaderoFalso;
import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.contenido.Tarea;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.logica.learningPath.Metadato;
import lprs.principal.LPRS;

public class LearningPathTest {
	private LearningPath learningPathCorrespondiente;
	private LPRS lprsActual;
	private Estudiante estudiante;
	private Profesor profesor;
	
	@BeforeEach
    public void setUp() throws Exception{
		lprsActual = new LPRS();

        lprsActual.getManejadorSesion().crearUsuario("Profesor Test", "5678", 2);
        lprsActual.getManejadorSesion().crearUsuario("Estudiante Test", "1234", 1);
        this.profesor = (Profesor) lprsActual.getManejadorSesion().getUsuarios().get("Profesor Test");
        this.estudiante = (Estudiante) lprsActual.getManejadorSesion().getUsuarios().get("Estudiante Test");
        ArrayList<String> keywords = new ArrayList<String>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", new ArrayList<String>(), keywords);

        estudiante.inscribirLearningPath("0");
        learningPathCorrespondiente = lprsActual.getManejadorLP().getLearningPath("0");
	}
	
	@AfterEach
    void tearDown() {
        profesor = null;
        estudiante = null;
        learningPathCorrespondiente = null;
        lprsActual = null;
    }
	
	@Test
    void testaddKeyWord() {
		learningPathCorrespondiente.addKeyWord("English");
		ArrayList<String> keys = new ArrayList<String>();
		keys.add("test");
		keys.add("English");
		assertEquals(keys,learningPathCorrespondiente.getKeyWords());
    }
	
	@Test
    void testobtenerFecha() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaString = myDateObj.format(myFormatObj);
		assertEquals(fechaString,learningPathCorrespondiente.obtenerFecha());
    }
	
	@Test
    void testcrearTarea() {
		learningPathCorrespondiente.crearTarea("Colors", "learning the color in english", "learning", 10, true, "20/10/2023");
		assertEquals(1,learningPathCorrespondiente.getCantidadObligatorias());
    }
	
	@Test
    void testcrearRecursoEducativo() {
		learningPathCorrespondiente.crearRecursoEducativo("Colors", "learning the color in english", "learning", 10, true, "20/10/2023", "video","ww.google.com");
		assertEquals(1,learningPathCorrespondiente.getCantidadObligatorias());
    }
	
	@Test
    void testcrearQuizMultiple() {
		learningPathCorrespondiente.crearQuizMultiple("Colors", "learning the color in english", "learning", 10, true, "20/10/2023", 1.0);
		assertEquals(1,learningPathCorrespondiente.getCantidadObligatorias());
    }
	
	@Test
    void testcrearQuizVerdaderoFalso() {
		learningPathCorrespondiente.crearQuizVerdaderoFalso("Colors", "learning the color in english", "learning", 10, true, "20/10/2023", 1.0);
		assertEquals(1,learningPathCorrespondiente.getCantidadObligatorias());
    }
	
	@Test
    void testcrearExamen() {
		learningPathCorrespondiente.crearExamen("Colors", "learning the color in english", "learning", 10, true, "20/10/2023");
		assertEquals(1,learningPathCorrespondiente.getCantidadObligatorias());
    }
	
	@Test
    void testcrearEncuesta() {
		learningPathCorrespondiente.crearEncuesta("Colors", "learning the color in english", "learning", 10, true, "20/10/2023");
		assertEquals(1,learningPathCorrespondiente.getCantidadObligatorias());
    }
	
	@Test
    void testgetCantidadObligatorias() {
		assertEquals(0, learningPathCorrespondiente.getCantidadObligatorias());
    }
	
	@Test
    void testgetNumeroLP() {
		assertEquals(0, LearningPath.getNumeroLP());
    }
	
	@Test
    void testsetNumeroLP() {
		LearningPath.setNumeroLP(3);
		assertEquals(3, LearningPath.getNumeroLP());
    }
	
	@Test
    void testgetCalificaciones() {
		assertEquals(0, learningPathCorrespondiente.getCalificaciones());
    }
	
	@Test
    void testgetLprsActual() {
		assertEquals(lprsActual, learningPathCorrespondiente.getLprsActual());
    }
	
	@Test
    void testsetLprsActual() throws Exception{
		LPRS lprsActual2 = new LPRS();
		learningPathCorrespondiente.setLprsActual(lprsActual2);
		assertEquals(lprsActual2, learningPathCorrespondiente.getLprsActual());
    }
	
	@Test
    void testsetActividades(){
		Encuesta encuesta = new Encuesta("Colors", "learning the color in english", "learning", 10, true, "20/10/2023", learningPathCorrespondiente, "learning");
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		actividades.add(encuesta);
		learningPathCorrespondiente.setActividades(actividades);
		assertEquals(actividades, learningPathCorrespondiente.getActividades());
    }
	
	@Test
    void testsetEstudiantesInscritos(){
		learningPathCorrespondiente.setEstudiantesInscritos(new ArrayList<Estudiante>());
		assertEquals(new ArrayList<Estudiante>(), learningPathCorrespondiente.getEstudiantesInscritos());
    }
	
	@Test
    void testeliminarActividad(){
		learningPathCorrespondiente.crearEncuesta("Colors", "learning the color in english", "learning", 10, true, "20/10/2023");
		ArrayList<Actividad> actividades = learningPathCorrespondiente.getActividades();
		Actividad actividad=actividades.getFirst();
		learningPathCorrespondiente.eliminarActividad(actividad);
		assertEquals( new ArrayList<Actividad>(),learningPathCorrespondiente.getActividades());
    }
	
	@Test
    void testgetTitulo() {
		assertEquals("LP Test", learningPathCorrespondiente.getTitulo());
    }
	
	@Test
    void testsetTitulo() {
		learningPathCorrespondiente.setTitulo("LP Test2");
		assertEquals("LP Test2", learningPathCorrespondiente.getTitulo());
    }
	
	@Test
    void testgetDescripcion() {
		assertEquals("Descripcion del Learning Path", learningPathCorrespondiente.getDescripcion());
    }
	
	@Test
    void testsetDescripcion() {
		learningPathCorrespondiente.setDescripcion("LP Descripcion Test2");
		assertEquals("LP Descripcion Test2", learningPathCorrespondiente.getDescripcion());
    }
	
	@Test
    void testgetNivelDificultad() {
		assertEquals("Principiante", learningPathCorrespondiente.getNivelDificultad());
    }
	
	@Test
    void testsetNivelDificultad() {
		learningPathCorrespondiente.setNivelDificultad("Principiante2");
		assertEquals("Principiante2", learningPathCorrespondiente.getNivelDificultad());
    }
	
	@Test
    void testgetObjetivos() {
		assertEquals(new ArrayList<String>(), learningPathCorrespondiente.getObjetivos());
    }
	
	@Test
    void testaddObjetivo() {
		learningPathCorrespondiente.addObjetivo("objetivo2");
		ArrayList<String> objetivos = learningPathCorrespondiente.getObjetivos();
		assertEquals(1, objetivos.size());
    }
	
	@Test
    void testdelObjetivos() {
		ArrayList<String> objetivos = new ArrayList<String>();
		learningPathCorrespondiente.addObjetivo("objetivo2");
		learningPathCorrespondiente.delObjetivos("objetivo2");
		assertEquals(objetivos, learningPathCorrespondiente.getObjetivos());
    }
	
	@Test
    void testsetObjetivos() {
		ArrayList<String> objetivos = new ArrayList<String>();
		objetivos.add("objetivos2");
		objetivos.add("objetivos1");
		learningPathCorrespondiente.setObjetivos(objetivos);
		assertEquals(objetivos, learningPathCorrespondiente.getObjetivos());
    }
	
	@Test
    void testgetRating() {
		assertEquals(0, learningPathCorrespondiente.getRating());
    }
	
	@Test
    void testcambiarRating() {
		learningPathCorrespondiente.cambiarRating(4.3);
		assertEquals(4.3, learningPathCorrespondiente.getRating());
    }
	
	@Test
    void testgetID() {
		assertEquals("0", learningPathCorrespondiente.getID());
    }
	
	@Test
    void testsetID() {
		learningPathCorrespondiente.setID("3");
		assertEquals("3", learningPathCorrespondiente.getID());
    }
	
	@Test
    void testgetDuracion() {
		assertEquals(0, learningPathCorrespondiente.getDuracion());
    }
	
	@Test
    void testcambiarDuracion() {
		learningPathCorrespondiente.cambiarDuracion(5);
		assertEquals(5, learningPathCorrespondiente.getDuracion());
    }
	
	@Test
    void testgetActividades() {
		assertEquals(new ArrayList<Actividad>(), learningPathCorrespondiente.getActividades());
    }
	
	@Test
    void testaniadirActividad(){
		Encuesta encuesta = new Encuesta("Colors", "learning the color in english", "learning", 10, true, "20/10/2023", learningPathCorrespondiente, "learning");
		ArrayList<Actividad> actividades = new ArrayList<Actividad>();
		learningPathCorrespondiente.aniadirActividad(encuesta);
		actividades.add(encuesta);
		assertEquals(actividades, learningPathCorrespondiente.getActividades());
    }
	
	@Test
    void testgetEstudiantesInscritos(){
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		estudiantes.add(estudiante);
		assertEquals(estudiantes, learningPathCorrespondiente.getEstudiantesInscritos());
    }
	
	@Test
    void testaniadirEstudiante() throws Exception{
		Estudiante estudiante2 = new Estudiante("Estudiante Test2", "1234", lprsActual);
		learningPathCorrespondiente.aniadirEstudiante(estudiante2);
		ArrayList<Estudiante> estudiantes = learningPathCorrespondiente.getEstudiantesInscritos();
		assertEquals(2, estudiantes.size());
    }
	
	@Test
    void testeliminarEstudiante(){
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		learningPathCorrespondiente.eliminarEstudiante(estudiante);
		assertEquals(estudiantes, learningPathCorrespondiente.getEstudiantesInscritos());
    }
	
	@Test
    void testgetProfesorCreador() {
		assertEquals(profesor, learningPathCorrespondiente.getProfesorCreador());
    }
	
	@Test
    void testeditarLearningPath() throws Exception{
		learningPathCorrespondiente.editarLearningPath("titulo", "descripcion", "nivelDificultad",
				null, profesor);
		assertEquals("titulo", learningPathCorrespondiente.getTitulo());
    }
	
	@Test
    void testeliminarLearningPath() {
		learningPathCorrespondiente.eliminarLearningPath();
		List<LearningPath> lpi = estudiante.getLearningPathsInscritos();
		assertEquals(0,lpi.size() );
    }
	
	@Test
    void testsetLPRS() {
		LPRS lprs2 = new LPRS();
		learningPathCorrespondiente.setLPRS(lprs2);
		assertEquals(lprs2, learningPathCorrespondiente.getLprsActual());
    }
	
	@Test
    void testgetMetadatos() {
		Metadato metadato = learningPathCorrespondiente.getMetadatos();
		String version = metadato.getVersion();
		assertEquals("1",version);
    }
	
	@Test
    void testsetMetadatos() {
		learningPathCorrespondiente.setMetadatos(new Metadato("22/12/2023", "3"));
		Metadato metadato = learningPathCorrespondiente.getMetadatos();
		String version = metadato.getVersion();
		assertEquals("3",version);
    }
	
	@Test
    void testgetKeyWords() {
		ArrayList<String> keywords = new ArrayList<String>();
        keywords.add("test");
		assertEquals(keywords, learningPathCorrespondiente.getKeyWords());
    }
}
