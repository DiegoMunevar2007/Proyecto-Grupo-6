package lprs.consola.estudiante;

import java.util.List;
import java.util.Scanner;

import lprs.consola.ConsolaPrincipal;
import lprs.exceptions.NoLearningPathsException;
import lprs.logica.contenido.*;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;
import lprs.persistencia.PersistenciaGeneral;
import lprs.principal.LPRS;

public class ConsolaEstudiante extends ConsolaPrincipal {
    private Estudiante estudiante;
    private ConsolaSesionEstudiante consolaSesion;
    private ConsolaActividadEstudiante consolaActividad;

    public ConsolaEstudiante(LPRS lprsActual) {
        super(lprsActual);
        this.estudiante = null;
        this.consolaSesion = new ConsolaSesionEstudiante(lprsActual, this);
        this.consolaActividad = new ConsolaActividadEstudiante( this);
    }

    public ConsolaSesionEstudiante getConsolaSesion() {
        return consolaSesion;
    }


    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void mostrarConsolaEstudiante() {
        System.out.println("Bienvenido " + estudiante.getUsuario());
        String[] opciones = {"Ver mis Leaning Paths", "Inscribir un learning Path", "Ver mi avance",
                "Realizar una actividad", "Reseñar una actividad",
                "Salir"};

        boolean salir = false;
        while (!salir) {
            mostrarOpciones(opciones.length, opciones);
            int opcion = pedirInt("Seleccione una opción: ");
            if (opcion == 1) {
                try {
                    mostrarLearningPaths();
                } catch (NoLearningPathsException e) {
                    System.out.println(e.getMessage());
                }
            } else if (opcion == 2) {
                String id = "";
                try {
                    id = escogerLearningPath();
                    estudiante.inscribirLearningPath(id);
                } catch (NoLearningPathsException e) {
                    System.out.println(e.getMessage());
                } finally {

                }
            } else if (opcion == 3) {

            } else if (opcion == 4) {
                Actividad actividad = null;
                try {
                    actividad = escogerActividad();
                } catch (NoLearningPathsException e) {
                    System.out.println(e.getMessage());

                }
                if (actividad == null) {

                }
                if (actividad instanceof Quiz) {
                    consolaActividad.realizarQuiz((Quiz) actividad);
                } else if (actividad instanceof Examen) {
                    consolaActividad.realizarExamen((Examen) actividad);
                } else if (actividad instanceof Encuesta) {
                    consolaActividad.realizarEncuesta((Encuesta) actividad);
                } else if (actividad instanceof RecursoEducativo) {
                    consolaActividad.realizarRecurso((RecursoEducativo) actividad);
                } else if (actividad instanceof Tarea) {
                    consolaActividad.realizarTarea((Tarea) actividad);
                }

            } else if (opcion == 5) {
                System.out.println("Seleccione una actividad para reseñar: ");
                Actividad actividad = null;
                try {
                    actividad = obtenerActividadRealizada();
                    reseniarActividad(actividad, estudiante);
                } catch (NoLearningPathsException e) {
                    System.out.println(e.getMessage());

                }

            } else if (opcion == 6) {
                System.out.println("Hasta luego!");
                salir = true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            }
        }
    }

