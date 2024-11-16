package lprs.consola.profesor.learningPath.actividad;

import lprs.consola.profesor.learningPath.ConsolaActividadProfesor;
import lprs.logica.contenido.Quiz;
import lprs.logica.contenido.QuizMultiple;
import lprs.logica.contenido.QuizVerdaderoFalso;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.learningPath.LearningPath;

import java.util.Scanner;

public class ConsolaQuizProfesor {
    private final ConsolaActividadProfesor consolaProfesor;
    public ConsolaQuizProfesor( ConsolaActividadProfesor consolaP) {
        this.consolaProfesor = consolaP;
    }
    public void crearQuiz(LearningPath lp) {
        String titulo = consolaProfesor.pedirTitulo();
        String descripcion = consolaProfesor.pedirDescripcion();
        String objetivo = consolaProfesor.pedirObjetivo();
        int duracion = consolaProfesor.pedirDuracion();
        boolean obligatoria = consolaProfesor.pedirObligatoria();
        String fechaEntrega = consolaProfesor.pedirFechaEntrega();
        double calificacionMinima = 0;
        calificacionMinima = consolaProfesor.getConsolaProfesor().pedirDouble("Ingrese la calificación mínima en % para aprobar el quiz: ");
        if (calificacionMinima < 0 || calificacionMinima > 100) {
            System.out.println("La calificación mínima debe estar entre 0 y 100.");
            crearQuiz(lp);
            return;
        }
        String tipo = consolaProfesor.getConsolaProfesor().pedirString("¿Qué tipo de quiz desea crear? (m/vf)");
        Quiz quiz = null;
        if (tipo.equalsIgnoreCase("m")) {
            quiz = lp.crearQuizMultiple(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega, calificacionMinima);
        } else if (tipo.equalsIgnoreCase("vf")) {
            quiz = lp.crearQuizVerdaderoFalso(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega, calificacionMinima);
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            crearQuiz(lp);
            return;
        }
        consolaProfesor.aniadirPrerequisitos(lp, quiz);
        int numeroPreguntas = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de preguntas que desea añadir: ");
        for (int i = 0; i < numeroPreguntas; i++) {
            crearPreguntaCerrada(quiz);
        }
        consolaProfesor.aniadirActividadesSeguimiento(lp, quiz);
        System.out.println("Quiz creado con éxito.");
    }

    public void crearPreguntaCerrada(Quiz quiz) {
        String enunciado = consolaProfesor.getConsolaProfesor().pedirString("Ingrese el enunciado de la pregunta: ");
        if (quiz instanceof QuizVerdaderoFalso) {
            String respuesta = consolaProfesor.getConsolaProfesor().pedirString("¿Cuál es la respuesta correcta? (v/f)");
            if (respuesta.equalsIgnoreCase("v")) {
                System.out.println("Justificación: ");
                String justificacion = consolaProfesor.getConsolaProfesor().pedirString("Ingrese la justificación: ");
                Opcion opcion = new Opcion("Verdadero", justificacion);
                Opcion opcion2 = new Opcion("Falso", justificacion);
                Opcion[] opciones = { opcion, opcion2 };
                try {
                    quiz.crearPreguntaCerrada(enunciado, opcion, opciones);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    crearPreguntaCerrada(quiz);
                }
            } else {
                System.out.println("Justificación: ");
                String justificacion = consolaProfesor.getConsolaProfesor().pedirString("Ingrese la justificación: ");
                Opcion opcion = new Opcion("Verdadero", justificacion);
                Opcion opcion2 = new Opcion("Falso", justificacion);
                Opcion[] opciones = { opcion, opcion2 };
                try {
                    quiz.crearPreguntaCerrada(enunciado, opcion2, opciones);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    crearPreguntaCerrada(quiz);
                }
            }
        } else if (quiz instanceof QuizMultiple) {
            Opcion[] opciones = new Opcion[4];
            for (int i = 0; i < 4; i++) {
                System.out.println("Opción " + (i + 1) + ": ");
                System.out.println("Ingrese la opción: ");
                String opcion = consolaProfesor.getConsolaProfesor().pedirString("Ingrese la opción: ");
                System.out.println("Ingrese la justificación: ");
                String justificacion = consolaProfesor.getConsolaProfesor().pedirString("Ingrese la justificación: ");
                opciones[i] = new Opcion(opcion, justificacion);
            }
            int respuesta = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de la respuesta correcta: (1, 2, 3, 4) ");
            try {
                quiz.crearPreguntaCerrada(enunciado, opciones[respuesta - 1], opciones);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                crearPreguntaCerrada(quiz);
            }
        }

    }

