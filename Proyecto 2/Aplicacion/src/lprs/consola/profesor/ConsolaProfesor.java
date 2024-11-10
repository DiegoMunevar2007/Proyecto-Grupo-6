package lprs.consola.profesor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import lprs.consola.ConsolaPrincipal;
import lprs.exceptions.ClonarLPException;
import lprs.exceptions.TipoUsuarioIncorrectoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Quiz;
import lprs.logica.contenido.QuizMultiple;
import lprs.logica.contenido.QuizVerdaderoFalso;
import lprs.logica.contenido.Tarea;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.logica.learningPath.LearningPath;
import lprs.persistencia.PersistenciaGeneral;
import lprs.principal.LPRS;

public class ConsolaProfesor extends ConsolaPrincipal {

    private Profesor profesor;
    private Scanner lectura;
    private ConsolaActividadProfesor consolaActividad;

    public ConsolaProfesor(LPRS lprsActual) {
        super(lprsActual);
        profesor = null;
        lectura = new Scanner(System.in);
    }

    public void mostrarConsolaInicial() {
        System.out.println("Bienvenido a Learning Paths Recommendation System");
        String[] opciones = { "Iniciar sesión", "Crear una cuenta", "Salir" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            iniciarSesion();
        } else if (opcion == 2) {
            crearCuenta();
        } else if (opcion == 3) {
            System.out.println("Hasta luego!");
            try {
                lprsActual.guardarDatos();
            } catch (Exception e) {
                System.out.println("Error al guardar los datos");
                e.printStackTrace();
            }
            lectura.close();
            return;
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            lectura.close();
            mostrarConsolaInicial();
        }
    }

    public void crearCuenta() {
        lectura.nextLine();
        System.out.println("Ingrese su nombre de usuario: ");
        String usuario = lectura.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasenia = lectura.nextLine();
        try {
            lprsActual.getManejadorSesion().crearUsuario(usuario, contrasenia, 2);
        } catch (Exception e) {
            System.out.println("Error al crear la cuenta");
            e.printStackTrace();
        }
        System.out.println("Cuenta creada con éxito.");
        mostrarConsolaInicial();
    }

    public void iniciarSesion() {
        lectura.nextLine();
        System.out.println("Ingrese su nombre de usuario: ");
        String ID = lectura.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasenia = lectura.nextLine();
        try {
            Usuario usuario = lprsActual.getManejadorSesion().iniciarSesion(ID, contrasenia);
            if (usuario == null) {
                mostrarConsolaInicial();
                return;
            }
            if (usuario.getTipo().equals("ESTUDIANTE")) {
                throw new TipoUsuarioIncorrectoException("ESTUDIANTE");
            } else {
                profesor = (Profesor) usuario;
                mostrarConsolaProfesor();
            }
        } catch (TipoUsuarioIncorrectoException e) {
            System.out.println(e.getMessage());
            mostrarConsolaInicial();
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión");
            e.printStackTrace();
        }

    }

    public void mostrarConsolaProfesor() {
        System.out.println("-------------------------------------");
        System.out.println("Bienvenido " + profesor.getUsuario());
        String[] opciones = { "Ver mis Learning Paths hechos", "Ver los learning Paths disponibles",
                "Crear un learning path", "Salir" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            mostrarLearningPathsHechos();
        } else if (opcion == 2) {
            mostrarLearningPathsDisponibles();
        } else if (opcion == 3) {
            crearLearningPath();
        } else if (opcion == 4) {
            System.out.println("Hasta luego!");
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaProfesor();
        }

    }

