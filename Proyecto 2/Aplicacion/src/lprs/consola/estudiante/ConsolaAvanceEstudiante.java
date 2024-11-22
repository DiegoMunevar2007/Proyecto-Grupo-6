package lprs.consola.estudiante;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.realizable.*;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

import java.util.ArrayList;
import java.util.List;

public class ConsolaAvanceEstudiante {
    private ConsolaEstudiante consolaEstudiante;
    public ConsolaAvanceEstudiante(ConsolaEstudiante consolaEstudiante){
        this.consolaEstudiante=consolaEstudiante;
    }
    public void mostrarAvance() {
        System.out.println("Seleccione una opcion: ");
        String[] opciones = {"Ver avance en Learning Path", "Ver avance en actividad", "Volver"};
        boolean salir = false;
        while (!salir) {
            consolaEstudiante.mostrarOpciones(opciones.length, opciones);
            int opcion = consolaEstudiante.pedirInt("Seleccione una opcion: ");
            if (opcion == 1) {
                mostrarAvanceLP();
            } else if (opcion == 2) {
                mostrarAvanceActividad();
            } else if (opcion == 3) {
                salir = true;
            } else {
                System.out.println("Opcion invalida");
            }

        }
    }
    public void mostrarAvanceLP() {
        List<LearningPath> learningPaths = consolaEstudiante.getEstudiante().getLearningPathsInscritos();
        if (learningPaths.isEmpty()) {
            System.out.println("No hay Learning Paths inscritos.");
            return;
        }
        System.out.println("Seleccione un Learning Path: ");
        for (int i = 0; i < learningPaths.size(); i++) {
            System.out.println(i + 1 + ". " + consolaEstudiante.getEstudiante().getLearningPathsInscritos().get(i).getTitulo());
        }
        int numeroLP = consolaEstudiante.pedirInt("Ingrese el número del Learning Path que desea ver: ");
        LearningPath lp = consolaEstudiante.getEstudiante().getLearningPathsInscritos().get(numeroLP - 1);
        System.out.println("Learning Path: " + lp.getTitulo());
        Estudiante estudiante = consolaEstudiante.getEstudiante();
        Avance avance = estudiante.getAvance(lp.getID());
        System.out.println("Porcentaje de avance: " + avance.getFechaInicio() + "%");
        System.out.println("Fecha de inicio: " + avance.getFechaInicio());
        if (avance.getFechaFin() != null) {
            System.out.println("Fecha de fin: " + avance.getFechaFin());
        }
        System.out.println("Has completado las siguientes actividades: ");
        List<Actividad> actividadesCompletadas = avance.getActividadesCompletadasLista();
        for (Actividad actividadesCompletada : actividadesCompletadas) {
            System.out.println(actividadesCompletada.getTitulo());
        }
        System.out.println("Te faltan las siguientes actividades: ");
        List<Actividad> actividadesFaltantes = avance.getActividadesPendientes();
        for (Actividad actividadesFaltante : actividadesFaltantes) {
            System.out.println(actividadesFaltante.getTitulo());
        }
        double tasaExito = avance.getTasaExito();
        System.out.println("Tasa de éxito: " + tasaExito);
        double tasaFracaso = avance.getTasaFracaso();
        System.out.println("Tasa de fracaso: " + tasaFracaso);
    }
    public void mostrarAvanceActividad() {
        List<LearningPath> learningPaths = consolaEstudiante.getEstudiante().getLearningPathsInscritos();
        if (learningPaths.isEmpty()) {
            System.out.println("No hay Learning Paths inscritos.");
            return;
        }
        System.out.println("Seleccione un Learning Path: ");
        for (int i = 0; i < learningPaths.size(); i++) {
            System.out.println(i + 1 + ". " + consolaEstudiante.getEstudiante().getLearningPathsInscritos().get(i).getTitulo());
        }
        int numeroLP = consolaEstudiante.pedirInt("Ingrese el número del Learning Path que desea ver: ");
        LearningPath lp = consolaEstudiante.getEstudiante().getLearningPathsInscritos().get(numeroLP - 1);

        List<Actividad> actividades = consolaEstudiante.getEstudiante().getAvance(lp.getID()).getActividadesCompletadasLista();
        if (actividades.isEmpty()) {
            System.out.println("No hay actividades completadas.");
            return;
        }
        System.out.println("Seleccione una actividad: ");
        for (int i = 0; i < actividades.size(); i++) {
            System.out.println(i + 1 + ". " + actividades.get(i).getTitulo());
        }
        int numeroActividad = consolaEstudiante.pedirInt("Ingrese el número de la actividad que desea ver: ");
        Actividad actividad = actividades.get(numeroActividad - 1);
        System.out.println("Actividad: " + actividad.getTitulo());
        System.out.println("Descripción: " + actividad.getDescripcion());
        System.out.println("Objetivo: " + actividad.getObjetivo());
        ActividadRealizable actividadRealizable = consolaEstudiante.getEstudiante().getAvance(lp.getID()).getActividadesCompletadas().get(actividad);
        if (actividadRealizable instanceof QuizRealizable){
            mostrarAvanceQuiz((QuizRealizable) actividadRealizable);
            System.out.println("Calificación: " + ((QuizRealizable) actividadRealizable).getCalificacion());
        }
        else if (actividadRealizable instanceof TareaRealizable){
            mostrarAvanceTarea((TareaRealizable) actividadRealizable);
        }
        else if (actividadRealizable instanceof ExamenRealizable){
            mostrarAvanceExamen((ExamenRealizable) actividadRealizable);
            System.out.println("Estado: " + actividadRealizable.getEstado());
        }
        else if (actividadRealizable instanceof EncuestaRealizable){
            mostrarAvanceEncuesta((EncuestaRealizable) actividadRealizable);
        }
        else {
            System.out.println("No hay información adicional.");
        }
    }

    public void mostrarAvanceQuiz(QuizRealizable quizRealizable) {
        System.out.println("Preguntas: ");
        ArrayList<PreguntaCerradaRealizable> preguntas = quizRealizable.getPreguntas();
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("Pregunta " + i + 1);
            System.out.println(i + 1 + ". " + preguntas.get(i).getPreguntaBase().getEnunciado());
            System.out.println("Respuesta escogida: " + preguntas.get(i).getOpcionEscogida().getOpcion());
        }
    }
    public void mostrarAvanceTarea(TareaRealizable tareaRealizable) {
        System.out.println("Estado: " + tareaRealizable.getEstado());
    }
    public void mostrarAvanceExamen(ExamenRealizable examenRealizable) {
        System.out.println("Preguntas realizadas: ");
        ArrayList<PreguntaAbiertaRealizable> preguntas = examenRealizable.getPreguntas();
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("Pregunta " + i + 1);
            System.out.println(i + 1 + ". " + preguntas.get(i).getPreguntaBase().getEnunciado());
            System.out.println("Respuesta: " + preguntas.get(i).getRespuesta());
        }
    }
    public void mostrarAvanceEncuesta(EncuestaRealizable encuestaRealizable) {
        System.out.println("Preguntas realizadas: ");
        ArrayList<PreguntaAbiertaRealizable> preguntas = encuestaRealizable.getPreguntasRealizadas();
        for (int i = 0; i < preguntas.size(); i++) {
            System.out.println("Pregunta " + i + 1);
            System.out.println(i + 1 + ". " + preguntas.get(i).getPreguntaBase().getEnunciado());
            System.out.println("Respuesta: " + preguntas.get(i).getRespuesta());
        }
    }
}
