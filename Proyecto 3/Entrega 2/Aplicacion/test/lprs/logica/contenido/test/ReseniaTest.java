package lprs.logica.contenido.test;	

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.Resenia;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;

public class ReseniaTest {

    private Resenia resenia;
    private Profesor autor;

    @BeforeEach
    void setUp() {
        // Creamos un autor y una reseña antes de cada prueba
        autor = new Profesor("usuario123", "contraseña123", null);
        resenia = new Resenia(autor, "Contenido inicial", 4.5);
    }

    @AfterEach
    void tearDown() {
        // Limpiamos las referencias después de cada prueba
        resenia = null;
        autor = null;
    }

    @Test
    void testConstructorAndGetID() {
        // Verificar que el ID no sea nulo y que sea un número
        assertNotNull(resenia.getID());
        assertTrue(resenia.getID().matches("\\d+"));
    }

    @Test
    void testAsignarID() {
        String nuevoID = resenia.asignarID();
        assertNotNull(nuevoID);
        assertTrue(nuevoID.matches("\\d+"));
        assertNotEquals(resenia.getID(), nuevoID);
    }

    @Test
    void testGetContenido() {
        assertEquals("Contenido inicial", resenia.getContenido());
    }

    @Test
    void testSetContenido() {
        resenia.setContenido("Nuevo contenido");
        assertEquals("Nuevo contenido", resenia.getContenido());
    }

    @Test
    void testGetRating() {
        assertEquals(4.5, resenia.getRating());
    }

    @Test
    void testSetRating() {
        resenia.setRating(3.8);
        assertEquals(3.8, resenia.getRating());
    }

    @Test
    void testGetAutor() {
        assertEquals(autor, resenia.getAutor());
    }

    @Test
    void testSetAutor() {
        Usuario nuevoAutor = new Profesor("usuarioNuevo", "nuevaContraseña", null);
        resenia.setAutor(nuevoAutor);
        assertEquals(nuevoAutor, resenia.getAutor());
    }
}
