package lprs.consola;

import java.util.ArrayList;
import java.util.Collection;

import lprs.logica.contenido.Tarea;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaProfesor extends ConsolaPrincipal {
    Profesor profesor;

    public ConsolaProfesor(LPRS lprsActual, Profesor usuarioEncontrado) {
        super(lprsActual);
        profesor = usuarioEncontrado;
    }

    public void mostrarConsolaProfesor() {
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
            try {
                lprsActual.guardarDatos();
            } catch (Exception e) {
                System.out.println("Error al guardar los datos");
            }
            return;
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
        mostrarConsolaProfesor();
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
        System.out.println("Ingrese el número del Learning Path que desea ");
        int numero = lectura.nextInt();
        if (numero < 1 || numero > learningPathsHechos.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            mostrarLearningPathsHechos();
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

    public void modificarTarea(Tarea tarea) {
        System.out.println("¿Desea modificar la tarea o alguna de sus secciones? (t/s/n)");
        String respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("t")) {
            System.out.println("¿Desea modificar el título? (s/n)");
            respuesta = lectura.next();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo título: ");
                String titulo = lectura.nextLine();
                tarea.setTitulo(titulo);
            }
            System.out.println("¿Desea modificar la descripción? (s/n)");
            respuesta = lectura.next();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la nueva descripción: ");
                String descripcion = lectura.nextLine();
                tarea.setDescripcion(descripcion);
            }
            System.out.println("¿Desea modificar el objetivo? (s/n)");
            respuesta = lectura.next();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo objetivo: ");
                String objetivo = lectura.nextLine();
                tarea.setObjetivo(objetivo);
            }
            System.out.println("¿Desea modificar la duración? (s/n)");
            respuesta = lectura.next();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la nueva duración: ");
                int duracion = lectura.nextInt();
                tarea.setDuracionEsperada(duracion);
            }
            System.out.println("¿Desea modificar la obligatoriedad? (s/n)");
            respuesta = lectura.next();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("¿Es obligatoria? (s/n)");
                respuesta = lectura.next();
                boolean obligatoria = false;
                if (respuesta.equalsIgnoreCase("s")) {
                    obligatoria = true;
                }
                tarea.setObligatoria(obligatoria);
            }
            System.out.println("¿Desea modificar la fecha de entrega? (s/n)");
            respuesta = lectura.next();
            if (respuesta.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la nueva fecha de entrega en formato DD/MM/YYYY: ");
                String fechaEntrega = lectura.nextLine();
                tarea.setFechaLimite(fechaEntrega);
            }
            System.out.println("Tarea modificada con éxito.");
            mostrarLearningPathsHechos();
        } else if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("¿Qué sección desea modificar?");
            for (int i = 0; i < tarea.getSecciones().size(); i++) {
                System.out.println(i + 1 + ". " + tarea.getSecciones().get(i).getTitulo());
            }
            System.out.println("Ingrese el número de la sección que desea modificar: ");
            int numero = lectura.nextInt();
            if (numero < 1 || numero > tarea.getSecciones().size()) {
                System.out.println("Opción no válida. Por favor, seleccione una sección de la lista.");
                modificarTarea(tarea);
                return;
            }
            System.out.println("¿Qué desea modificar?");
            String[] opcionesModificar = { "Título", "Descripción", "Tipo", "Contenido", "Ejemplo", "Explicación",
                    "Pista", "Resultado esperado" };
            mostrarOpciones(opcionesModificar.length, opcionesModificar);
            int opcionModificar = lectura.nextInt();
            if (opcionModificar == 1) {
                System.out.println("Ingrese el nuevo título: ");
                String titulo = lectura.nextLine();
                tarea.getSecciones().get(numero - 1).setTitulo(titulo);
            } else if (opcionModificar == 2) {
                System.out.println("Ingrese la nueva descripción: ");
                String descripcion = lectura.nextLine();
                tarea.getSecciones().get(numero - 1).setDescripcion(descripcion);
            } else if (opcionModificar == 3) {
                System.out.println("Ingrese el nuevo Tipo: ");
                String objetivo = lectura.nextLine();
                tarea.getSecciones().get(numero - 1).setTipo(objetivo);
            } else if (opcionModificar == 4) {
                System.out.println("Ingrese el nuevo contenido: ");
                String contenido = lectura.nextLine();
                tarea.getSecciones().get(numero - 1).setContenido(contenido);
            } else if (opcionModificar == 5) {
                System.out.println("Ingrese el nuevo ejemplo: ");
                String ejemplo = lectura.nextLine();
                tarea.getSecciones().get(numero - 1).setEjemplo(ejemplo);
            } else if (opcionModificar == 6) {
                System.out.println("Ingrese la nueva explicación: ");
                String explicacion = lectura.nextLine();
                tarea.getSecciones().get(numero - 1).setExplicacion(explicacion);
            } else if (opcionModificar == 7) {
                System.out.println("Ingrese la nueva pista: ");
                String pista = lectura.nextLine();
                tarea.getSecciones().get(numero - 1).setPista(pista);
            } else if (opcionModificar == 8) {
                System.out.println("Ingrese el nuevo resultado esperado: ");
                String resultadoEsperado = lectura.nextLine();
                tarea.getSecciones().get(numero - 1).setResultadoEsperado(resultadoEsperado);
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
                modificarTarea(tarea);
            }
        } else if (respuesta.equalsIgnoreCase("n")) {
            mostrarLearningPathsHechos();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            modificarTarea(tarea);
        }

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

    public void modificarLearningPath(LearningPath lp) {
        System.out.println("¿Desea modificar el título? (s/n)");
        String respuesta = lectura.next();
        lectura.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo título: ");
            String titulo = lectura.nextLine();
            lp.setTitulo(titulo);
        }
        System.out.println("¿Desea modificar la descripción? (s/n)");
        respuesta = lectura.next();
        lectura.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese la nueva descripción: ");
            String descripcion = lectura.nextLine();
            lp.setDescripcion(descripcion);
        }
        System.out.println("¿Desea modificar el nivel de dificultad? (s/n)");
        respuesta = lectura.next();
        lectura.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo nivel de dificultad: ");
            String nivelDificultad = lectura.next();
            lp.setNivelDificultad(nivelDificultad);
        }
        System.out.println("¿Desea modificar los objetivos? (s/n)");
        respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            lectura.nextLine();
            ArrayList<String> objetivos = new ArrayList<>();
            boolean terminado = false;
            while (!terminado) {
                System.out.println("Ingrese un objetivo: ");
                String objetivo = lectura.nextLine();
                objetivos.add(objetivo);

                System.out.println("¿Desea agregar otro objetivo? (s/n): ");
                respuesta = lectura.next();
                lectura.nextLine();
                if (respuesta.equalsIgnoreCase("n")) {
                    terminado = true;
                }
            }
            lp.setObjetivos(objetivos);
        }

        System.out.println("Learning Path modificado con éxito.");
        mostrarLearningPathsHechos();
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
}
