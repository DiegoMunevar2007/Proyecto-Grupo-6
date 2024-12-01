package lprs.manejador.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.exceptions.ContraseniaIncorrectaException;
import lprs.exceptions.UsuarioNotFoundException;
import lprs.exceptions.UsuarioRepetidoException;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.manejador.ManejadorSesion;
import lprs.principal.LPRS;

public class ManejadorSesionTest {

    private ManejadorSesion manejadorSesion;
    private LPRS lprsActual;

    @BeforeEach
    void setUp() {
        lprsActual = new LPRS();
        manejadorSesion = new ManejadorSesion(lprsActual);
    }

    @AfterEach
    void tearDown() {
        manejadorSesion = null;
        lprsActual = null;
    }

    @Test
    void testCrearUsuarioEstudiante() throws Exception {
        manejadorSesion.crearUsuario("Estudiante Test", "1234", 1);
        Usuario usuario = manejadorSesion.obtenerUsuario("Estudiante Test");
        assertTrue(usuario instanceof Estudiante);
    }

    @Test
    void testCrearUsuarioProfesor() throws Exception {
        manejadorSesion.crearUsuario("Profesor Test", "5678", 2);
        Usuario usuario = manejadorSesion.obtenerUsuario("Profesor Test");
        assertTrue(usuario instanceof Profesor);
    }

    @Test
    void testCrearUsuarioRepetido() {
        assertThrows(UsuarioRepetidoException.class, () -> {
            manejadorSesion.crearUsuario("Profesor Test", "5678", 2);
            manejadorSesion.crearUsuario("Profesor Test", "5678", 2);
        });
    }

    @Test
    void testObtenerUsuarioNoExistente() {
        assertThrows(UsuarioNotFoundException.class, () -> {
            manejadorSesion.obtenerUsuario("Usuario Inexistente");
        });
    }

    @Test
    void testIniciarSesionContrasenaIncorrecta() throws Exception {
        manejadorSesion.crearUsuario("Profesor Test", "5678", 2);
        assertThrows(ContraseniaIncorrectaException.class, () -> {
            manejadorSesion.iniciarSesion("Profesor Test", "wrongpassword");
        });
    }

    @Test
    void testIniciarSesionUsuarioNoExistente() throws Exception {
       assertEquals(null, manejadorSesion.iniciarSesion("Usuario Inexistente", "1234"));
    }

    @Test
    void testIniciarSesionCorrecta() throws Exception {
        manejadorSesion.crearUsuario("Profesor Test", "5678", 2);
        Usuario usuario = manejadorSesion.iniciarSesion("Profesor Test", "5678");
        assertNotNull(usuario);
        assertEquals("Profesor Test", usuario.getUsuario());
    }

    @Test
    void testAgregarUsuario() throws Exception {
        Usuario profesor = new Profesor("Profesor Test", "5678", lprsActual);
        manejadorSesion.agregarUsuario(profesor);
        Usuario usuario = manejadorSesion.obtenerUsuario("Profesor Test");
        assertNotNull(usuario);
        assertEquals("Profesor Test", usuario.getUsuario());
    }

    @Test
    void testSetUsuarios() {
        HashMap<String, Usuario> usuarios = new HashMap<>();
        usuarios.put("Profesor Test", new Profesor("Profesor Test", "5678", lprsActual));
        manejadorSesion.setUsuarios(usuarios);
        assertEquals(usuarios, manejadorSesion.getUsuarios());
    }

    @Test
    void testGetUsuariosLista() throws Exception {
        manejadorSesion.crearUsuario("Profesor Test", "5678", 2);
        assertEquals(1, manejadorSesion.getUsuariosLista().size());
    }

    @Test
    void testGetEstudianteFinal() {
        assertEquals("Estudiante", manejadorSesion.getEstudianteFinal());
    }

    @Test
    void testGetProfesorFinal() {
        assertEquals("Profesor", manejadorSesion.getProfesorFinal());
    }

    @Test
    void testGetLprsActual() {
        assertEquals(lprsActual, manejadorSesion.getLprsActual());
    }

    @Test
    void testSetLprsActual() {
        LPRS nuevoLprs = new LPRS();
        manejadorSesion.setLprsActual(nuevoLprs);
        assertEquals(nuevoLprs, manejadorSesion.getLprsActual());
    }
}