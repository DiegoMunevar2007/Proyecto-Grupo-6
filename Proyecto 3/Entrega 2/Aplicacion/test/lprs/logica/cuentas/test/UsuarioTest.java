package lprs.logica.cuentas.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

public class UsuarioTest {

    private Usuario usuario;
    private LPRS lprs;

    @BeforeEach
    public void setUp() {
        lprs = new LPRS();
        usuario = new Usuario("usuarioTest", "contraseniaTest", lprs) {};
    }

    @AfterEach
    public void tearDown() {
        usuario = null;
        lprs = null;
    }

    @Test
    public void testGetContrasenia() {
        assertEquals("contraseniaTest", usuario.getContrasenia());
    }

    @Test
    public void testGetTipo() {
        usuario.setTipo("tipoTest");
        assertEquals("tipoTest", usuario.getTipo());
    }

    @Test
    public void testSetUsuario() {
        usuario.setUsuario("nuevoUsuario");
        assertEquals("nuevoUsuario", usuario.getUsuario());
    }

    @Test
    public void testSetContrasenia() {
        usuario.setContrasenia("nuevaContrasenia");
        assertEquals("nuevaContrasenia", usuario.getContrasenia());
    }

    @Test
    public void testSetTipo() {
        usuario.setTipo("nuevoTipo");
        assertEquals("nuevoTipo", usuario.getTipo());
    }

    @Test
    public void testSetLprsActual() {
        LPRS nuevoLprs = new LPRS();
        usuario.setLprsActual(nuevoLprs);
        assertEquals(nuevoLprs, usuario.getLprsActual());
    }

    @Test
    public void testGetLprsActual() {
        assertNotNull(usuario.getLprsActual());
    }
}
