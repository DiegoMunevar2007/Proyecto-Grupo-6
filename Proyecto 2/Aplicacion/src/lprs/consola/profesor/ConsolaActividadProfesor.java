package lprs.consola.profesor;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.logica.contenido.*;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaActividadProfesor {

    private LPRS lprsActual;
    private ConsolaProfesor consolaProfesor;

    public ConsolaActividadProfesor(LPRS lprsActual, ConsolaProfesor consolaProfesor) {
        this.lprsActual = lprsActual;
        this.consolaProfesor = consolaProfesor;

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
        String[] opciones = { "Crear una actividad", "Modificar una actividad", "Eliminar una actividad", "Volver" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        opcion = lectura.nextInt();
        if (opcion == 1) {
            System.out.println("¿Qué tipo de actividad desea crear?");
            String[] opcionesActividad = { "Tarea", "Recurso Educativo", "Quiz", "Examen", "Encuesta" };
            consolaProfesor.mostrarOpciones(opcionesActividad.length, opcionesActividad);
            int opcionActividad = lectura.nextInt();
            lectura.nextLine();
            if (opcionActividad == 1) {
                crearTarea(lp);
            } else if (opcionActividad == 2) {
                crearRecursoEducativo(lp);
            } else if (opcionActividad == 3) {
                crearQuiz(lp);
            } else if (opcionActividad == 4) {
                crearExamen(lp);
            } else if (opcionActividad == 5) {

            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
                mostrarConsolaActividad();
            }
        } else if (opcion == 2) {
            modificarActividad(lp);
        } else if (opcion == 3) {

        } else if (opcion == 4) {
            consolaProfesor.mostrarConsolaProfesor();
        } else {
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
        if (lp.getActividades().get(numero - 1) instanceof Tarea) {
            System.out.println(numero + ". " + lp.getActividades().get(numero - 1).getTitulo());
        }
        System.out.println("¿Qué desea modificar?");
        String[] opcionesModificar = { "Título", "Descripción", "Objetivo", "Duración", "Obligatoriedad",
                "Fecha de entrega" };
        consolaProfesor.mostrarOpciones(opcionesModificar.length, opcionesModificar);
        int opcionModificar = lectura.nextInt();
        lectura.nextLine();
        if (opcionModificar == 1) {
            System.out.println("Ingrese el nuevo título: ");
            String titulo = lectura.nextLine();
            lp.getActividades().get(numero - 1).setTitulo(titulo);
        } else if (opcionModificar == 2) {
            System.out.println("Ingrese la nueva descripción: ");
            String descripcion = lectura.nextLine();
            lp.getActividades().get(numero - 1).setDescripcion(descripcion);
        } else if (opcionModificar == 3) {
            System.out.println("Ingrese el nuevo objetivo: ");
            String objetivo = lectura.nextLine();
            lp.getActividades().get(numero - 1).setObjetivo(objetivo);
        } else if (opcionModificar == 4) {
            System.out.println("Ingrese la nueva duración: ");
            int duracion = lectura.nextInt();
            lp.getActividades().get(numero - 1).setDuracionEsperada(duracion);
        } else if (opcionModificar == 5) {
            System.out.println("¿Es obligatoria? (s/n)");
            String respuesta = lectura.next();
            boolean obligatoria = false;
            if (respuesta.equalsIgnoreCase("s")) {
                obligatoria = true;
            }
            lp.getActividades().get(numero - 1).setObligatoria(obligatoria);
        } else if (opcionModificar == 6) {
            System.out.println("¿Tiene una fecha de entrega? (s/n)");
            String respuesta = lectura.next();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la fecha de entrega en formato DD/MM/YYYY: ");
                String fechaEntrega = lectura.nextLine();
                lp.getActividades().get(numero - 1).setFechaLimite(fechaEntrega);
            } else {
                lp.getActividades().get(numero - 1).setFechaLimite(null);
            }
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaActividad();
        }
        System.out.println("Actividad modificada con éxito.");
        mostrarConsolaActividad();
    }

    public void crearExamen(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el título del examen: ");
        String titulo = lectura.nextLine();
        System.out.println("Ingrese la descripción del examen: ");
        String descripcion = lectura.nextLine();
        System.out.println("Ingrese el objetivo del examen: ");
        String objetivo = lectura.nextLine();
        System.out.println("Ingrese la duración del examen: ");
        int duracion = lectura.nextInt();
        System.out.println("¿Es un examen obligatorio (s/n)?");
        String respuesta = lectura.next();
        boolean obligatoria = false;
        if (respuesta.equalsIgnoreCase("s")) {
            obligatoria = true;
        }
        System.out.println("¿Tiene una fecha de entrega? (s/n)");
        respuesta = lectura.next();
        String fechaEntrega = null;
        if (respuesta.equalsIgnoreCase("s")) {
            lectura.nextLine();
            System.out.println("Ingrese la fecha de entrega en formato DD/MM/YYYY: ");
            fechaEntrega = lectura.nextLine();
        }
        Examen examen = lp.crearExamen(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega);
        aniadirPrerequisitos(lp, examen);
        System.out.println("Ingrese el número de preguntas que desea añadir: ");
        int numeroPreguntas = lectura.nextInt();
        lectura.nextLine();
        for (int i = 0; i < numeroPreguntas; i++) {
            crearPreguntaAbiertaExamen(examen);
        }
        System.out.println("Examen creado con éxito.");
        mostrarConsolaActividad();
    }
    public void crearTarea(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        Tarea tareaCreada = null;
        lectura.nextLine();
        System.out.println("Ingrese el título de la tarea: ");
        String titulo = lectura.nextLine();
        System.out.println("Ingrese la descripción de la tarea: ");
        String descripcion = lectura.nextLine();
        System.out.println("Ingrese el objetivo de la tarea: ");
        String objetivo = lectura.nextLine();
        System.out.println("Ingrese la duración de la tarea: ");
        int duracion = lectura.nextInt();
        System.out.println("¿Es una tarea obligatoria? (s/n)");
        String respuesta = lectura.next();
        boolean obligatoria = false;
        if (respuesta.equalsIgnoreCase("s")) {
            obligatoria = true;
        }
        System.out.println("¿Tiene una fecha de entrega? (s/n)");
        respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            lectura.nextLine();
            System.out.println("Ingrese la fecha de entrega en formato DD/MM/YYYY: ");
            String fechaEntrega = lectura.nextLine();
            tareaCreada = lp.crearTarea(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega);
        } else {
            tareaCreada = lp.crearTarea(titulo, descripcion, objetivo, duracion, obligatoria, null);
        }
        System.out.println("¿Desea añadir secciones a la tarea? (s/n)");
        respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el número de secciones que desea añadir: ");
            int numeroSecciones = lectura.nextInt();

            for (int i = 0; i < numeroSecciones; i++) {
                lectura.nextLine();
                System.out.println("Ingrese el título de la sección: ");
                String tituloSeccion = lectura.nextLine();
                System.out.println("Ingrese la descripción de la sección: ");
                String descripcionSeccion = lectura.nextLine();
                System.out.println("Ingrese el tipo de contenido de la sección: ");
                String tipoContenido = lectura.nextLine();
                System.out.println("Ingrese la descripción del contenido de la sección: ");
                descripcionSeccion = lectura.nextLine();
                System.out.println("Ingrese el contenido de la sección: ");
                String contenido = lectura.nextLine();
                String ejemplo = null;
                String explicacion = null;
                String pista = null;
                String resultadoEsperado = null;
                System.out.println("¿Desea añadir un ejemplo? (s/n)");
                respuesta = lectura.next();
                if (respuesta.equalsIgnoreCase("s")) {
                    System.out.println("Ingrese el ejemplo: ");
                    ejemplo = lectura.nextLine();
                }
                System.out.println("¿Desea añadir una explicación? (s/n)");
                respuesta = lectura.next();
                if (respuesta.equalsIgnoreCase("s")) {
                    System.out.println("Ingrese la explicación: ");
                    explicacion = lectura.nextLine();
                }
                System.out.println("¿Desea añadir una pista? (s/n)");
                respuesta = lectura.next();
                if (respuesta.equalsIgnoreCase("s")) {
                    System.out.println("Ingrese la pista: ");
                    pista = lectura.nextLine();
                }
                System.out.println("¿Desea añadir un resultado esperado? (s/n)");
                respuesta = lectura.next();
                if (respuesta.equalsIgnoreCase("s")) {
                    System.out.println("Ingrese el resultado esperado: ");
                    resultadoEsperado = lectura.nextLine();
                }
                tareaCreada.crearSeccion(i, tituloSeccion, tipoContenido, descripcionSeccion, contenido,
                        ejemplo,
                        explicacion, pista, resultadoEsperado);
                System.out.println("Sección creada con éxito.");
            }
        }
        System.out.println("Tarea creada con éxito.");
        mostrarConsolaActividad();
    }

    public void crearRecursoEducativo(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el título del recurso educativo: ");
        String titulo = lectura.nextLine();
        System.out.println("Ingrese la descripción del recurso educativo: ");
        String descripcion = lectura.nextLine();
        System.out.println("Ingrese el objetivo del recurso educativo: ");
        String objetivo = lectura.nextLine();
        System.out.println("Ingrese la duración del recurso educativo: ");
        int duracion = lectura.nextInt();
        System.out.println("¿Es un recurso educativo obligatorio? (s/n)");
        String respuesta = lectura.next();
        boolean obligatoria = false;
        if (respuesta.equalsIgnoreCase("s")) {
            obligatoria = true;
        }
        System.out.println("¿Que tipo de recurso educativo es?");
        String[] opciones = { "Video", "Libro", "Sitio web", "PDF" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion < 1 || opcion > opciones.length) {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            crearRecursoEducativo(lp);
            return;
        }
        String tipo = opciones[opcion - 1];
        System.out.println("Ingrese el link del recurso educativo: ");
        String link = lectura.nextLine();

        System.out.println("¿Tiene una fecha de entrega? (s/n)");
        respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese la fecha de entrega en formato DD/MM/YYYY: ");
            String fechaEntrega = lectura.nextLine();
            lp.crearRecursoEducativo(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega, tipo, link);
        } else {
            lp.crearRecursoEducativo(titulo, descripcion, objetivo, duracion, obligatoria, null, tipo, link);
        }
        System.out.println("Recurso educativo creado con éxito.");
    }

    public void crearQuiz(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el título del quiz: ");
        String titulo = lectura.nextLine();
        System.out.println("Ingrese la descripción del quiz: ");
        String descripcion = lectura.nextLine();
        System.out.println("Ingrese el objetivo del quiz: ");
        String objetivo = lectura.nextLine();
        System.out.println("Ingrese la duración del quiz: ");
        int duracion = lectura.nextInt();
        System.out.println("¿Es un quiz obligatorio? (s/n)");
        String respuesta = lectura.next();
        boolean obligatoria = false;
        if (respuesta.equalsIgnoreCase("s")) {
            obligatoria = true;
        }
        System.out.println("¿Tiene una fecha de entrega? (s/n)");
        respuesta = lectura.next();
        String fechaEntrega = null;
        if (respuesta.equalsIgnoreCase("s")) {
            lectura.nextLine();
            System.out.println("Ingrese la fecha de entrega en formato DD/MM/YYYY: ");
            fechaEntrega = lectura.nextLine();
        }
        double calificacionMinima = 0;
        System.out.println("Ingrese la calificación mínima para aprobar el quiz: ");
        calificacionMinima = lectura.nextDouble();
        System.out.println("Es un quiz multiple o verdadero/falso? (m/vf)");
        String tipo = lectura.next();
        if (tipo.equalsIgnoreCase("m")) {
            QuizMultiple quiz = lp.crearQuizMultiple(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega,
                    calificacionMinima);
            aniadirPrerequisitos(lp, quiz);
            int numeroPreguntas = 0;
            System.out.println("Ingrese el número de preguntas que desea añadir: ");
            numeroPreguntas = lectura.nextInt();
            lectura.nextLine();
            for (int i = 0; i < numeroPreguntas; i++) {
                crearPreguntaCerrada(quiz, tipo);
            }
        } else if (tipo.equalsIgnoreCase("vf")) {
            QuizVerdaderoFalso quiz = lp.crearQuizVerdaderoFalso(titulo, descripcion, objetivo, duracion, obligatoria,
                    fechaEntrega, calificacionMinima);
            aniadirPrerequisitos(lp, quiz);
            int numeroPreguntas = 0;
            System.out.println("Ingrese el número de preguntas que desea añadir: ");
            numeroPreguntas = lectura.nextInt();
            lectura.nextLine();
            for (int i = 0; i < numeroPreguntas; i++) {
                crearPreguntaCerrada(quiz, tipo);
            }
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            crearQuiz(lp);
        }
        System.out.println("Quiz creado con éxito.");
        mostrarConsolaActividad();
    }

    public void crearPreguntaCerrada(Quiz quiz, String tipo) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el enunciado de la pregunta: ");
        String enunciado = lectura.nextLine();
        if (tipo.equals("vf")) {
            System.out.println("¿Es verdadera o falsa? (v/f)");
            String respuesta = lectura.next();
            lectura.nextLine();
            if (respuesta.equalsIgnoreCase("v")) {
                System.out.println("Justificación: ");
                String justificacion = lectura.nextLine();
                Opcion opcion = new Opcion("Verdadero", justificacion);
                Opcion opcion2 = new Opcion("Falso", justificacion);
                Opcion[] opciones = { opcion, opcion2 };
                try {
                    quiz.crearPreguntaCerrada(enunciado, opcion, opciones);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    crearPreguntaCerrada(quiz, tipo);
                }
            } else {
                System.out.println("Justificación: ");
                String justificacion = lectura.nextLine();
                Opcion opcion = new Opcion("Verdadero", justificacion);
                Opcion opcion2 = new Opcion("Falso", justificacion);
                Opcion[] opciones = { opcion, opcion2 };
                try {
                    quiz.crearPreguntaCerrada(enunciado, opcion2, opciones);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    crearPreguntaCerrada(quiz, tipo);
                }
            }
        } else if (tipo.equals("m")) {
            Opcion[] opciones = new Opcion[4];
            for (int i = 0; i < 4; i++) {
                System.out.println("Opción " + (i + 1) + ": ");
                System.out.println("Ingrese la opción: ");
                String opcion = lectura.nextLine();
                System.out.println("Ingrese la justificación: ");
                String justificacion = lectura.nextLine();
                opciones[i] = new Opcion(opcion, justificacion);
            }
            System.out.println("¿Cuál es la respuesta correcta? (1, 2, 3, 4)");
            int respuesta = lectura.nextInt();
            try {
                quiz.crearPreguntaCerrada(enunciado, opciones[respuesta - 1], opciones);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                crearPreguntaCerrada(quiz, tipo);
            }
        }

    }

    public void crearPreguntaAbiertaExamen (Examen actividad){
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el enunciado de la pregunta: ");
        String enunciado = lectura.nextLine();
        PreguntaAbierta pregunta = new PreguntaAbierta(enunciado);
        actividad.addPreguntaExamen(pregunta);
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