    public void crearLearningPath() {
        lectura.nextLine();
        System.out.println("Ingrese el titulo del Learning Path: ");
        String titulo = lectura.nextLine();

        System.out.println("Ingrese la descripcion del Learning Path: ");
        String descripcion = lectura.nextLine();

        System.out.println("Ingrese el nivel de dificultad del Learning Path: (Principiante, Intermedio, Avanzado)");
        String nivelDificultad = lectura.nextLine();
        if (!nivelDificultad.equals("Principiante") && !nivelDificultad.equals("Intermedio")
                && !nivelDificultad.equals("Avanzado")) {
            System.out.println("Nivel de dificultad no válido. Por favor, ingrese un nivel de dificultad válido.");
            crearLearningPath();
            return;
        }
        ArrayList<String> objetivos = new ArrayList<>();
        boolean terminado = false;
        while (!terminado) {
            System.out.println("Ingrese un objetivo: ");
            String objetivo = lectura.nextLine();
            objetivos.add(objetivo);

            System.out.println("¿Desea agregar otro objetivo? (s/n): ");
            String respuesta = lectura.nextLine();
            if (respuesta.equalsIgnoreCase("n")) {
                terminado = true;
            }
        }
        String ID = profesor.crearLearningPath(titulo, descripcion, nivelDificultad, objetivos);
        System.out.println("El learning path se ha creado exitosamente con el ID: " + ID);
        for (LearningPath lp : lprsActual.getManejadorLP().getLearningPathsDisponibles()) {
            System.out.println(lp.getTitulo());
        }
        mostrarConsolaProfesor();
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
        System.out.println("Seleccione un Learning Path o ingrese 0 para volver: ");
        int opcion = lectura.nextInt();
        if (opcion < 0 || opcion > learningPathsDisponibles.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            mostrarLearningPathsDisponibles();
            return;

        } else if (opcion == 0) {
            mostrarConsolaProfesor();
            return;
        }
        System.out.println("Esta es la informacion del Learning Path seleccionado: ");
        System.out.println("ID: " + learningPathsDisponibles.get(opcion - 1).getID());
        System.out.println("Titulo: " + learningPathsDisponibles.get(opcion - 1).getTitulo());
        System.out.println("Descripcion: " + learningPathsDisponibles.get(opcion - 1).getDescripcion());
        System.out.println("Nivel de dificultad: " + learningPathsDisponibles.get(opcion - 1).getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : learningPathsDisponibles.get(opcion - 1).getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + learningPathsDisponibles.get(opcion - 1).getDuracion());
        System.out.println("Rating: " + learningPathsDisponibles.get(opcion - 1).getRating());
        System.out.println(
                "Profesor creador: " + learningPathsDisponibles.get(opcion - 1).getProfesorCreador().getUsuario());
        System.out.println("¿Que desea hacer? ");
        String[] opciones = { "Clonar el Learning Path", "Volver" };
        mostrarOpciones(opciones.length, opciones);
        int opcion2 = lectura.nextInt();
        if (opcion2 == 1) {
            LearningPath lp = learningPathsDisponibles.get(opcion - 1);
            String ID = lp.getID();
            try {
                profesor.clonarLearningPath(ID);
                System.out.println("Learning Path clonado con éxito.");
                mostrarConsolaProfesor();
            } catch (ClonarLPException e) {
                System.out.println(e.getMessage());
                mostrarLearningPathsDisponibles();
            }
        } else if (opcion2 == 2) {
            mostrarConsolaProfesor();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarLearningPathsDisponibles();
        }
    }

    public void mostrarLearningPathsHechos() {

        Collection<LearningPath> learningPathsHechos = profesor.getLearningPathsCreados();
        if (learningPathsHechos.isEmpty()) {
            System.out.println("No tienes Learning Paths creados.");
            mostrarConsolaProfesor();
            return;
        }
        int indice = 1;
        for (LearningPath lp : learningPathsHechos) {
            System.out.println(indice + ". " + lp.getTitulo());
            indice++;
        }
        System.out.println("Ingrese el número del Learning Path que desea o ingrese 0 para salir ");
        int numero = lectura.nextInt();
        if (numero < 0 || numero > learningPathsHechos.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            mostrarLearningPathsHechos();
            return;
        }
        if (numero == 0) {
            mostrarConsolaProfesor();
            return;
        }
        LearningPath lp = (LearningPath) learningPathsHechos.toArray()[numero - 1];

        System.out.println("¿Que desea hacer?");
        String[] opciones = { "Ver informacion detallada del Learning Path", "Modificar un Learning Path",
                "Añadir/Modificar/Eliminar una actividad",
                "Eliminar un Learning Path", "Volver" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            mostrarLearningPathCompleto(lp);
            mostrarLearningPathsHechos();
        } else if (opcion == 2) {
            modificarLearningPath(lp);
        } else if (opcion == 3) {
            consolaActividad(lp);
        } else if (opcion == 4) {
            profesor.eliminarLearningPath(lp.getID());
            mostrarLearningPathsHechos();
        } else if (opcion == 5) {
            mostrarConsolaProfesor();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarLearningPathsHechos();
        }
    }

