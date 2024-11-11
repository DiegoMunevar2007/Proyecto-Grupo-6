package lprs.consola.profesor;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.consola.profesor.actividad.ConsolaEncuestaProfesor;
import lprs.consola.profesor.actividad.ConsolaExamenProfesor;
import lprs.consola.profesor.actividad.ConsolaQuizProfesor;
import lprs.consola.profesor.actividad.ConsolaTareaProfesor;
import lprs.consola.profesor.actividad.ConsolaRecursoProfesor;
import lprs.logica.contenido.*;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaActividadProfesor {

    private LPRS lprsActual;
    private final ConsolaProfesor consolaProfesor;
    private final ConsolaExamenProfesor consolaExamen;
    private final ConsolaEncuestaProfesor consolaEncuesta;
    private ConsolaTareaProfesor consolaTarea;
    private final ConsolaQuizProfesor consolaQuiz;
    private ConsolaRecursoProfesor consolaRecursoEducativo;
    public ConsolaActividadProfesor(LPRS lprsActual, ConsolaProfesor consolaProfesor) {
        this.lprsActual = lprsActual;
        this.consolaProfesor = consolaProfesor;
        this.consolaExamen = new ConsolaExamenProfesor(this);
        this.consolaEncuesta = new ConsolaEncuestaProfesor(this);
        this.consolaTarea = new ConsolaTareaProfesor(this);
        this.consolaQuiz = new ConsolaQuizProfesor(this);
        this.consolaRecursoEducativo = new ConsolaRecursoProfesor(this);
    }

    public Scanner getLectura() {
        return consolaProfesor.getLectura();
    }
    public ConsolaProfesor getConsolaProfesor() {
        return consolaProfesor;
    }
    public void mostrarConsolaActividad() {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Seleccione un Learning Path: ");
        ArrayList<LearningPath> learningPaths = consolaProfesor.getProfesor().getLearningPathsCreadosLista();
        for (int i = 0; i < learningPaths.size(); i++) {
            System.out.println(i + 1 + ". " + learningPaths.get(i).getTitulo());
        }
        int opcion = lectura.nextInt();
        if (opcion < 1 || opcion > learningPaths.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            mostrarConsolaActividad();
            return;
        }
        LearningPath lp = learningPaths.get(opcion - 1);
        System.out.println("¿Qué desea hacer?");
        String[] opciones = { "Crear una actividad","Modificar una actividad", "Eliminar una actividad" , "Ver una actividad", "Volver" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        opcion = lectura.nextInt();
        if (opcion == 1) {
            System.out.println("¿Qué tipo de actividad desea crear?");
            String[] opcionesActividad = { "Tarea", "Recurso Educativo", "Quiz", "Examen", "Encuesta" };
            consolaProfesor.mostrarOpciones(opcionesActividad.length, opcionesActividad);
            int opcionActividad = lectura.nextInt();
            lectura.nextLine();
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
            int numero = lectura.nextInt();
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
                consolaProfesor.mostrarConsolaProfesor();
            }
            for (int i = 0; i < lp.getActividades().size(); i++) {
                System.out.println(i + 1 + ". " + lp.getActividades().get(i).getTitulo());
            }
            System.out.println("Seleccione una actividad para ver más detalles: ");
            int numero = lectura.nextInt();
            if (numero < 1 || numero > lp.getActividades().size()) {
                System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
                mostrarConsolaActividad();
                return;
            }
            Actividad actividad = lp.getActividades().get(numero - 1);
            if (actividad instanceof Quiz){
                consolaQuiz.verQuiz((Quiz) actividad);
            }
            else if (actividad instanceof Examen){
                consolaExamen.verExamen((Examen) actividad);
            }
            else if (actividad instanceof Encuesta){
                consolaEncuesta.verEncuesta((Encuesta) actividad);
            }
            else if (actividad instanceof Tarea){
                consolaTarea.verTarea((Tarea) actividad);
            }
            else if (actividad instanceof RecursoEducativo){
                consolaRecursoEducativo.verRecurso((RecursoEducativo) actividad);
            }
        } else if (opcion == 5){
            consolaProfesor.mostrarConsolaProfesor();
        }
        else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaActividad();
        }

    }

    public void modificarActividad(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("¿Qué actividad desea modificar?");
        for (int i = 0; i < lp.getActividades().size(); i++) {
            System.out.println(i + 1 + ". " + lp.getActividades().get(i).getTitulo());
        }

        System.out.println("Ingrese el número de la actividad que desea modificar: ");
        int numero = lectura.nextInt();
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
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el título de la actividad: ");
        return lectura.nextLine();
    }
    public String pedirDescripcion() {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese la descripción de la actividad: ");
        return lectura.nextLine();
    }
    public String pedirObjetivo() {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el objetivo de la actividad: ");
        return lectura.nextLine();
    }
    public int pedirDuracion() {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese la duración de la actividad: ");
        return lectura.nextInt();
    }
    public boolean pedirObligatoria() {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("¿Es una actividad obligatoria? (s/n)");
        String respuesta = lectura.next();
        boolean obligatoria = false;
        if (respuesta.equalsIgnoreCase("s")) {
            obligatoria = true;
        }
        return obligatoria;
    }
    public String pedirFechaEntrega() {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("¿Tiene una fecha de entrega? (s/n)");
        String respuesta = lectura.next();
        String fechaEntrega = null;
        if (respuesta.equalsIgnoreCase("s")) {
            lectura.nextLine();
            System.out.println("Ingrese la fecha de entrega en formato DD/MM/YYYY: ");
            fechaEntrega = lectura.nextLine();
        }
        return fechaEntrega;
    }

    public void aniadirPrerequisitos(LearningPath lp, Actividad actividad) {
        Scanner lectura = consolaProfesor.getLectura();
        ArrayList<Actividad> prerequisitos = new ArrayList<Actividad>();
        System.out.println("¿Desea añadir prerequisitos a la actividad? (s/n)");
        String respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            boolean termino = false;
            while (!termino && lp.getActividades().size() > prerequisitos.size()) {
                System.out.println("Seleccione una actividad de la lista de actividades disponibles: ");
                for (int i = 0; i < lp.getActividades().size(); i++) {
                    if (!prerequisitos.contains(lp.getActividades().get(i))) {
                        System.out.println(i + 1 + ". " + lp.getActividades().get(i).getTitulo());
                    }
                }
                int opcion = lectura.nextInt();
                if (opcion < 1 || opcion > lp.getActividades().size()) {
                    System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
                    continue;
                }
                prerequisitos.add(lp.getActividades().get(opcion - 1));
                System.out.println("¿Desea añadir otro prerequisito? (s/n)");
                respuesta = lectura.next();
                if (respuesta.equalsIgnoreCase("n")) {
                    termino = true;
                }
            }
        }
        actividad.setActividadesPrevias(prerequisitos);
    }
}
