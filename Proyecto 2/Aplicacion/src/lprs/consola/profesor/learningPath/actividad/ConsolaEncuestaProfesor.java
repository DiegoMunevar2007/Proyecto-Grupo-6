package lprs.consola.profesor.learningPath.actividad;

import lprs.consola.profesor.learningPath.ConsolaActividadProfesor;
import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.learningPath.LearningPath;

import java.util.Scanner;

public class ConsolaEncuestaProfesor {

    private ConsolaActividadProfesor consolaProfesor;

    public ConsolaEncuestaProfesor(ConsolaActividadProfesor consolaP) {
        this.consolaProfesor = consolaP;
    }

    public void crearEncuesta(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        String titulo = consolaProfesor.pedirTitulo();
        String descripcion = consolaProfesor.pedirDescripcion();
        String objetivo = consolaProfesor.pedirObjetivo();
        int duracion = consolaProfesor.pedirDuracion();
        boolean obligatoria = consolaProfesor.pedirObligatoria();
        String fechaEntrega = consolaProfesor.pedirFechaEntrega();
        Encuesta encuesta = lp.crearEncuesta(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega);
        consolaProfesor.aniadirPrerequisitos(lp, encuesta);
        System.out.println("Ingrese el número de preguntas que desea añadir: ");
        int numeroPreguntas = lectura.nextInt();
        lectura.nextLine();
        for (int i = 0; i < numeroPreguntas; i++) {
            crearPreguntaAbiertaEncuesta(encuesta);
        }
        System.out.println("Encuesta creada con éxito.");
        consolaProfesor.mostrarConsolaActividad();
    }

    public void modificarEncuesta(Encuesta encuesta) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("¿Qué desea modificar?");
        String[] opciones = {"Título", "Descripción", "Objetivo", "Duración", "Obligatoria", "Fecha de entrega", "Preguntas", "Salir"};
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        lectura.nextLine();
        if (opcion == 1) {
            encuesta.setTitulo(consolaProfesor.pedirTitulo());
        } else if (opcion == 2) {
            encuesta.setDescripcion(consolaProfesor.pedirDescripcion());
        } else if (opcion == 3) {
            encuesta.setObjetivo(consolaProfesor.pedirObjetivo());
        } else if (opcion == 4) {
            encuesta.setDuracionEsperada(consolaProfesor.pedirDuracion());
        } else if (opcion == 5) {
            encuesta.setObligatoria(consolaProfesor.pedirObligatoria());
        } else if (opcion == 6) {
            encuesta.setFechaLimite(consolaProfesor.pedirFechaEntrega());
        } else if (opcion == 7) {
            System.out.println("¿Qué desea hacer?");
            String[] opcionesPreguntas = {"Añadir pregunta", "Editar pregunta", "Eliminar pregunta", "Salir"};
            consolaProfesor.getConsolaProfesor().mostrarOpciones(opcionesPreguntas.length, opcionesPreguntas);
            int opcionPreguntas = lectura.nextInt();
            lectura.nextLine();
            if (opcionPreguntas == 1) {
                crearPreguntaAbiertaEncuesta(encuesta);
            } else if (opcionPreguntas == 2) {
                editarPreguntaAbiertaEncuesta(encuesta);
            } else if (opcionPreguntas == 3) {
                eliminarPreguntaAbiertaEncuesta(encuesta);
            }
        }
        System.out.println("Encuesta modificada con éxito.");
        consolaProfesor.mostrarConsolaActividad();
    }

    public void crearPreguntaAbiertaEncuesta(Encuesta actividad) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el enunciado de la pregunta: ");
        String enunciado = lectura.nextLine();
        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado);
        actividad.addPreguntaEncuesta(pregunta);
    }

    public void editarPreguntaAbiertaEncuesta(Encuesta actividad) {
        Scanner lectura = consolaProfesor.getLectura();
        for (int i = 0; i < actividad.getPreguntasEncuesta().size(); i++) {
            System.out.println(i + 1 + ". " + actividad.getPreguntasEncuesta().get(i).getEnunciado());
        }
        System.out.println("Ingrese el número de la pregunta que desea editar: ");
        int numeroPregunta = lectura.nextInt();
        lectura.nextLine();
        System.out.println("Ingrese el nuevo enunciado de la pregunta: ");
        String enunciado = lectura.nextLine();
        actividad.getPreguntasEncuesta().get(numeroPregunta - 1).setEnunciado(enunciado);
        consolaProfesor.mostrarConsolaActividad();
    }

    public void eliminarPreguntaAbiertaEncuesta(Encuesta actividad) {
        Scanner lectura = consolaProfesor.getLectura();
        for (int i = 0; i < actividad.getPreguntasEncuesta().size(); i++) {
            System.out.println(i + 1 + ". " + actividad.getPreguntasEncuesta().get(i).getEnunciado());
        }
        System.out.println("Ingrese el número de la pregunta que desea eliminar: ");
        int numeroPregunta = lectura.nextInt();
        lectura.nextLine();
        actividad.removePreguntaEncuesta(actividad.getPreguntasEncuesta().get(numeroPregunta - 1));
        consolaProfesor.mostrarConsolaActividad();
    }
    public void verEncuesta(Encuesta encuesta) {
        System.out.println("Título: " + encuesta.getTitulo());
        System.out.println("Descripción: " + encuesta.getDescripcion());
        System.out.println("Objetivo: " + encuesta.getObjetivo());
        System.out.println("Duración: " + encuesta.getDuracionEsperada());
        System.out.println("Obligatoria: " + encuesta.isObligatoria());
        System.out.println("Fecha de entrega: " + encuesta.getFechaLimite());
        System.out.println("Preguntas: ");
        for (int i = 0; i < encuesta.getPreguntasEncuesta().size(); i++) {
            PreguntaAbierta pregunta = encuesta.getPreguntasEncuesta().get(i);
            System.out.println(i + 1 + ". " + pregunta.getEnunciado());
        }
        consolaProfesor.mostrarConsolaActividad();
    }
}