    public void consolaActividad(LearningPath lp) {

        System.out.println("¿Qué desea hacer?");
        String[] opciones = { "Crear una actividad", "Modificar una actividad", "Eliminar una actividad", "Volver" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            System.out.println("¿Qué tipo de actividad desea crear?");
            String[] opcionesActividad = { "Tarea", "Recurso Educativo" };
            mostrarOpciones(opcionesActividad.length, opcionesActividad);
            int opcionActividad = lectura.nextInt();
            if (opcionActividad == 1) {
                crearTarea(lp);
            } else if (opcionActividad == 2) {
                crearRecursoEducativo(lp);
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
                consolaActividad(lp);
            }
        } else if (opcion == 2) {
            System.out.println("¿Qué actividad desea modificar?");
            for (int i = 0; i < lp.getActividades().size(); i++) {
                System.out.println(i + 1 + ". " + lp.getActividades().get(i).getTitulo());
            }

            System.out.println("Ingrese el número de la actividad que desea modificar: ");
            int numero = lectura.nextInt();
            if (numero < 1 || numero > lp.getActividades().size()) {
                System.out.println("Opción no válida. Por favor, seleccione una actividad de la lista.");
                consolaActividad(lp);
                return;
            }
            if (lp.getActividades().get(numero - 1) instanceof Tarea) {
                System.out.println(numero + ". " + lp.getActividades().get(numero - 1).getTitulo());
            }
            System.out.println("¿Qué desea modificar?");
            String[] opcionesModificar = { "Título", "Descripción", "Objetivo", "Duración", "Obligatoriedad",
                    "Fecha de entrega" };
            mostrarOpciones(opcionesModificar.length, opcionesModificar);
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
                consolaActividad(lp);
            }
            System.out.println("Actividad modificada con éxito.");
            mostrarLearningPathsHechos();
        }

    }

    public void crearTarea(LearningPath lp) {

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
        mostrarConsolaProfesor();
    }

    public void crearRecursoEducativo(LearningPath lp) {

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
        mostrarOpciones(opciones.length, opciones);
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

        } else if (tipo.equalsIgnoreCase("vf")) {
            QuizVerdaderoFalso quiz = lp.crearQuizVerdaderoFalso(titulo, descripcion, objetivo, duracion, obligatoria,
                    fechaEntrega, calificacionMinima);
            aniadirPrerequisitos(lp, quiz);
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            crearQuiz(lp);
        }
        System.out.println("Quiz creado con éxito.");
        System.out.println("¿Desea añadir preguntas al quiz? (s/n)");
    }

    public void crearPregunta(Quiz quiz, String tipo) {
        System.out.println("Ingrese el enunciado de la pregunta: ");
        String enunciado = lectura.nextLine();
        if (tipo.equals("vf")) {
            System.out.println("¿Es verdadera o falsa? (v/f)");
            String respuesta = lectura.next();
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
                    crearPregunta(quiz, tipo);
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
                    crearPregunta(quiz, tipo);
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
                crearPregunta(quiz, tipo);
            }
        }

    }

    public void aniadirPrerequisitos(LearningPath lp, Actividad actividad) {
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

    public void modificarLearningPath(LearningPath lp) {
        System.out.println("¿Qué desea modificar?");
        String[] opciones = { "Título", "Descripción", "Nivel de dificultad", "Objetivos", "Volver" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        lectura.nextLine();
        if (opcion == 1) {
            System.out.println("Ingrese el nuevo título: ");
            String titulo = lectura.nextLine();
            String descripcion = lp.getDescripcion();
            String nivelDificultad = lp.getNivelDificultad();
            ArrayList<String> objetivos = lp.getObjetivos();
            profesor.modificarLearningPath(lp.getID(), titulo, descripcion, nivelDificultad, objetivos);
            mostrarLearningPathsHechos();
        } else if (opcion == 2) {
            System.out.println("Ingrese la nueva descripción: ");
            String descripcion = lectura.nextLine();
            String titulo = lp.getTitulo();
            String nivelDificultad = lp.getNivelDificultad();
            ArrayList<String> objetivos = lp.getObjetivos();
            profesor.modificarLearningPath(lp.getID(), titulo, descripcion, nivelDificultad, objetivos);
            mostrarLearningPathsHechos();
        } else if (opcion == 3) {
            System.out.println("Ingrese el nuevo nivel de dificultad: ");
            String nivelDificultad = lectura.nextLine();
            String titulo = lp.getTitulo();
            String descripcion = lp.getDescripcion();
            ArrayList<String> objetivos = lp.getObjetivos();
            profesor.modificarLearningPath(lp.getID(), titulo, descripcion, nivelDificultad, objetivos);
            mostrarLearningPathsHechos();
        } else if (opcion == 4) {
            ArrayList<String> objetivos = new ArrayList<>();
            boolean terminado = false;
            while (!terminado) {
                System.out.println("Ingrese un objetivo: ");
                String objetivo = lectura.nextLine();
                objetivos.add(objetivo);

                System.out.println("¿Desea agregar otro objetivo? (s/n): ");
                String respuesta = lectura.nextLine();
                if (respuesta.equalsIgnoreCase("n")) {
                    terminado = true;
                }
            }
            String titulo = lp.getTitulo();
            String descripcion = lp.getDescripcion();
            String nivelDificultad = lp.getNivelDificultad();
            profesor.modificarLearningPath(lp.getID(), titulo, descripcion, nivelDificultad, objetivos);
            mostrarLearningPathsHechos();
        } else if (opcion == 5) {
            mostrarLearningPathsHechos();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            modificarLearningPath(lp);
        }
    }

    public void mostrarLearningPathCompleto(LearningPath lp) {
        System.out.println("Titulo: " + lp.getTitulo());
        System.out.println("Descripcion: " + lp.getDescripcion());
        System.out.println("Nivel de dificultad: " + lp.getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : lp.getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + lp.getDuracion());
        System.out.println("Rating: " + lp.getRating());
    }

    public static void main(String[] args) {
        LPRS lprs = new LPRS();
        try {
            lprs = PersistenciaGeneral.cargarDatos();
        } catch (Exception e) {
            System.out.println("Error al cargar los datos");
            e.printStackTrace();
        }
        ConsolaProfesor consola = new ConsolaProfesor(lprs);
        consola.mostrarConsolaInicial();
        try {
            PersistenciaGeneral.guardarDatos(lprs);
        } catch (Exception e) {
            System.out.println("Error al guardar los datos");
            e.printStackTrace();
        }
        return;
    }
}
