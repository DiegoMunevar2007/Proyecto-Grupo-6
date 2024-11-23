package lprs.logica.contenido.realizable.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.PreguntaCerradaRealizable;

public class PreguntaCerradaRealizableTest {

    private PreguntaCerrada preguntaBase;
    private Opcion opcionCorrecta;
    private Opcion opcionIncorrecta;
    private PreguntaCerradaRealizable preguntaCerradaRealizable;

    @BeforeEach
    public void setUp() {
    	// Crear opciones para la pregunta cerrada
        opcionCorrecta = new Opcion("París", "La capital de Francia.");
        Opcion[] opciones = new Opcion[] {
            new Opcion("Londres", "Capital del Reino Unido."),
            opcionCorrecta,
            new Opcion("Berlín", "Capital de Alemania."),
            new Opcion("Madrid", "Capital de España.")
        };

        // Inicializar una PreguntaCerrada antes de cada prueba
        preguntaBase = new PreguntaCerrada("¿Cuál es la capital de Francia?", opcionCorrecta, opciones);
        preguntaCerradaRealizable = new PreguntaCerradaRealizable(preguntaBase,opcionCorrecta);
    }

    @Test
    public void testGetOpcionEscogida() {
        assertEquals(opcionCorrecta, preguntaCerradaRealizable.getOpcionEscogida());
    }

    @Test
    public void testSetOpcionEscogida() {
        preguntaCerradaRealizable.setOpcionEscogida(opcionIncorrecta);
        assertEquals(opcionIncorrecta, preguntaCerradaRealizable.getOpcionEscogida());
    }

    @Test
    public void testVerificarOpcionCorrecta() {
        assertTrue(preguntaCerradaRealizable.verificarOpcion(opcionCorrecta));
    }

    @Test
    public void testVerificarOpcionIncorrecta() {
        assertFalse(preguntaCerradaRealizable.verificarOpcion(opcionIncorrecta));
    }

    @Test
    public void testIsCorrecta() {
        preguntaCerradaRealizable.verificarOpcion(opcionCorrecta);
        assertTrue(preguntaCerradaRealizable.isCorrecta());

        preguntaCerradaRealizable.verificarOpcion(opcionIncorrecta);
        assertFalse(preguntaCerradaRealizable.isCorrecta());
    }

    @Test
    public void testGetPreguntaBase() {
        assertEquals(preguntaBase, preguntaCerradaRealizable.getPreguntaBase());
    }
    
}