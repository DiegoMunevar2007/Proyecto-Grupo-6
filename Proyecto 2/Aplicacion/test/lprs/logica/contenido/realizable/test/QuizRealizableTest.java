package lprs.logica.contenido.realizable.test;



import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.exceptions.ActividadPreviaException;
import lprs.logica.contenido.Quiz;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.PreguntaCerradaRealizable;
import lprs.logica.contenido.realizable.QuizRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

class QuizRealizableTest {

    private QuizRealizable quizRealizable;
    private Quiz quiz;
    private Estudiante estudiante;
    private LPRS lprsActual;	
    private Profesor profesor;
    private LearningPath lP;
    @BeforeEach
    void setUp() throws Exception{
    	lprsActual = new LPRS();
        
        lprsActual.getManejadorSesion().crearUsuario("Profesor Test", "5678", 2);
		lprsActual.getManejadorSesion().crearUsuario("Estudiante Test", "1234", 1);
        this.profesor = (Profesor) lprsActual.getManejadorSesion().getUsuarios().get("Profesor Test");
        this.estudiante = (Estudiante) lprsActual.getManejadorSesion().getUsuarios().get("Estudiante Test");
        ArrayList<String> keywords = new ArrayList<String>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", null, keywords);
        // Crear el estudiante y asociar un avance
        
        estudiante.inscribirLearningPath("0");
        lP = lprsActual.getManejadorLP().getLearningPath("0");
        quiz =lP.crearQuizVerdaderoFalso("Quiz V/F", "descripción", "a", 10, false, null,10);
       
        quizRealizable = new QuizRealizable(estudiante, quiz);
    }
    @AfterEach
    void tearDown() {
    	quizRealizable = null;
        quiz = null;
        estudiante = null;
        lprsActual = null;
        profesor = null;
        lP = null;
    }

    @Test
    void testCalificacion() {
    	quizRealizable.setCalificacion(100);
        assertEquals(100, quizRealizable.getCalificacion());
    }
    @Test
    void testException() throws ActividadPreviaException {
    	Quiz quiz2 =lP.crearQuizMultiple("Quiz Multiple", "descripción", "a", 10, false, null, 10);
        quiz.addActividadPrevia(quiz2);
        assertThrows(ActividadPreviaException.class, () -> {
            quizRealizable.realizarActividad();
        });
    }
    @Test
    void testRealizarActividad() throws ActividadPreviaException {
        ArrayList<PreguntaCerrada> preguntas = quizRealizable.realizarActividad();
        assertNotNull(preguntas);
        assertEquals(quiz.getPreguntasQuiz(), preguntas);
    }

    @Test
    void testGuardarActividad() {
        ArrayList<PreguntaCerradaRealizable> respuestas = new ArrayList<>();
        quizRealizable.guardarActividad(respuestas);
        assertEquals(respuestas, quizRealizable.getPreguntas());
    }

    @Test
    void testEnviarActividad() {
        ArrayList<PreguntaCerradaRealizable> respuestas = new ArrayList<>();
        quizRealizable.enviarActividad(respuestas);
        assertEquals(respuestas, quizRealizable.getPreguntas());
        assertTrue(quizRealizable.getCalificacion() >= 0);
    }
    @Test
    void testEnviarActividadCalificacion() {
        ArrayList<PreguntaCerradaRealizable> respuestas = new ArrayList<>();
        quizRealizable.incCorrectas();
        quizRealizable.enviarActividad(respuestas);
        assertTrue(quizRealizable.getCalificacion() >= 0);
    }

    @Test
    void testIncCorrectas() {
        quizRealizable.incCorrectas();
        assertEquals(1, quizRealizable.getCorrectas());
        quizRealizable.incCorrectas();
        assertEquals(2,quizRealizable.getCorrectas());
    }

}