package lprs.consola.profesor.actividad;

import lprs.consola.profesor.ConsolaActividadProfesor;
import lprs.logica.contenido.Examen;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.learningPath.LearningPath;

import java.util.Scanner;

public class ConsolaExamenProfesor{

    private final ConsolaActividadProfesor consolaProfesor;
    public ConsolaExamenProfesor( ConsolaActividadProfesor consolaP) {
        this.consolaProfesor = consolaP;
    }

    public void crearExamen(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        String titulo = consolaProfesor.pedirTitulo();
        String descripcion = consolaProfesor.pedirDescripcion();
        String objetivo = consolaProfesor.pedirObjetivo();
        int duracion = consolaProfesor.pedirDuracion();
        boolean obligatoria = consolaProfesor.pedirObligatoria();
        String fechaEntrega = consolaProfesor.pedirFechaEntrega();
        Examen examen = lp.crearExamen(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega);
        consolaProfesor.aniadirPrerequisitos(lp, examen);
        System.out.println("Ingrese el número de preguntas que desea añadir: ");
        int numeroPreguntas = lectura.nextInt();
        lectura.nextLine();
        for (int i = 0; i < numeroPreguntas; i++) {
            crearPreguntaAbiertaExamen(examen);
        }
        System.out.println("Examen creado con éxito.");
        consolaProfesor.mostrarConsolaActividad();
    }
    public void modificarExamen(Examen examen) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("¿Qué desea modificar?");
        String[] opciones = {"Título", "Descripción", "Objetivo", "Duración", "Obligatoria", "Fecha de entrega", "Preguntas", "Salir"};
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        lectura.nextLine();
        if (opcion == 1) {
            examen.setTitulo(consolaProfesor.pedirTitulo());
        } else if (opcion == 2) {
            examen.setDescripcion(consolaProfesor.pedirDescripcion());
        } else if (opcion == 3) {
            examen.setObjetivo(consolaProfesor.pedirObjetivo());
        } else if (opcion == 4) {
            examen.setDuracionEsperada(consolaProfesor.pedirDuracion());
        } else if (opcion == 5) {
            examen.setObligatoria(consolaProfesor.pedirObligatoria());
        } else if (opcion == 6) {
            examen.setFechaLimite(consolaProfesor.pedirFechaEntrega());
        } else if (opcion == 7) {
            System.out.println("¿Qué desea hacer?");
            String[] opcionesPreguntas = {"Añadir pregunta", "Editar pregunta", "Eliminar pregunta", "Salir"};
            consolaProfesor.getConsolaProfesor().mostrarOpciones(opcionesPreguntas.length, opcionesPreguntas);
            int opcionPreguntas = lectura.nextInt();
            lectura.nextLine();
            if (opcionPreguntas == 1) {
                crearPreguntaAbiertaExamen(examen);
            } else if (opcionPreguntas == 2) {
                editarPreguntaAbiertaExamen(examen);
            } else if (opcionPreguntas == 3) {
                eliminarPreguntaAbiertaExamen(examen);
            }
        }
        System.out.println("Examen modificado con éxito.");
        consolaProfesor.mostrarConsolaActividad();
    }
    public void crearPreguntaAbiertaExamen (Examen actividad){
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el enunciado de la pregunta: ");
        String enunciado = lectura.nextLine();
        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado);
        actividad.addPreguntaExamen(pregunta);
    }
    public void editarPreguntaAbiertaExamen(Examen actividad) {
        Scanner lectura = consolaProfesor.getLectura();
        for (int i = 0; i < actividad.getPreguntasExamen().size(); i++) {
            System.out.println(i + 1 + ". " + actividad.getPreguntasExamen().get(i).getEnunciado());
        }
        System.out.println("Ingrese el número de la pregunta que desea editar: ");
        int numeroPregunta = lectura.nextInt();
        lectura.nextLine();
        System.out.println("Ingrese el nuevo enunciado de la pregunta: ");
        String enunciado = lectura.nextLine();
        actividad.getPreguntasExamen().get(numeroPregunta - 1).setEnunciado(enunciado);
        consolaProfesor.mostrarConsolaActividad();
    }
    public void eliminarPreguntaAbiertaExamen(Examen actividad) {
        Scanner lectura = consolaProfesor.getLectura();
        for (int i = 0; i < actividad.getPreguntasExamen().size(); i++) {
            System.out.println(i + 1 + ". " + actividad.getPreguntasExamen().get(i).getEnunciado());
        }
        System.out.println("Ingrese el número de la pregunta que desea eliminar: ");
        int numeroPregunta = lectura.nextInt();
        lectura.nextLine();
        actividad.removePreguntaExamen(actividad.getPreguntasExamen().get(numeroPregunta - 1));
        consolaProfesor.mostrarConsolaActividad();
    }

    public void verExamen(Examen examen) {
        System.out.println("Título: " + examen.getTitulo());
        System.out.println("Descripción: " + examen.getDescripcion());
        System.out.println("Objetivo: " + examen.getObjetivo());
        System.out.println("Duración: " + examen.getDuracionEsperada());
        System.out.println("Obligatoria: " + examen.isObligatoria());
        System.out.println("Fecha de entrega: " + examen.getFechaLimite());
        System.out.println("Preguntas: ");
        for (int i = 0; i < examen.getPreguntasExamen().size(); i++) {
            PreguntaAbierta pregunta = examen.getPreguntasExamen().get(i);
            System.out.println(i + 1 + ". " + pregunta.getEnunciado());
        }
        consolaProfesor.mostrarConsolaActividad();
    }

}
