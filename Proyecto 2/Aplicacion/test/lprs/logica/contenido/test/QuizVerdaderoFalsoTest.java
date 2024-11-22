package lprs.logica.contenido.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.exceptions.CantidadOpcionesException;
import lprs.logica.contenido.QuizVerdaderoFalso;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

class QuizVerdaderoFalsoTest {

    private QuizVerdaderoFalso quiz;
    private LearningPath learningPath;

    @BeforeEach
    void setUp() {
        // Crear un LearningPath para el quiz
        ArrayList<String> objetivos = new ArrayList<>();
        objetivos.add("Comprender los conceptos básicos.");
        learningPath = new LearningPath(
            "LP001", 
            "Learning Path Test", 
            "Descripción de prueba", 
            "Fácil", 
            objetivos, 
            null, // Profesor asociado (puede ser null si no se requiere en este test)
            null  // LPRS (puede ser null si no se usa)
        );

        // Crear el quiz
        quiz = new QuizVerdaderoFalso(
            "Quiz Test", 
            "Descripción del quiz", 
            "Evaluar conocimientos básicos.", 
            20,      // Duración esperada
            true,    // Obligatoria
            "2024-12-31", 
            learningPath, 
            "Media", 
            70.0     // Calificación mínima
        );
    }

    @Test
    void testCrearActividadRealizable() {
        // Crear un estudiante
        Estudiante estudiante = new Estudiante("Estudiante Prueba", "1234", null);

        // Crear actividad realizable
        ActividadRealizable actividad = quiz.crearActividadRealizable(estudiante);

        assertNotNull(actividad, "La actividad realizable no debe ser null.");
        assertEquals(estudiante, actividad.getEstudiante(), "El estudiante asociado debe coincidir.");
        assertEquals(quiz, actividad.getActividadBase(), "La actividad base debe ser el quiz.");
    }

    @Test
    void testCrearPreguntaCerradaValida() throws Exception {
        // Crear opciones válidas para verdadero/falso
        Opcion opcionVerdadero = new Opcion("Verdadero", "La Tierra es redonda.");
        Opcion opcionFalso = new Opcion("Falso","La Tierra es plana");
        Opcion[] opciones = { opcionVerdadero, opcionFalso };

        // Crear una pregunta cerrada válida
        quiz.crearPreguntaCerrada("¿La Tierra es redonda?", opcionVerdadero, opciones);

        assertEquals(1, quiz.getPreguntasQuiz().size(), "Debe haber una pregunta en el quiz.");
        PreguntaCerrada pregunta = (PreguntaCerrada) quiz.getPreguntasQuiz().get(0);
        assertEquals("¿La Tierra es redonda?", pregunta.getEnunciado(), "El enunciado debe coincidir.");
        assertEquals(opcionVerdadero, pregunta.getCorrecta(), "La respuesta correcta debe coincidir.");
    }

    @Test
    void testCrearPreguntaCerradaConOpcionesInvalidas() {
        // Crear opciones inválidas (más de dos)
        Opcion opcionVerdadero = new Opcion("Verdadero","La Tierra es redonda.");
        Opcion opcionFalso = new Opcion("Falso","La Tierra es plana");
        Opcion opcionExtra = new Opcion("Tal vez","No estoy seguro");
        Opcion[] opciones = { opcionVerdadero, opcionFalso, opcionExtra };

        // Intentar crear una pregunta cerrada con opciones inválidas
        Exception exception = assertThrows(
            CantidadOpcionesException.class,
            () -> quiz.crearPreguntaCerrada("¿Es posible viajar en el tiempo?", opcionVerdadero, opciones),
            "Debe lanzar una excepción si el número de opciones no es válido."
        );

       
    }

    @Test
    void testCrearPreguntaCerradaSinOpciones() {
        // Crear una pregunta sin opciones
        Opcion[] opciones = {};

        // Intentar crear una pregunta cerrada sin opciones
        Exception exception = assertThrows(
            CantidadOpcionesException.class,
            () -> quiz.crearPreguntaCerrada("¿Es el agua líquida a temperatura ambiente?", null, opciones),
            "Debe lanzar una excepción si no hay opciones."
        );

        
    }
}