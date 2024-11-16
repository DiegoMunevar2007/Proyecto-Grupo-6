package lprs.consola.profesor.learningPath;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.consola.profesor.learningPath.actividad.ConsolaEncuestaProfesor;
import lprs.consola.profesor.learningPath.actividad.ConsolaExamenProfesor;
import lprs.consola.profesor.learningPath.actividad.ConsolaQuizProfesor;
import lprs.consola.profesor.learningPath.actividad.ConsolaTareaProfesor;
import lprs.consola.profesor.learningPath.actividad.ConsolaRecursoProfesor;
import lprs.logica.contenido.*;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaActividadProfesor {

    private LPRS lprsActual;
    private final ConsolaProfesorLP consolaProfesorLP;
    private final ConsolaExamenProfesor consolaExamen;
    private final ConsolaEncuestaProfesor consolaEncuesta;
    private ConsolaTareaProfesor consolaTarea;
    private final ConsolaQuizProfesor consolaQuiz;
    private ConsolaRecursoProfesor consolaRecursoEducativo;
    public ConsolaActividadProfesor(LPRS lprsActual, ConsolaProfesorLP consolaProfesorLP) {
        this.lprsActual = lprsActual;
        this.consolaProfesorLP = consolaProfesorLP;
        this.consolaExamen = new ConsolaExamenProfesor(this);
        this.consolaEncuesta = new ConsolaEncuestaProfesor(this);
        this.consolaTarea = new ConsolaTareaProfesor(this);
        this.consolaQuiz = new ConsolaQuizProfesor(this);
        this.consolaRecursoEducativo = new ConsolaRecursoProfesor(this);
    }

    public Scanner getLectura() {
        return consolaProfesorLP.getLectura();
    }
    public ConsolaProfesorLP getConsolaProfesor() {
        return consolaProfesorLP;
    }
    public void mostrarConsolaActividad() {
        ArrayList<LearningPath> learningPaths = consolaProfesorLP.getProfesor().getLearningPathsCreadosLista();
        if (learningPaths.isEmpty()) {
            System.out.println("No hay Learning Paths creados.");
            return;
        }
        for (int i = 0; i < learningPaths.size(); i++) {
            System.out.println(i + 1 + ". " + learningPaths.get(i).getTitulo());
        }
        int opcion = consolaProfesorLP.pedirInt("Seleccione un Learning Path: ");
        if (opcion < 1 || opcion > learningPaths.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            mostrarConsolaActividad();
            return;
        }
        LearningPath lp = learningPaths.get(opcion - 1);
        String[] opciones = { "Crear una actividad","Modificar una actividad", "Eliminar una actividad" , "Ver una actividad", "Volver" };
        boolean salir = false;
        while (!salir) {
            consolaProfesorLP.mostrarOpciones(opciones.length, opciones);
            opcion = consolaProfesorLP.pedirInt("¿Qué desea hacer?");
            if (opcion == 1) {
                String[] opcionesActividad = {"Tarea", "Recurso Educativo", "Quiz", "Examen", "Encuesta"};
                consolaProfesorLP.mostrarOpciones(opcionesActividad.length, opcionesActividad);
                int opcionActividad = consolaProfesorLP.pedirInt("¿Qué tipo de actividad desea crear?");
                if (opcionActividad == 1) {
                    consolaTarea.crearTarea(lp);
                } else if (opcionActividad == 2) {
                    consolaRecursoEducativo.crearRecursoEducativo(lp);
                } else if (opcionActividad == 3) {
                    consolaQuiz.crearQuiz(lp);
                } else if (opcionActividad == 4) {
                    consolaExamen.crearExamen(lp);
                } else if (opcionActividad == 5) {
                    consolaEncuesta.crearEncuesta(lp);
                } else {
                    System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
                    mostrarConsolaActividad();
                }
            } else if (opcion == 2) {
                modificarActividad(lp);
            } else if (opcion == 3) {
                System.out.println("¿Qué actividad desea eliminar?");
                for (int i = 0; i < lp.getActividades().size(); i++) {
                    System.out.println(i + 1 + ". " + lp.getActividades().get(i).getTitulo());
                }
                int numero = consolaProfesorLP.pedirInt("Seleccione una actividad para eliminar: ");
                if (numero < 1 || numero > lp.getActividades().size()) {
                    System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
                    mostrarConsolaActividad();
                    return;
                }
                lp.eliminarActividad(lp.getActividades().get(numero - 1));
                System.out.println("Actividad eliminada con éxito.");
                mostrarConsolaActividad();
            } else if (opcion == 4) {
                if (lp.getActividades().isEmpty()) {
                    System.out.println("No hay actividades en este Learning Path.");
                    mostrarConsolaActividad();
                }
                for (int i = 0; i < lp.getActividades().size(); i++) {
                    System.out.println(i + 1 + ". " + lp.getActividades().get(i).getTitulo());
                }
                int numero = consolaProfesorLP.pedirInt("Seleccione una actividad para ver más detalles: ");
                if (numero < 1 || numero > lp.getActividades().size()) {
                    System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
                    mostrarConsolaActividad();
                    return;
                }
                Actividad actividad = lp.getActividades().get(numero - 1);
                if (actividad instanceof Quiz) {
                    consolaQuiz.verQuiz((Quiz) actividad);
                } else if (actividad instanceof Examen) {
                    consolaExamen.verExamen((Examen) actividad);
                } else if (actividad instanceof Encuesta) {
                    consolaEncuesta.verEncuesta((Encuesta) actividad);
                } else if (actividad instanceof Tarea) {
                    consolaTarea.verTarea((Tarea) actividad);
                } else if (actividad instanceof RecursoEducativo) {
                    consolaRecursoEducativo.verRecurso((RecursoEducativo) actividad);
                }
            } else if (opcion == 5) {
                salir=true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            }
        }

    }

    public void modificarActividad(LearningPath lp) {
        System.out.println("¿Qué actividad desea modificar?");
        for (int i = 0; i < lp.getActividades().size(); i++) {
            System.out.println(i + 1 + ". " + lp.getActividades().get(i).getTitulo());
        }

        int numero = consolaProfesorLP.pedirInt("Seleccione una actividad para modificar: ");
        if (numero < 1 || numero > lp.getActividades().size()) {
            System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
            mostrarConsolaActividad();
            return;
        }
        if (lp.getActividades().get(numero-1) instanceof Examen){
            consolaExamen.modificarExamen((Examen) lp.getActividades().get(numero-1));
        } else if (lp.getActividades().get(numero-1) instanceof Encuesta){
            consolaEncuesta.modificarEncuesta((Encuesta) lp.getActividades().get(numero-1));
        } else if (lp.getActividades().get(numero-1) instanceof Quiz){
            consolaQuiz.modificarQuiz((Quiz) lp.getActividades().get(numero-1));
        } else if (lp.getActividades().get(numero-1) instanceof RecursoEducativo){
            consolaRecursoEducativo.modificarRecursoEducativo((RecursoEducativo) lp.getActividades().get(numero-1));
        } else if (lp.getActividades().get(numero-1) instanceof Tarea){
            consolaTarea.modificarTarea((Tarea) lp.getActividades().get(numero-1));
        }

        System.out.println("Actividad modificada con éxito.");
        mostrarConsolaActividad();
    }
    public String pedirTitulo() {
    return consolaProfesorLP.pedirString("Ingrese el título de la actividad: ");
}

public String pedirDescripcion() {
    return consolaProfesorLP.pedirString("Ingrese la descripción de la actividad: ");
}

public String pedirObjetivo() {
    return consolaProfesorLP.pedirString("Ingrese el objetivo de la actividad: ");
}

public int pedirDuracion() {
    int duracion = consolaProfesorLP.pedirInt("Ingrese la duración esperada de la actividad en minutos: ");
    if (duracion < 0) {
        System.out.println("La duración no puede ser negativa. Por favor, ingrese un valor válido.");
        return pedirDuracion();
    }
    return duracion;
}

public boolean pedirObligatoria() {
    String respuesta = consolaProfesorLP.pedirString("¿Es una actividad obligatoria? (s/n)");
    return respuesta.equalsIgnoreCase("s");
}

public String pedirFechaEntrega() {
    String respuesta = consolaProfesorLP.pedirString("¿Tiene una fecha de entrega? (s/n)");
    if (respuesta.equalsIgnoreCase("s")) {
        return consolaProfesorLP.pedirString("Ingrese la fecha de entrega en formato DD/MM/YYYY: ");
    }
    return null;
}

    public void aniadirPrerequisitos(LearningPath lp, Actividad actividad) {
        ArrayList<Actividad> prerequisitos = new ArrayList<Actividad>();
        String respuesta = consolaProfesorLP.pedirString("¿Desea añadir Prerrequisitos? (s/n)");
        if (respuesta.equalsIgnoreCase("s")) {
            boolean termino = false;
            while (!termino) {
                ArrayList<Actividad> actividadesDisponibles = new ArrayList<Actividad>();
                for (int i = 0; i < lp.getActividades().size(); i++) {
                    if (!prerequisitos.contains(lp.getActividades().get(i)) && !lp.getActividades().get(i).equals(actividad) && !actividad.getActividadesSeguimiento().contains(lp.getActividades().get(i))) {
                        actividadesDisponibles.add(lp.getActividades().get(i));
                    }
                }
                if (actividadesDisponibles.isEmpty()) {
                    System.out.println("No hay actividades disponibles para añadir como prerrequisitos.");
                    break;
                }
                for (int i = 0; i < actividadesDisponibles.size(); i++) {
                    System.out.println(i + 1 + ". " + actividadesDisponibles.get(i).getTitulo());
                }
                int opcion = consolaProfesorLP.pedirInt("Seleccione una actividad de la lista: ");
                if (opcion < 1 || opcion > actividadesDisponibles.size()) {
                    System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
                    continue;
                }
                prerequisitos.add(actividadesDisponibles.get(opcion - 1));
                respuesta = consolaProfesorLP.pedirString("¿Desea añadir otro prerrequisito? (s/n)");
                if (respuesta.equalsIgnoreCase("n")) {
                    termino = true;
                }
            }
        }
actividad.setActividadesPrevias(prerequisitos);
    }
    public void aniadirActividadesSeguimiento(LearningPath lp, Actividad actividad) {
        ArrayList<Actividad> actividadesSeleccionadas = new ArrayList<Actividad>();
        System.out.println("¿Desea añadir actividades de seguimiento a la actividad? (s/n)");
        String respuesta = consolaProfesorLP.pedirString("¿Desea añadir actividades de seguimiento a la actividad? (s/n)");
        if (respuesta.equalsIgnoreCase("s")) {
            boolean termino = false;
            while (!termino) {
                ArrayList<Actividad> actividadesSeguimiento = new ArrayList<Actividad>();
                for (int i = 0; i < lp.getActividades().size(); i++) {
                    if ( !lp.getActividades().get(i).equals(actividad) && !actividad.getActividadesPrevias().contains(lp.getActividades().get(i))) {
                        actividadesSeguimiento.add(lp.getActividades().get(i));
                    }
                }
                if (actividadesSeguimiento.isEmpty()) {
                    System.out.println("No hay actividades disponibles para añadir como seguimiento.");
                    break;
                }
                for (int i = 0; i < actividadesSeguimiento.size(); i++) {
                    System.out.println(i + 1 + ". " + actividadesSeguimiento.get(i).getTitulo());
                }
                int opcion = consolaProfesorLP.pedirInt("Seleccione una actividad de la lista: ");
                if (opcion < 1 || opcion > lp.getActividades().size()) {
                    System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
                    continue;
                }
                actividadesSeleccionadas.add(actividadesSeguimiento.get(opcion - 1));
                respuesta = consolaProfesorLP.pedirString("¿Desea añadir otra actividad de seguimiento? (s/n)");
                if (respuesta.equalsIgnoreCase("n")) {
                    termino = true;
                }
            }
        }
        actividad.setActividadesSeguimiento(actividadesSeleccionadas);
    }
}
