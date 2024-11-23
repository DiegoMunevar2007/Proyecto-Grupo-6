package lprs.logica.contenido.realizable.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Tarea;
import lprs.logica.contenido.realizable.TareaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

class TareaRealizableTest {

    private TareaRealizable tareaRealizable;
    private Tarea tareaBase;
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
        learningPath = lprsActual.getManejadorLP().getLearningPath("0");
        tareaBase = new Tarea("Tarea Test", "Descripción Tarea", "Objetivo", 30, true, "2024-12-31", learningPath, "Principiante");
        tareaRealizable = new TareaRealizable(tareaBase, estudiante);
    }

    @AfterEach
    void tearDown() {
        tareaRealizable = null;
        tareaBase = null;
        estudiante = null;
        learningPath = null;
        lprsActual = null;
    }

    @Test
    void testRealizarActividad() throws ActividadPreviaException {
        ArrayList<Object> resultado = tareaRealizable.realizarActividad();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testGuardarActividad() {
        ArrayList<Object> respuestas = new ArrayList<>();
        tareaRealizable.guardarActividad(respuestas);
        assertTrue(estudiante.getAvance(learningPath.getID()).getActividadesCompletadasLista().contains(tareaBase));
    }

    @Test
    void testEnviarActividad() {
        ArrayList<Object> respuestas = new ArrayList<>();
        tareaRealizable.enviarActividad(respuestas);
        assertEquals("No Exitoso", tareaRealizable.getEstado());
        assertTrue(profesor.getActividadesPendientes().contains(tareaRealizable));
    }

    @Test
    void testSetEstadoValido() {
        assertDoesNotThrow(() -> tareaRealizable.setEstado("Exitoso"));
        assertEquals("Exitoso", tareaRealizable.getEstado());
    }

    @Test
    void testSetEstadoInvalido() {
        assertThrows(EstadoException.class, () -> tareaRealizable.setEstado("En progreso"));
    }

    @Test
    void testGetActividadBase() {
        assertEquals(tareaBase, tareaRealizable.getActividadBase());
    }

    @Test
    void testSetActividadBase() {
        Tarea nuevaTareaBase = new Tarea("Nueva Tarea", "Nueva Descripción", "Nuevo Objetivo", 30, true, "2024-12-31", learningPath, "Principiante");
        tareaRealizable.setActividadBase(nuevaTareaBase);
        assertEquals(nuevaTareaBase, tareaRealizable.getActividadBase());
    }

    @Test
    void testCalificarActividad() {
        tareaRealizable.calificarActividad();
        // Add assertions based on the expected behavior of calificarActividad
    }
}