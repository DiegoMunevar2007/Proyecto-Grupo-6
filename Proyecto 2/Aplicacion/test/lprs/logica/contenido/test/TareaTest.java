package lprs.logica.contenido.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lprs.exceptions.ActividadPreviaException;
import lprs.logica.contenido.Seccion;
import lprs.logica.contenido.Tarea;
import lprs.logica.contenido.realizable.TareaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

public class TareaTest {

	
	private Tarea tarea; 
	private TareaRealizable trealizable;
	private Seccion seccion;
	@BeforeEach
	public void setup() {
		Profesor profesor2 = new Profesor("Profe", "Profe", null);
    	ArrayList<String> objetivos2 = new ArrayList<String>();
    	objetivos2.add("Objetivo 2");
        LearningPath learningPath = new LearningPath("b", "Titulo learning path2", "Descripcion", "Principiante", objetivos2,profesor2, null);
        tarea = new Tarea(learningPath.crearTarea("tarea", "descripcion", "objetivos", 10 , true,
    			"10/12/2024"));
		Estudiante estudiante = new Estudiante("pepe", "123", null); 
		seccion = new Seccion(1, "10°a", "datos", "objetivo", "contenido", "ejemplo",
				"explicacion", "pista", "resultadoEsperado");
        trealizable =  tarea.crearActividadRealizable(estudiante);
        
		
		
	}
	
	
	
	
	@AfterEach
	void tearDown() throws Exception{
		tarea.borrar_secciones();
		tarea = null;
		trealizable = null; 
		seccion=null;
	}
	
	@Test
	void TestcrearTarea() {
		
		assertEquals("tarea",tarea.getTitulo(),"Error creando la tarea");
		
	}
	
	@Test
	void TestRealizarActividad() {
		assertEquals(trealizable,tarea.RealizarActividad(trealizable),"Error realizando la tarea");
	}
	
	@Test
	void TestcrearSeccion() {
		tarea.crearSeccion(1, "10°a", "datos", "objetivo", "contenido", "ejemplo",
				"explicacion", "pista", "resultadoEsperado");
		assertEquals(seccion.getTitulo(),tarea.getSecciones().getLast().getTitulo(),"Error realizando la tarea");
	}
	
	@Test
	void TestsetSecciones() {
		tarea.crearSeccion(1, "10°a", "datos", "objetivo", "contenido", "ejemplo",
				"explicacion", "pista", "resultadoEsperado");
		tarea.setSecciones(tarea.secciones);
		assertEquals(seccion.getTitulo(),tarea.getSecciones().getLast().getTitulo(),"Error realizando la tarea");
	}
	
	@Test
	void TestremoveSeccion() {
		tarea.removeSeccion(seccion);
		assertEquals(new ArrayList<Seccion>(),tarea.secciones,"Error realizando la tarea");
	}
}
