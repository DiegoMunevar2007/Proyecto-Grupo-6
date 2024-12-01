package lprs.logica.learningPath.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.learningPath.Metadato;

public class MetadatoTest {
	private Metadato metadato;
	
	@BeforeEach
    public void setUp(){
		metadato = new Metadato("2/10/2021", "v2.1");
	}
	
	@AfterEach
    void tearDown() {
		metadato = null;
    }
	
	@Test
    void testgetFechaModificacion() {
		assertEquals("2/10/2021", metadato.getFechaModificacion());
    }
	
	@Test
    void testsetFechaModificacion() {
		metadato.setFechaModificacion("27/7/2023");
		assertEquals("27/7/2023", metadato.getFechaModificacion());
    }
	
	@Test
    void testgetVersion() {
		assertEquals("v2.1", metadato.getVersion());
    }
	
	@Test
    void testsetVersion() {
		metadato.setVersion("v4.1");
		assertEquals("v4.1", metadato.getVersion());
    }
	
	@Test
    void testgetFechaCreacion() {
		assertEquals("2/10/2021", metadato.getFechaCreacion());
    }
	
	@Test
    void testsetFechaCreacion() {
		metadato.setFechaCreacion("27/7/2020");
		assertEquals("27/7/2020", metadato.getFechaCreacion());
    }
}
