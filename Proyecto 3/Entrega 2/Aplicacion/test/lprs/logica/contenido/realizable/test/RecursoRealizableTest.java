package lprs.logica.contenido.realizable.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.contenido.realizable.RecursoRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

class RecursoRealizableTest {

    private RecursoRealizable recursoRealizable;
    private RecursoEducativo recursoBase;
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
        recursoBase = new RecursoEducativo("Recurso Test", "Descripción Recurso", "Objetivo", 30, true, "2024-12-31", learningPath,"Principiante","Video","link");
        recursoRealizable = new RecursoRealizable(recursoBase, estudiante);
    }

    @AfterEach
    void tearDown() {
        recursoRealizable = null;
        recursoBase = null;
        estudiante = null;
        learningPath = null;
        lprsActual = null;
    }

    @Test
    void testRealizarActividad() throws ActividadPreviaException {
        ArrayList<Object> resultado = recursoRealizable.realizarActividad();
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    void testGuardarActividad() {
        ArrayList<Object> respuestas = new ArrayList<>();
        recursoRealizable.guardarActividad(respuestas);
        assertTrue(estudiante.getAvance(learningPath.getID()).getActividadesCompletadasLista().contains(recursoBase));
    }

    @Test
    void testEnviarActividad() {
        ArrayList<Object> respuestas = new ArrayList<>();
        recursoRealizable.enviarActividad(respuestas);
        assertEquals("Completado", recursoRealizable.getEstado());
        assertTrue(profesor.getActividadesPendientes().contains(recursoRealizable));
    }

    @Test
    void testSetEstadoValido() {
        assertDoesNotThrow(() -> recursoRealizable.setEstado("Completado"));
        assertEquals("Completado", recursoRealizable.getEstado());
    }

    @Test
    void testSetEstadoInvalido() {
      assertThrows(EstadoException.class, () -> recursoRealizable.setEstado("En progreso"));
    
    }

    @Test
    void testGetActividadBase() {
        assertEquals(recursoBase, recursoRealizable.getActividadBase());
    }
}