    public void modificarQuiz(Quiz quiz){
        String[] opciones = {"Título", "Descripción", "Objetivo", "Duración", "Obligatoria", "Fecha de entrega", "Calificación mínima", "Preguntas", "Salir"};
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opciones.length, opciones);
        int opcion = consolaProfesor.getConsolaProfesor().pedirInt("Seleccione una opción: ");
        if (opcion == 1) {
            quiz.setTitulo(consolaProfesor.pedirTitulo());
        } else if (opcion == 2) {
            quiz.setDescripcion(consolaProfesor.pedirDescripcion());
        } else if (opcion == 3) {
            quiz.setObjetivo(consolaProfesor.pedirObjetivo());
        } else if (opcion == 4) {
            quiz.setDuracionEsperada(consolaProfesor.pedirDuracion());
        } else if (opcion == 5) {
            quiz.setObligatoria(consolaProfesor.pedirObligatoria());
        } else if (opcion == 6) {
            quiz.setFechaLimite(consolaProfesor.pedirFechaEntrega());
        } else if (opcion == 7) {
            double calificacionMinima = consolaProfesor.getConsolaProfesor().pedirDouble("Ingrese la calificación mínima en % para aprobar el quiz: ");
            quiz.setCalificacionMinima(calificacionMinima);
        } else if (opcion == 8) {
            String[] opcionesPreguntas = {"Añadir pregunta", "Editar pregunta", "Eliminar pregunta", "Salir"};
            consolaProfesor.getConsolaProfesor().mostrarOpciones(opcionesPreguntas.length, opcionesPreguntas);
            int opcionPreguntas = consolaProfesor.getConsolaProfesor().pedirInt("Seleccione una opción: ");
            if (opcionPreguntas == 1) {
                crearPreguntaCerrada(quiz);
            } else if (opcionPreguntas == 2) {
                editarPreguntaCerrada(quiz);
            } else if (opcionPreguntas == 3) {
                eliminarPreguntaCerrada(quiz);
            }
        }
    }
    public void editarPreguntaCerrada(Quiz quiz) {
        for (int i = 0; i < quiz.getPreguntasQuiz().size(); i++) {
            System.out.println(i + 1 + ". " + quiz.getPreguntasQuiz().get(i).getEnunciado());
        }
        int numeroPregunta = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de la pregunta que desea editar: ");
        PreguntaCerrada pregunta = quiz.getPreguntasQuiz().get(numeroPregunta - 1);
        System.out.println("¿Qué desea editar?");
        System.out.println("1. Enunciado");
        System.out.println("2. Respuesta correcta");
        System.out.println("3. Opciones");
        int opcion = consolaProfesor.getConsolaProfesor().pedirInt("Seleccione una opción: ");
        if (opcion == 1) {
            String enunciado = consolaProfesor.getConsolaProfesor().pedirString("Ingrese el nuevo enunciado: ");
            quiz.getPreguntasQuiz().get(numeroPregunta - 1).setEnunciado(enunciado);
        } else if (opcion == 2) {
            mostrarOpcionesPregunta(pregunta);
            int opcionCorrecta = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de la respuesta correcta: ");
            pregunta.setCorrecta(pregunta.getOpciones()[opcionCorrecta - 1]);
        } else if (opcion == 3) {
            mostrarOpcionesPregunta(pregunta);
            int numeroOpcion = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de la opción que desea editar: ");
            String opcionNueva = consolaProfesor.getConsolaProfesor().pedirString("Ingrese la nueva opción: ");
            String justificacion = consolaProfesor.getConsolaProfesor().pedirString("Ingrese la justificación: ");
            Opcion opcionN = new Opcion(opcionNueva, justificacion);
            pregunta.getOpciones()[numeroOpcion - 1] = opcionN;
        }
    }
    public void eliminarPreguntaCerrada(Quiz quiz) {
        for (int i = 0; i < quiz.getPreguntasQuiz().size(); i++) {
            System.out.println(i + 1 + ". " + quiz.getPreguntasQuiz().get(i).getEnunciado());
        }
        int numeroPregunta = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de la pregunta que desea eliminar: ");
        quiz.removePreguntaQuiz(quiz.getPreguntasQuiz().get(numeroPregunta - 1));
    }

    private void mostrarOpcionesPregunta(PreguntaCerrada pregunta){
        for (int i = 0; i < pregunta.getOpciones().length; i++) {
            System.out.println(i + 1 + ". " + pregunta.getOpciones()[i].getOpcion());
        }
    }

    public void verQuiz(Quiz quiz){
        System.out.println("Título: " + quiz.getTitulo());
        System.out.println("Descripción: " + quiz.getDescripcion());
        System.out.println("Objetivo: " + quiz.getObjetivo());
        System.out.println("Duración: " + quiz.getDuracionEsperada());
        System.out.println("Obligatoria: " + quiz.isObligatoria());
        System.out.println("Fecha de entrega: " + quiz.getFechaLimite());
        System.out.println("Calificación mínima: " + quiz.getCalificacionMinima());
        System.out.println("Preguntas: ");
        for (int i = 0; i < quiz.getPreguntasQuiz().size(); i++) {
            PreguntaCerrada pregunta = quiz.getPreguntasQuiz().get(i);
            System.out.println(i + 1 + ". " + pregunta.getEnunciado());
            for (int j = 0; j < pregunta.getOpciones().length; j++) {
                System.out.println("Opción " + (j + 1) + ": " + pregunta.getOpciones()[j].getOpcion());
            }
            System.out.println("Respuesta correcta: " + pregunta.getCorrecta().getOpcion());
        }
    }

}
