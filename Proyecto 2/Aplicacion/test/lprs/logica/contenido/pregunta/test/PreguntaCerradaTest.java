package lprs.logica.contenido.pregunta.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;

class PreguntaCerradaTest {

    private PreguntaCerrada preguntaCerrada;
    private Opcion correcta;
    private Opcion[] opciones;

    @BeforeEach
    void setUp() {
        // Crear opciones para la pregunta cerrada
        correcta = new Opcion("París", "La capital de Francia.");
        opciones = new Opcion[] {
            new Opcion("Londres", "Capital del Reino Unido."),
            correcta,
            new Opcion("Berlín", "Capital de Alemania."),
            new Opcion("Madrid", "Capital de España.")
        };

        // Inicializar una PreguntaCerrada antes de cada prueba
        preguntaCerrada = new PreguntaCerrada("¿Cuál es la capital de Francia?", correcta, opciones);
    }

    @AfterEach
    void tearDown() {
        // Limpiar las referencias después de cada prueba
        preguntaCerrada = null;
        correcta = null;
        opciones = null;
    }

    @Test
    void testGetCorrecta() {
        assertEquals(correcta, preguntaCerrada.getCorrecta(), "La opción correcta debería ser 'París'.");
    }

    @Test
    void testSetCorrecta() {
        Opcion nuevaCorrecta = new Opcion("Madrid", "La capital de España.");
        preguntaCerrada.setCorrecta(nuevaCorrecta);
        assertEquals(nuevaCorrecta, preguntaCerrada.getCorrecta(), "La opción correcta debería ser 'Madrid'.");
    }

    @Test
    void testGetOpciones() {
        assertArrayEquals(opciones, preguntaCerrada.getOpciones(), "Las opciones no coinciden con las esperadas.");
    }

    @Test
    void testSetOpciones() {
        Opcion[] nuevasOpciones = {
            new Opcion("Roma", "Capital de Italia."),
            new Opcion("Lisboa", "Capital de Portugal.")
        };
        preguntaCerrada.setOpciones(nuevasOpciones);
        assertArrayEquals(nuevasOpciones, preguntaCerrada.getOpciones(), "Las nuevas opciones no coinciden.");
    }

    @Test
    void testGetEnunciado() {
        assertEquals("¿Cuál es la capital de Francia?", preguntaCerrada.getEnunciado());
    }

    @Test
    void testSetEnunciado() {
        preguntaCerrada.setEnunciado("¿Cuál es la capital de España?");
        assertEquals("¿Cuál es la capital de España?", preguntaCerrada.getEnunciado());
    }
}