    public Actividad obtenerActividadRealizada() throws NoLearningPathsException {
        String ID = "";
        try {
            ID = mostrarLearningPaths();
        } catch (NoLearningPathsException e) {
            throw e;
        }
        List<Actividad> actividades = estudiante.getAvance(ID).getActividadesCompletadasLista();
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades disponibles en este Learning Path.");
            return null;
        }
        System.out.println("Seleccione una actividad: ");
        for (int i = 0; i < actividades.size(); i++) {
            System.out.println(i + 1 + ". " + actividades.get(i).getTitulo());
        }
        int opcion = pedirInt("Seleccione una actividad: ");
        if (opcion < 1 || opcion > actividades.size()) {
            System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
            return obtenerActividadRealizada();
        }
        return actividades.get(opcion - 1);


    }
    public String mostrarLearningPaths() throws NoLearningPathsException {
        List<LearningPath> learningPathsDisponibles = estudiante.getLearningPathsInscritos();
        if (learningPathsDisponibles.isEmpty()) {
            throw new NoLearningPathsException("No hay Learning Paths disponibles.");
        }
        for (int i = 0; i < learningPathsDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + learningPathsDisponibles.get(i).getTitulo());
        }
        int opcion = pedirInt("Seleccione un Learning Path: ");
        if (opcion < 1 || opcion > learningPathsDisponibles.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            mostrarLearningPaths();
        }
        LearningPath lP = learningPathsDisponibles.get(opcion - 1);
        System.out.println("Esta es la informacion del Learning Path seleccionado: ");
        System.out.println("Titulo: " + lP.getTitulo());
        System.out.println("Descripcion: " + learningPathsDisponibles.get(opcion - 1).getDescripcion());
        System.out.println("Nivel de dificultad: " + lP.getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : lP.getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + lP.getDuracion());
        System.out.println("Rating: " + lP.getRating());
        return lP.getID();
    }

    public Actividad escogerActividad() throws  NoLearningPathsException {
        Scanner lectura = new Scanner(System.in);
        String ID = null;
        try {
            ID = mostrarLearningPaths();
        } catch (NoLearningPathsException e) {
            throw e;
        }
        LearningPath lP = estudiante.getLprsActual().getManejadorLP().getLearningPath(ID);
        List<Actividad> actividades = lP.getActividades();
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades disponibles en este Learning Path.");
            return null;
        }
        System.out.println("Seleccione una actividad: ");
        for (int i = 0; i < actividades.size(); i++) {
            System.out.println(i + 1 + ". " + actividades.get(i).getTitulo());
        }
        int opcion = lectura.nextInt();
        if (opcion < 1 || opcion > actividades.size()) {
            System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
            return escogerActividad();
        }
        return actividades.get(opcion - 1);
    }

    public ActividadRealizable RealizarActividad(Actividad actividad) {
        return actividad.crearActividadRealizable(estudiante);
    }

    public void mostrarLearningPathsDisponibles() {

        List<LearningPath> learningPathsDisponibles = lprsActual.getManejadorLP().getLearningPathsDisponibles();
        if (learningPathsDisponibles.isEmpty()) {
            System.out.println("No hay Learning Paths disponibles.");
            return;
        }
        for (int i = 0; i < learningPathsDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + learningPathsDisponibles.get(i).getTitulo());
        }
    }

    public String escogerLearningPath() throws NoLearningPathsException {
        List<LearningPath> learningPathsDisponibles = lprsActual.getManejadorLP().learningPathsDisponibles();
        mostrarLearningPathsDisponibles();
        if (learningPathsDisponibles.isEmpty()) {
           throw new NoLearningPathsException("No hay Learning Paths disponibles.");
        }
        int opcion = pedirInt("Seleccione un Learning Path: ");
        if (opcion < 1 || opcion > learningPathsDisponibles.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            return escogerLearningPath();
        }
        System.out.println("Esta es la informacion del Learning Path seleccionado: ");
        System.out.println("Titulo: " + learningPathsDisponibles.get(opcion - 1).getTitulo());
        System.out.println("Descripcion: " + learningPathsDisponibles.get(opcion - 1).getDescripcion());
        System.out.println("Nivel de dificultad: " + learningPathsDisponibles.get(opcion - 1).getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : learningPathsDisponibles.get(opcion - 1).getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + learningPathsDisponibles.get(opcion - 1).getDuracion());
        System.out.println("Rating: " + learningPathsDisponibles.get(opcion - 1).getRating());
        String respuesta = pedirString("Desea inscribirse en este Learning Path? (s/n)");
        if (respuesta.equals("s")) {
            return learningPathsDisponibles.get(opcion - 1).getID();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        LPRS lprs = new LPRS();
        try {
            lprs = PersistenciaGeneral.cargarDatos();
        } catch (Exception e) {
            System.out.println("Error al cargar los datos");
            e.printStackTrace();
        }
        ConsolaEstudiante consola = new ConsolaEstudiante(lprs);
        consola.getConsolaSesion().mostrarConsolaSesion();
        consola.getLectura().close();
        try {
            PersistenciaGeneral.guardarDatos(lprs);
        } catch (Exception e) {
            System.out.println("Error al guardar los datos");
            e.printStackTrace();
        }
    }

}
