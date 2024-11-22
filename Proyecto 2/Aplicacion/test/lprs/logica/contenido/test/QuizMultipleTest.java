package lprs.logica.contenido.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.exceptions.CantidadOpcionesException;
import lprs.logica.contenido.QuizMultiple;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

class QuizMultipleTest {

    private QuizMultiple quiz;
    private LearningPath learningPath;

    @BeforeEach
    void setUp() {
        // Crear un LearningPath ficticio
        ArrayList<String> objetivos = new ArrayList<>();
        objetivos.add("Aprender conceptos básicos de matemáticas.");
        learningPath = new LearningPath(
            "LP001",
            "Matemáticas Básicas",
            "Descripción del Learning Path.",
            "Básico",
            objetivos,
            null, // Profesor no relevante para esta prueba
            null  // LPRS no relevante para esta prueba
            ,null
        );

        // Crear el QuizMultiple
        quiz = new QuizMultiple(
            "Quiz Matemáticas",
            "Evaluación inicial",
            "Evaluar conocimientos básicos.",
            20,    // Duración esperada
            true,  // Es obligatoria
            "2024-12-31",
            learningPath,
            "Básico",
            60.0   // Calificación mínima
        );
    }

    @Test
    void testCrearActividadRealizable() {
        // Crear un estudiante ficticio
        Estudiante estudiante = new Estudiante("Juan Pérez", "12345", null);

        // Llamar al método crearActividadRealizable
        ActividadRealizable actividad = quiz.crearActividadRealizable(estudiante);

        // Verificar que la actividad fue creada correctamente
        assertNotNull(actividad, "La actividad realizable no debe ser null.");
        assertEquals(estudiante, actividad.getEstudiante(), "El estudiante asociado debe coincidir.");
        assertEquals(quiz, actividad.getActividadBase(), "La actividad base debe ser el quiz.");
        assertTrue(quiz.getActividadesRealizablesCreadas().contains(actividad), "La actividad creada debe ser agregada a la lista de actividades creadas.");
    }

    @Test
    void testCrearPreguntaCerradaConCuatroOpciones() throws Exception {
        // Crear opciones válidas
        Opcion opcion1 = new Opcion("Opción 1","La Tierra");
        Opcion opcion2 = new Opcion("Opción 2","La Tierra");
        Opcion opcion3 = new Opcion("Opción 3","La Tierra");
        Opcion opcion4 = new Opcion("Opción 4","La Tierra");
        Opcion[] opciones = { opcion1, opcion2, opcion3, opcion4 };

        // Llamar al método crearPreguntaCerrada
        quiz.crearPreguntaCerrada("¿Cuál es 2 + 2?", opcion3, opciones);

        // Verificar que la pregunta fue creada correctamente
        assertEquals(1, quiz.getPreguntasQuiz().size(), "Debe haber exactamente una pregunta en el quiz.");
        PreguntaCerrada pregunta = (PreguntaCerrada) quiz.getPreguntasQuiz().get(0);
        assertEquals("¿Cuál es 2 + 2?", pregunta.getEnunciado(), "El enunciado debe coincidir.");
        assertEquals(opcion3, pregunta.getCorrecta(), "La respuesta correcta debe coincidir.");
    }

    @Test
    void testCrearPreguntaCerradaConOpcionesInvalidas() {
        // Crear opciones inválidas (menos de cuatro)
        Opcion opcion1 = new Opcion("Opción 1","La Tierra es");
        Opcion opcion2 = new Opcion("Opción 2","La Tierra es");
        Opcion[] opciones = { opcion1, opcion2 };

        // Intentar crear una pregunta con opciones inválidas
        CantidadOpcionesException exception = assertThrows(
            CantidadOpcionesException.class,
            () -> quiz.crearPreguntaCerrada("¿Cuál es la capital de Francia?", opcion1, opciones),
            "Debe lanzar una excepción si el número de opciones no es exactamente cuatro."
        );

        // Verificar el mensaje de la excepción
      
    }

    @Test
    void testCrearPreguntaCerradaConMasDeCuatroOpciones() {
        // Crear opciones inválidas (más de cuatro)
        Opcion opcion1 = new Opcion("Opción 1","La Tierra es");
        Opcion opcion2 = new Opcion("Opción 2","La Tierra es");
        Opcion opcion3 = new Opcion("Opción 3","La Tierra es");
        Opcion opcion4 = new Opcion("Opción 4","La Tierra es");
        Opcion opcion5 = new Opcion("Opción 5","La Tierra es");
        Opcion[] opciones = { opcion1, opcion2, opcion3, opcion4, opcion5 };

        // Intentar crear una pregunta con más de cuatro opciones
        CantidadOpcionesException exception = assertThrows(
            CantidadOpcionesException.class,
            () -> quiz.crearPreguntaCerrada("¿Qué es el agua?", opcion1, opciones),
            "Debe lanzar una excepción si el número de opciones no es exactamente cuatro."
        );

      
    }
}