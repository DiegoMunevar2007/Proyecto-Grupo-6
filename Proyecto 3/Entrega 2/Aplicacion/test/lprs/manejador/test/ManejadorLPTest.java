package lprs.manejador.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.manejador.ManejadorLP;
import lprs.principal.LPRS;

public class ManejadorLPTest {

    private ManejadorLP manejadorLP;
    private Profesor profesor;
    private LPRS lprsActual;

    @BeforeEach
    void setUp() throws Exception {
        lprsActual = new LPRS();
        manejadorLP = lprsActual.getManejadorLP();
        
        lprsActual.getManejadorSesion().crearUsuario("Profesor Test", "5678", 2);
        this.profesor = (Profesor) lprsActual.getManejadorSesion().getUsuarios().get("Profesor Test");
    }

    @AfterEach
    void tearDown() {
        manejadorLP = null;
        profesor = null;
        lprsActual = null;
    }

    @Test
    void testCrearLearningPath() {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", null, keywords);
        assertNotNull(manejadorLP.getLearningPath("0"));
    }

    @Test
    void testGetLearningPath() {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", null, keywords);
        assertEquals("LP Test", manejadorLP.getLearningPath("0").getTitulo());
    }

    @Test
    void testAddLearningPath() {
    	LearningPath learningPath = new LearningPath("1","LP Test", "Descripcion del Learning Path","Principiante", new ArrayList<String>() {{add("a");}}, profesor,lprsActual, new ArrayList<String>() {{add("test");}});
        manejadorLP.addLearningPath(learningPath);
        assertTrue(manejadorLP.getLearningPaths().contains(learningPath));
    }

    @Test
    void testAddLearningPathKeyWord() {
        LearningPath learningPath = new LearningPath("1","LP Test", "Descripcion del Learning Path","Principiante", new ArrayList<String>() {{add("a");}}, profesor,lprsActual, new ArrayList<String>());
        manejadorLP.addLearningPathKeyWord("test", learningPath);
        assertTrue(manejadorLP.getLearningPathsKeywords("test").contains(learningPath));
    }

    @Test
    void testGetKeyWords() {
    	LearningPath learningPath = new LearningPath("1","LP Test", "Descripcion del Learning Path","Principiante", new ArrayList<String>() {{add("a");}}, profesor,lprsActual, new ArrayList<String>());
        manejadorLP.addLearningPathKeyWord("test", learningPath);
        assertTrue(manejadorLP.getKeyWords().contains("test"));
    }
    @Test
    void testLearningPathsHashMap() {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", null, keywords);
        assertNotNull(manejadorLP.learningPathsHashMap().get("0"));
    }

    @Test
    void testGetLearningPathsDisponibles() {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", null, keywords);
        assertTrue(manejadorLP.getLearningPathsDisponibles().contains(manejadorLP.getLearningPath("0")));
    }

    @Test
    void testAddLearningPathKeywordExistente() {
        LearningPath learningPath1 = new LearningPath("1", "LP Test 1", "Descripcion del Learning Path 1", "Principiante", new ArrayList<String>() {{ add("test"); }}, profesor, lprsActual, new ArrayList<String>() {{add("test");}});
        manejadorLP.addLearningPath(learningPath1);

        LearningPath learningPath2 = new LearningPath("2", "LP Test 2", "Descripcion del Learning Path 2", "Intermedio", new ArrayList<String>() {{ add("test"); }}, profesor, lprsActual, new ArrayList<String>(){{add("test");}});
        manejadorLP.addLearningPath(learningPath2);

        // Verify that both LearningPaths are associated with the keyword "test"
        ArrayList<LearningPath> learningPaths = manejadorLP.getLearningPathsKeywords("test");
        assertTrue(learningPaths.contains(learningPath1));
        assertTrue(learningPaths.contains(learningPath2));
        assertEquals(2, learningPaths.size(), "The keyword 'test' should have two learning paths associated with it.");
    }

    @Test
    void testSetLearningPathsDisponibles() {
        ArrayList<LearningPath> learningPathsDisponibles = new ArrayList<>();
        LearningPath learningPath = new LearningPath("1", "LP Test", "Descripcion del Learning Path", "Principiante", new ArrayList<String>() {{ add("a"); }}, profesor, lprsActual, new ArrayList<String>() {{ add("test"); }});
        learningPathsDisponibles.add(learningPath);
        manejadorLP.setLearningPathsDisponibles(learningPathsDisponibles);
        assertEquals(learningPathsDisponibles, manejadorLP.getLearningPathsDisponibles());
    }
    @Test
    void testAddKeyWordExistente() {
        LearningPath learningPath1 = new LearningPath("1", "LP Test 1", "Descripcion del Learning Path 1", "Principiante", new ArrayList<String>() {{ add("a"); }}, profesor, lprsActual, new ArrayList<String>() {{ add("test"); }});
        LearningPath learningPath2 = new LearningPath("2", "LP Test 2", "Descripcion del Learning Path 2", "Intermedio", new ArrayList<String>() {{ add("b"); }}, profesor, lprsActual, new ArrayList<String>() {{ add("test"); }});
        
        manejadorLP.addLearningPathKeyWord("test", learningPath1);
        manejadorLP.addLearningPathKeyWord("test", learningPath2);
        
        ArrayList<LearningPath> learningPaths = manejadorLP.getLearningPathsKeywords("test");
        assertTrue(learningPaths.contains(learningPath1));
        assertTrue(learningPaths.contains(learningPath2));
        assertEquals(2, learningPaths.size(), "La palabra clave test deberia tener dos learning paths asociados.");
    }
}