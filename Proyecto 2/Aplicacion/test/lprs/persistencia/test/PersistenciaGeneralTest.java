	package lprs.persistencia.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;
import lprs.persistencia.PersistenciaGeneral;

public class PersistenciaGeneralTest {

    private LPRS lprs;
    private final String archivo = "PersistenciaTest.dat";

    @BeforeEach
    public void setUp() throws Exception {
        // Crear una instancia de LPRS
        lprs = new LPRS();

        // Crear y agregar un profesor
        Profesor profesor = new Profesor("profesor1", "contrasena1", lprs);
        lprs.getManejadorSesion().agregarUsuario(profesor);

        // Crear y agregar un estudiante
        Estudiante estudiante = new Estudiante("estudiante1", "contrasena2", lprs);
        lprs.getManejadorSesion().agregarUsuario(estudiante);

        // Crear y agregar un Learning Path
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("test");
        profesor.crearLearningPath("LP Test", "Descripcion del Learning Path", "Principiante", null, keywords);
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Eliminar el archivo de prueba
        File file = new File(System.getProperty("user.dir") + "/datos/" + archivo);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testGuardarDatos() throws Exception {
        // Guardar los datos en un archivo
        PersistenciaGeneral.guardarDatos(lprs, archivo);

        // Verificar que el archivo se haya creado
        File file = new File(System.getProperty("user.dir") + "/datos/" + archivo);
        assertTrue(file.exists(), "El archivo de persistencia debe existir.");
    }

    @Test
    public void testCargarDatos() throws Exception {
        // Guardar los datos en un archivo
        PersistenciaGeneral.guardarDatos(lprs, archivo);

        // Cargar los datos desde el archivo
        LPRS lprsCargado = PersistenciaGeneral.cargarDatos(archivo);

        // Verificar que la instancia cargada no sea nula
        assertNotNull(lprsCargado, "La instancia cargada no debe ser nula.");

        // Verificar que el profesor y el estudiante se hayan cargado correctamente
        Profesor profesor = (Profesor) lprsCargado.getManejadorSesion().obtenerUsuario("profesor1");
        Estudiante estudiante = (Estudiante) lprsCargado.getManejadorSesion().obtenerUsuario("estudiante1");

        assertNotNull(profesor, "El profesor cargado no debe ser nulo.");
        assertNotNull(estudiante, "El estudiante cargado no debe ser nulo.");
    }
    @Test
    public void testCargarDatosFail() throws Exception{
    	LPRS lprsCargado = PersistenciaGeneral.cargarDatos(archivo+"a");
    	 assertNotNull(lprsCargado, "La instancia cargada no debe ser nula.");
    }
}