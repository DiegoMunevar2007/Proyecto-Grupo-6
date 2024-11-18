package lprs.logica.contenido.test;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Resenia;
import lprs.logica.contenido.realizable.ActividadRealizable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

class ActividadTest {

    class ActividadConcreta extends Actividad {
        public ActividadConcreta(String titulo, String descripcion, String objetivo, int duracionEsperada,
                                 boolean obligatoria, String fechaLimite, LearningPath lP, String dificultad) {
            super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
        }

        @Override
        public ActividadRealizable crearActividadRealizable(Estudiante estudiante) {
            return new ActividadRealizableConcreta(estudiante, this);
        }
    }

    class ActividadRealizableConcreta extends ActividadRealizable {
        public ActividadRealizableConcreta(Estudiante estudiante, Actividad actividadBase) {
            super(estudiante);
            this.actividadBase = actividadBase;
        }

        @Override
        public void calificarActividad() {
            this.estado = "Calificada";
        }

        @Override
        public ArrayList realizarActividad() {
            return new ArrayList<>();
        }

        @Override
        public void guardarActividad(ArrayList respuestasEsperadas) {}

        @Override
        public void enviarActividad(ArrayList respuestasEsperadas) {
            this.estado = "Enviada";
        }

        @Override
        public void setEstado(String estado) {
            this.estado = estado;
        }

        @Override
        public Actividad getActividadBase() {
            return actividadBase;
        }

        

    }
    
    private Actividad actividadBase;
    
    @BeforeEach
    public void setUp() {
    	Profesor profesor2 = new Profesor("Profe", "Profe", null);
    	ArrayList<String> objetivos2 = new ArrayList<String>();
    	objetivos2.add("Objetivo 2");
        LearningPath learningPath = new LearningPath("b", "Titulo learning path2", "Descripcion", "Principiante", objetivos2,profesor2, null);
    	actividadBase = new ActividadConcreta(
                "ActividadP",
                "Descripción",
                "Objetivo",
                60,
                true,
                "2024-12-31",
                learningPath,
                "Media"
        ); ;
        actividadBase.setTitulo("Actividad de Prueba");
        actividadBase.setNivelDificultad("Alta");
        actividadBase.setDescripcion("Descripción de prueba");
        actividadBase.setObjetivo("Objetivo de prueba");
        actividadBase.setDuracionEsperada(120); // 2 horas
        actividadBase.setObligatoria(true);
        actividadBase.setFechaLimite("2024-12-31");
    }

    @Test
    public void testGetTitulo() {
        assertEquals("Actividad de Prueba", actividadBase.getTitulo(), "El título debe ser 'Actividad de Prueba'");
    }

    @Test
    public void testGetNivelDificultad() {
        assertEquals("Alta", actividadBase.getNivelDificultad(), "El nivel de dificultad debe ser 'Alta'");
    }

    @Test
    public void testGetDescripcion() {
        assertEquals("Descripción de prueba", actividadBase.getDescripcion(), "La descripción debe ser 'Descripción de prueba'");
    }

    @Test
    public void testGetObjetivo() {
        assertEquals("Objetivo de prueba", actividadBase.getObjetivo(), "El objetivo debe ser 'Objetivo de prueba'");
    }

    @Test
    public void testGetDuracionEsperada() {
        assertEquals(120, actividadBase.getDuracionEsperada(), "La duración esperada debe ser 120 minutos");
    }

    @Test
    public void testIsObligatoria() {
        assertTrue(actividadBase.isObligatoria(), "La actividad debe ser obligatoria");
    }

    @Test
    public void testGetFechaLimite() {
        assertEquals("2024-12-31", actividadBase.getFechaLimite(), "La fecha límite debe ser '2024-12-31'");
    }

    @Test
    public void testGetActividadesRealizablesCreadas() {
        ArrayList<ActividadRealizable> actividadesRealizables = actividadBase.getActividadesRealizablesCreadas();
        assertNotNull(actividadesRealizables, "La lista de actividades realizables no debe ser nula");
        assertTrue(actividadesRealizables.isEmpty(), "La lista de actividades realizables debe estar vacía inicialmente");
    }

