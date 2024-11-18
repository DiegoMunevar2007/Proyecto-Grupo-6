package lprs.logica.contenido.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.contenido.realizable.RecursoRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

class RecursoEducativoTest {

    private RecursoEducativo recursoEducativo;
    private LearningPath learningPath;
    private RecursoRealizable realizable;

    @BeforeEach
    void setUp() {
        // Crear un objeto LearningPath ficticio para pruebas
    	Estudiante estudiante = new Estudiante("pepe", "123", null); 
        
        // Inicializar un objeto RecursoEducativo antes de cada prueba
        recursoEducativo = new RecursoEducativo(
            "Título de Recurso",
            "Descripción del recurso",
            "Objetivo del recurso",
            120, // duración esperada en minutos
            true, // obligatoria
            "2024-12-31", // fecha límite
            learningPath, // objeto LearningPath
            "Media", // dificultad
            "Video", // tipo de recurso
            "https://example.com/recurso"
            
        		
        		);
        recursoEducativo.crearActividadRealizable(estudiante);
        
    }

    @AfterEach
    void tearDown() {
        // Limpiar las referencias después de cada prueba
        recursoEducativo = null;
        learningPath = null;
    }

    @Test
    void testGetUrl() {
        assertEquals("https://example.com/recurso", recursoEducativo.getUrl());
    }

    @Test
    void testSetUrl() {
        recursoEducativo.setUrl("https://nuevaurl.com/recurso");
        assertEquals("https://nuevaurl.com/recurso", recursoEducativo.getUrl());
    }

    @Test
    void testGetTipoRecurso() {
        assertEquals("Video", recursoEducativo.getTipoRecurso());
    }

    @Test
    void testSetTipoRecurso() {
        recursoEducativo.setTipoRecurso("Documento");
        assertEquals("Documento", recursoEducativo.getTipoRecurso());
    }

    @Test
    void testSetEstado() {
        recursoEducativo.setEstado(true);
        assertTrue(recursoEducativo.estado);
    }
    
   
}

	
	

