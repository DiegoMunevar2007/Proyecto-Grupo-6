
package lprs.logica.cuentas.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.Tarea;
import lprs.logica.contenido.realizable.TareaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ProfesorTest {

    private Profesor profesor;
    private Profesor profesor2;
    private LPRS lprs;
    private LearningPath learningPath;
    private TareaRealizable actividad;
    private Estudiante estudiante;

    @BeforeEach
    public void setUp() {
        lprs = new LPRS();
        profesor = new Profesor("usuarioTest", "contraseniaTest", lprs);
        profesor2 = new Profesor("usuarioTest", "contraseniaTest", lprs);
        estudiante = new Estudiante("usuarioTest", "contraseniaTest", lprs);
        Tarea tarea = new Tarea("Tarea Title", "Tarea Description", "Tarea Objective", 60, true, 
                "2023-12-31", learningPath, "Intermediate");
        actividad  = new TareaRealizable(tarea, estudiante);
        
    }

    @AfterEach
    public void tearDown() {
        profesor = null;
        lprs = null;
        learningPath = null;
        actividad = null;
    }

    @Test
    public void testCrearLearningPath() {
        ArrayList<String> objetivos = new ArrayList<>();
        objetivos.add("Objective 1");
        objetivos.add("Objective 2");

        ArrayList<String> keyWords = new ArrayList<>();
        keyWords.add("Keyword1");
        keyWords.add("Keyword2");

        String learningPathID = profesor.crearLearningPath("Learning Path Title", "Learning Path Description",
                                                           "Intermediate", objetivos, keyWords);

        learningPath = lprs.getManejadorLP().getLearningPath(learningPathID);
        assertNotNull(learningPath);
        assertEquals("Learning Path Title", learningPath.getTitulo());
    }

    @Test
    public void testModificarLearningPath() {
        ArrayList<String> objetivos = new ArrayList<>();
        objetivos.add("Objective 1");
        objetivos.add("Objective 2");

        ArrayList<String> keyWords = new ArrayList<>();
        keyWords.add("Keyword1");
        keyWords.add("Keyword2");

        String learningPathID = profesor.crearLearningPath("Learning Path Title", "Learning Path Description",
                                                           "Intermediate", objetivos, keyWords);

        profesor.modificarLearningPath(learningPathID, "New Title", "New Description", "Advanced", objetivos);

        learningPath = lprs.getManejadorLP().getLearningPath(learningPathID);
        assertEquals("New Title", learningPath.getTitulo());
        assertEquals("New Description", learningPath.getDescripcion());
    }

    @Test
    public void testEliminarLearningPath() {
        ArrayList<String> objetivos = new ArrayList<>();
        objetivos.add("Objective 1");
        objetivos.add("Objective 2");

        ArrayList<String> keyWords = new ArrayList<>();
        keyWords.add("Keyword1");
        keyWords.add("Keyword2");

        String learningPathID = profesor.crearLearningPath("Learning Path Title", "Learning Path Description",
                                                           "Intermediate", objetivos, keyWords);
        

        profesor.eliminarLearningPath(learningPathID);

        learningPath = lprs.getManejadorLP().getLearningPath(learningPathID);
        assertTrue(learningPath == null);
    }

    @Test
    public void testClonarLearningPath() throws Exception {
        ArrayList<String> objetivos = new ArrayList<>();
        objetivos.add("Objective 1");
        objetivos.add("Objective 2");

        ArrayList<String> keyWords = new ArrayList<>();
        keyWords.add("Keyword1");
        keyWords.add("Keyword2");

        String learningPathID = profesor.crearLearningPath("Learning Path Title", "Learning Path Description",
                                                           "Intermediate", objetivos, keyWords);

        String clonado = profesor2.clonarLearningPath(learningPathID);

        LearningPath clonedLearningPath = lprs.getManejadorLP().getLearningPath(clonado);
        assertNotNull(clonedLearningPath);
        assertEquals("Learning Path Title", clonedLearningPath.getTitulo());
    }

    @Test
    public void testGetSetLearningPathsCreados() {
        HashMap<String, LearningPath> learningPaths = new HashMap<>();
        profesor.setLearningPathsCreados(learningPaths);
        assertEquals(learningPaths.values(), profesor.getLearningPathsCreados());
    }

    @Test
    public void testGetSetLearningPathsCreadosLista() {
        ArrayList<LearningPath> learningPathsLista = new ArrayList<>();
        profesor.setLearningPathsCreadosLista(learningPathsLista);
        assertEquals(learningPathsLista, profesor.getLearningPathsCreadosLista());
    }

    @Test
    public void testAddActividadPendiente() {
        profesor.addActividadPendiente(actividad);
        assertTrue(profesor.getActividadesPendientes().contains(actividad));
    }

    @Test
    public void testRemoveActividadPendiente() {
        profesor.addActividadPendiente(actividad);
        profesor.actividadCalificada(actividad);
        assertTrue(!profesor.getActividadesPendientes().contains(actividad));
    }
}