    @Test
    public void testAddActividadPrevia() {
    	Profesor profesor2 = new Profesor("Profe", "Profe", null);
    	ArrayList<String> objetivos2 = new ArrayList<String>();
    	objetivos2.add("Objetivo 2");
        LearningPath learningPath = new LearningPath("b", "Titulo learning path2", "Descripcion", "Principiante", objetivos2,profesor2, null);
        Actividad actividadPrevia = new ActividadConcreta(
                "ActividadP",
                "Descripción",
                "Objetivo",
                60,
                true,
                "2024-12-31",
                learningPath,
                "Media"
        ); 
        actividadBase.addActividadPrevia(actividadPrevia);
        assertEquals(1, actividadBase.getActividadesPrevias().size(), "Debe haber una actividad previa");
        assertTrue(actividadBase.getActividadesPrevias().contains(actividadPrevia), "La actividad previa debe estar en la lista");
    }

    @Test
    public void testRemoveActividadPrevia() {
    	Profesor profesor2 = new Profesor("Profe", "Profe", null);
    	ArrayList<String> objetivos2 = new ArrayList<String>();
    	objetivos2.add("Objetivo 2");
        LearningPath learningPath = new LearningPath("b", "Titulo learning path2", "Descripcion", "Principiante", objetivos2,profesor2, null);
        Actividad actividadPrevia = new ActividadConcreta(
                "ActividadP",
                "Descripción",
                "Objetivo",
                60,
                true,
                "2024-12-31",
                learningPath,
                "Media"
        ); 
        actividadBase.addActividadPrevia(actividadPrevia);
        actividadBase.removeActividadPrevia(actividadPrevia);
        assertEquals(0, actividadBase.getActividadesPrevias().size(), "La lista de actividades previas debe estar vacía");
    }

    @Test
    public void testAddResenia() {
    	Profesor profesor1 = new Profesor("Profe", "Profe", null);
        Resenia resenia = new Resenia(profesor1, "Fue interesante", 4.5);
        actividadBase.addResenia(resenia);
        assertEquals(1, actividadBase.getResenias().size(), "La lista de reseñas debe tener una reseña");
    }


    @Test
    void testCrearActividad() {
    	Profesor profesor1 = new Profesor("Profe", "Profe", null);
    	ArrayList<String> objetivos = new ArrayList<String>();
    	objetivos.add("Objetivo 1");
        LearningPath learningPath = new LearningPath("a", "Titulo learning path", "Descripcion", "Principiante", objetivos,profesor1, null);
        Actividad actividad = new ActividadConcreta(
                "Título",
                "Descripción",
                "Objetivo",
                60,
                true,
                "2024-12-31",
                learningPath,
                "Media"
        );

        assertEquals("Título", actividad.getTitulo());
        assertEquals("Descripción", actividad.getDescripcion());
        assertTrue(actividad.isObligatoria());
        assertEquals("Media", actividad.getNivelDificultad());
    }

    @Test
    void testCrearActividadRealizable() {
    	Profesor profesor2 = new Profesor("Profe", "Profe", null);
    	ArrayList<String> objetivos2 = new ArrayList<String>();
    	objetivos2.add("Objetivo 2");
        LearningPath learningPath = new LearningPath("b", "Titulo learning path2", "Descripcion", "Principiante", objetivos2,profesor2, null);
        Actividad actividad = new ActividadConcreta(
                "Título",
                "Descripción",
                "Objetivo",
                60,
                true,
                "2024-12-31",
                learningPath,
                "Media"
        );

        Estudiante estudiante = new Estudiante("Estudiante", "Hola", null);
        ActividadRealizable realizable = actividad.crearActividadRealizable(estudiante);

        assertNotNull(realizable);
        assertEquals(estudiante, realizable.getEstudiante());
        assertEquals("", realizable.getEstado());
    }
    
    
}
