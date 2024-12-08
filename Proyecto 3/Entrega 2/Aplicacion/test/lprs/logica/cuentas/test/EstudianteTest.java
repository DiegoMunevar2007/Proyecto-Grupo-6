
package lprs.logica.cuentas.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class EstudianteTest {

    private Estudiante estudiante;
    private LPRS lprs;
    private LearningPath learningPath;
    private Avance avance;

    @BeforeEach
    public void setUp() {
        ArrayList<String> objetivos = new ArrayList<>();
        objetivos.add("Objective 1");
        objetivos.add("Objective 2");

        // Create a list of keywords
        ArrayList<String> keyWords = new ArrayList<>();
        keyWords.add("Keyword1");
        keyWords.add("Keyword2");

        // Create a mock professor
        Profesor profesorCreador = new Profesor("usuarioTest", "contraseniaTest", lprs);

        // Create a mock LPRS instance
        lprs = new LPRS();

        // Create a LearningPath instance
        learningPath = new LearningPath("LP1", "Learning Path Title", "Learning Path Description", 
                                        "Intermediate", objetivos, profesorCreador, lprs, keyWords);

        avance = new Avance("2023-10-01", learningPath);
        estudiante = new Estudiante("usuarioTest", "contraseniaTest", lprs);
    }

    @AfterEach
    public void tearDown() {
        estudiante = null;
        lprs = null;
        learningPath = null;
        avance = null;
    }

    @Test
    public void testInscribirLearningPath() throws Exception {
        lprs.getManejadorLP().addLearningPath(learningPath);
        estudiante.inscribirLearningPath("LP1");
        List<LearningPath> learningPaths = estudiante.getLearningPathsInscritos();
        assertTrue(learningPaths.contains(learningPath));
    }

    @Test
    public void testEliminarLearningPath() throws Exception {
        lprs.getManejadorLP().addLearningPath(learningPath);
        estudiante.inscribirLearningPath("LP1");
        estudiante.eliminarLearningPath("LP1");
        List<LearningPath> learningPaths = estudiante.getLearningPathsInscritos();
        assertFalse(learningPaths.contains(learningPath));
    }

    @Test
    public void testGetLearningPathsInscritos() {
        List<LearningPath> learningPaths = estudiante.getLearningPathsInscritos();
        assertNotNull(learningPaths);
    }

    @Test
    public void testGetAvancesEstudiante() {
        HashMap<String, Avance> avances = estudiante.getAvancesEstudiante();
        assertNotNull(avances);
    }

    @Test
    public void testAddAvance() {
        estudiante.addAvance(avance);
        HashMap<String, Avance> avances = estudiante.getAvancesEstudiante();
        assertTrue(avances.containsValue(avance));
    }


	@Test
	public void testGetAvance() throws Exception {
	    lprs.getManejadorLP().addLearningPath(learningPath);
	    estudiante.inscribirLearningPath("LP1");
	    Avance avance = estudiante.getAvance("LP1");
	    assertNotNull(avance);
	}
	
	@Test
	public void testObtenerAvance() throws Exception {
	    lprs.getManejadorLP().addLearningPath(learningPath);
	    estudiante.inscribirLearningPath("LP1");
	    Avance avance = estudiante.obtenerAvance("LP1");
	    assertNotNull(avance);
	}

}
