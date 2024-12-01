package reqFun;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import lprs.consola.profesor.seguimiento.ConsolaProfesorSeguimiento;
import lprs.exceptions.ContraseniaIncorrectaException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.contenido.Resenia;
import lprs.logica.contenido.Tarea;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.RecursoRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Use a single instance for the whole test class
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReqFunsTest {
    private Estudiante estudiante;
    private Profesor profesor;
    private LearningPath lp;
    private Tarea tarea;
    private ConsolaProfesorSeguimiento consolaProfesor;
    private LPRS lprsActual;

    @BeforeAll
    void setUp() {
        this.lprsActual = new LPRS();
    }

    @Order(1)
    @Test
    void testReq1() throws Exception {
        lprsActual.getManejadorSesion().crearUsuario("profesor", "1234", 2);
        assertEquals(1, lprsActual.getManejadorSesion().getUsuarios().size());
        assertEquals("profesor", lprsActual.getManejadorSesion().getUsuarios().get("profesor").getUsuario());
        lprsActual.getManejadorSesion().crearUsuario("estudiante", "1234", 1);
        assertEquals(2, lprsActual.getManejadorSesion().getUsuarios().size());
        assertEquals("estudiante", lprsActual.getManejadorSesion().getUsuarios().get("estudiante").getUsuario());
    }
    @Order(2)
    @Test
    void testReq2() throws Exception{
        this.profesor = (Profesor) lprsActual.getManejadorSesion().iniciarSesion("profesor", "1234");
        assertEquals("profesor", profesor.getUsuario());
        this.estudiante = (Estudiante) lprsActual.getManejadorSesion().iniciarSesion("estudiante", "1234");
        assertEquals("estudiante", estudiante.getUsuario());
        assertThrows(ContraseniaIncorrectaException.class, () -> lprsActual.getManejadorSesion().iniciarSesion("estudiante", "12345"));
    }
    @Order(3)
    @Test
    void testReq3() {
        String titulo = "Titulo";
        String descripcion = "Descripcion";
        String nivelDificultad = "Principiante";
        ArrayList<String> objetivos = new ArrayList<String>() {{
            add("Objetivo");
        }};
        ArrayList<String> keywords = new ArrayList<String>() {{
            add("keyword");
        }};

        profesor.crearLearningPath(titulo, descripcion, nivelDificultad, objetivos, keywords);
        assertEquals(1, profesor.getLearningPathsCreados().size());
        lp = profesor.getLearningPathsCreadosLista().get(0);
        assertEquals(titulo, lp.getTitulo());
        assertEquals(descripcion, lp.getDescripcion());
        assertEquals(nivelDificultad, lp.getNivelDificultad());
        assertEquals(objetivos, lp.getObjetivos());
        assertEquals(keywords, lp.getKeyWords());
        assertTrue(lp.getProfesorCreador().equals(profesor));
        assertTrue(profesor.getLearningPathsCreados().contains(lp));
        assertTrue(lprsActual.getManejadorLP().getLearningPaths().contains(lp));
    }
    @Order(4)
    @Test
    void testReq4() throws Exception {
        String id = "0";
        lprsActual.getManejadorSesion().crearUsuario("profesor2", "1234", 2);
        Profesor profesor2 = (Profesor) lprsActual.getManejadorSesion().iniciarSesion("profesor2", "1234");
        profesor2.clonarLearningPath(id);
        assertEquals(1, profesor.getLearningPathsCreados().size());
        assertEquals(1, profesor2.getLearningPathsCreados().size());
        assertEquals(2, lprsActual.getManejadorLP().getLearningPaths().size());
        assertNotEquals(lp, profesor2.getLearningPathsCreadosLista().get(0));
    }
    @Order(5)
    @Test
    void testReq5() {
        String titulo = "Titulo";
        String descripcion = "Descripcion";
        String objetivo = "Objetivo";
        int duracionEsperada = 10;
        boolean obligatoria = true;
        String fechaLimite = "01/01/2022";
        String dificultad = "Principiante";

        profesor.getLearningPathCreado("0").crearTarea(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite);
        assertEquals(1, profesor.getLearningPathCreado("0").getActividades().size());
        profesor.getLearningPathCreado("0").crearRecursoEducativo(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite,"video","a");
        assertEquals(2, profesor.getLearningPathCreado("0").getActividades().size());
        profesor.getLearningPathCreado("0").crearEncuesta(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite);
        assertEquals(3, profesor.getLearningPathCreado("0").getActividades().size());
        profesor.getLearningPathCreado("0").crearExamen(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite);
        assertEquals(4, profesor.getLearningPathCreado("0").getActividades().size());
        profesor.getLearningPathCreado("0").crearQuizVerdaderoFalso(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite,1);
        assertEquals(5, profesor.getLearningPathCreado("0").getActividades().size());
        profesor.getLearningPathCreado("0").crearQuizMultiple(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite,1);
        assertEquals(6, profesor.getLearningPathCreado("0").getActividades().size());
    }

    @Order(6)
    @Test
    void testReq6() {
        String nuevoTitulo = "Nuevo Titulo";
        String nuevaDescripcion = "Nueva Descripcion";
        String nuevoNivelDificultad = "Intermedio";
        ArrayList<String> nuevosObjetivos = new ArrayList<String>() {{
            add("Nuevo Objetivo");
        }};
        profesor.modificarLearningPath(lp.getID(), nuevoTitulo, nuevaDescripcion, nuevoNivelDificultad, nuevosObjetivos);

        assertEquals(nuevoTitulo, lp.getTitulo());
        assertEquals(nuevaDescripcion, lp.getDescripcion());
        assertEquals(nuevoNivelDificultad, lp.getNivelDificultad());
        assertEquals(nuevosObjetivos, lp.getObjetivos());
        assertTrue(profesor.getLearningPathsCreados().contains(lp));
    }
    @Order(7)
    @Test
    void testReq7() throws Exception {

        estudiante.inscribirLearningPath(lp.getID());

        assertTrue(estudiante.getLearningPathsInscritos().contains(lp));
        assertTrue(lp.getEstudiantesInscritos().contains(estudiante));
    }
    @Order(8)
    @Test
    void testReq8() throws Exception {
        // Realizar una actividad (por ejemplo, una tarea)
        RecursoEducativo tarea = (RecursoEducativo) lp.getActividades().get(1);
        RecursoRealizable tareaR = tarea.crearActividadRealizable(estudiante);
        tareaR.enviarActividad(new ArrayList<>());
        // Verificar que la actividad ha sido realizada
        assertTrue(estudiante.getAvance(lp.getID()).getActividadesCompletadas().containsKey(tarea));
        assertTrue(tarea.getActividadesRealizablesCreadas().contains(tareaR));
    }
    @Order(9)
    @Test
    void testReq9() {
        assertFalse(estudiante.getAvance(lp.getID()).getActividadesCompletadas().isEmpty());
        assertFalse(lp.getActividades().isEmpty());
    }
    
    @Order(10)
    @Test
    void testReq10() {
        Actividad actividad = estudiante.getAvance(lp.getID()).getActividadesCompletadasLista().getFirst();
       
        actividad.addResenia(new Resenia(estudiante, "Reseña", 5));
        assertFalse(actividad.getResenias().isEmpty());
        assertTrue(actividad.getResenias().get(0).getAutor().equals(estudiante));
    }
}
