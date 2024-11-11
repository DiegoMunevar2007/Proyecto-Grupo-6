package lprs.consola.profesor.actividad;

import lprs.consola.profesor.ConsolaActividadProfesor;
import lprs.logica.contenido.Tarea;
import lprs.logica.learningPath.LearningPath;
import lprs.logica.contenido.Seccion;
import java.util.Scanner;

public class ConsolaTareaProfesor {

    private final ConsolaActividadProfesor consolaProfesor;

    public ConsolaTareaProfesor(ConsolaActividadProfesor consolaP) {
        this.consolaProfesor = consolaP;
    }

    public void crearTarea(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        Tarea tareaCreada = crearTareaBase(lp);
        consolaProfesor.aniadirPrerequisitos(lp, tareaCreada);
        if (deseaAnadirSecciones(lectura)) {
            anadirSeccionesATarea(tareaCreada, lectura);
        }
        System.out.println("Tarea creada con éxito.");
        consolaProfesor.mostrarConsolaActividad();
    }

    private Tarea crearTareaBase(LearningPath lp) {
        String titulo = consolaProfesor.pedirTitulo();
        String descripcion = consolaProfesor.pedirDescripcion();
        String objetivo = consolaProfesor.pedirObjetivo();
        int duracion = consolaProfesor.pedirDuracion();
        boolean obligatoria = consolaProfesor.pedirObligatoria();
        String fechaEntrega = consolaProfesor.pedirFechaEntrega();
        return lp.crearTarea(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega);
    }

    private boolean deseaAnadirSecciones(Scanner lectura) {
        System.out.println("¿Desea añadir secciones a la tarea? (s/n)");
        String respuesta = lectura.next();
        return respuesta.equalsIgnoreCase("s");
    }

    private void anadirSeccionesATarea(Tarea tareaCreada, Scanner lectura) {
        System.out.println("Ingrese el número de secciones que desea añadir: ");
        int numeroSecciones = lectura.nextInt();
        for (int i = 0; i < numeroSecciones; i++) {
            anadirSeccion(tareaCreada, lectura, i);
        }
    }

    private void anadirSeccion(Tarea tareaCreada, Scanner lectura, int index) {
        lectura.nextLine();
        String tituloSeccion = pedirTituloSeccion(lectura);
        String descripcionSeccion = pedirDescripcionSeccion(lectura);
        String tipoContenido = pedirTipoContenido(lectura);
        String contenido = pedirContenido(lectura);
        String ejemplo = pedirEjemplo(lectura);
        String explicacion = pedirExplicacion(lectura);
        String pista = pedirPista(lectura);
        String resultadoEsperado = pedirResultadoEsperado(lectura);
        tareaCreada.crearSeccion(index, tituloSeccion, tipoContenido, descripcionSeccion, contenido, ejemplo, explicacion, pista, resultadoEsperado);
        System.out.println("Sección creada con éxito.");
    }

    private String pedirTituloSeccion(Scanner lectura) {
        System.out.println("Ingrese el título de la sección: ");
        return lectura.nextLine();
    }

    private String pedirDescripcionSeccion(Scanner lectura) {
        System.out.println("Ingrese la descripción de la sección: ");
        return lectura.nextLine();
    }

    private String pedirTipoContenido(Scanner lectura) {
        System.out.println("Ingrese el tipo de contenido de la sección: ");
        return lectura.nextLine();
    }

    private String pedirContenido(Scanner lectura) {
        System.out.println("Ingrese el contenido de la sección: ");
        return lectura.nextLine();
    }

    private String pedirEjemplo(Scanner lectura) {
        System.out.println("¿Desea añadir un ejemplo? (s/n)");
        String respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el ejemplo: ");
            lectura.nextLine();
            return lectura.nextLine();
        }
        return null;
    }

    private String pedirExplicacion(Scanner lectura) {
        System.out.println("¿Desea añadir una explicación? (s/n)");
        String respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese la explicación: ");
            lectura.nextLine();
            return lectura.nextLine();
        }
        return null;
    }

    private String pedirPista(Scanner lectura) {
        System.out.println("¿Desea añadir una pista? (s/n)");
        String respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese la pista: ");
            lectura.nextLine();
            return lectura.nextLine();
        }
        return null;
    }

    private String pedirResultadoEsperado(Scanner lectura) {
        System.out.println("¿Desea añadir un resultado esperado? (s/n)");
        String respuesta = lectura.next();
        if (respuesta.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el resultado esperado: ");
            lectura.nextLine(); // consume the newline
            return lectura.nextLine();
        }
        return null;
    }

    public void modificarTarea(Tarea tarea) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese el número de la tarea que desea modificar: ");

        if (tarea == null) {
            System.out.println("Tarea no encontrada.");
            consolaProfesor.mostrarConsolaActividad();
            return;
        }
        String[] opciones = {"Título", "Descripción", "Objetivo", "Duración", "Obligatoria", "Fecha de entrega", "Secciones", "Salir"};
        System.out.println("¿Qué desea modificar?");
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            tarea.setTitulo(consolaProfesor.pedirTitulo());
        } else if (opcion == 2) {
            tarea.setDescripcion(consolaProfesor.pedirDescripcion());
        } else if (opcion == 3) {
            tarea.setObjetivo(consolaProfesor.pedirObjetivo());
        } else if (opcion == 4) {
            tarea.setDuracionEsperada(consolaProfesor.pedirDuracion());
        } else if (opcion == 5) {
            tarea.setObligatoria(consolaProfesor.pedirObligatoria());
        } else if (opcion == 6) {
            tarea.setFechaLimite(consolaProfesor.pedirFechaEntrega());
        } else if (opcion == 7) {
            System.out.println("¿Qué desea hacer?");
            String[] opcionesSecciones = {"Añadir sección", "Editar sección", "Eliminar sección", "Salir"};
            consolaProfesor.getConsolaProfesor().mostrarOpciones(opcionesSecciones.length, opcionesSecciones);
            int opcionSecciones = lectura.nextInt();
            lectura.nextLine();
            if (opcionSecciones == 1) {
                anadirSeccion(tarea, lectura, tarea.getSecciones().size());
            } else if (opcionSecciones == 2) {
                editarSeccion(tarea, lectura);
            } else if (opcionSecciones == 3) {
                eliminarSeccion(tarea, lectura);
            }
        }
        System.out.println("Tarea modificada con éxito.");
        consolaProfesor.mostrarConsolaActividad();
    }
    public void editarSeccion(Tarea tarea, Scanner lectura) {
        for (int i = 0; i < tarea.getSecciones().size(); i++) {
            System.out.println(i + 1 + ". " + tarea.getSecciones().get(i).getTitulo());
        }
        System.out.println("Ingrese el número de la sección que desea editar: ");
        int numeroSeccion = lectura.nextInt();
        lectura.nextLine();
        System.out.println("¿Qué desea editar?");
        String[] opcionesSeccion = {"Título", "Descripción", "Tipo de contenido", "Contenido", "Ejemplo", "Explicación", "Pista", "Resultado esperado"};
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opcionesSeccion.length, opcionesSeccion);
        int opcionSeccion = lectura.nextInt();
        lectura.nextLine();
        if (opcionSeccion == 1) {
            tarea.getSecciones().get(numeroSeccion - 1).setTitulo(pedirTituloSeccion(lectura));
        } else if (opcionSeccion == 2) {
            tarea.getSecciones().get(numeroSeccion - 1).setDescripcion(pedirDescripcionSeccion(lectura));
        } else if (opcionSeccion == 3) {
            tarea.getSecciones().get(numeroSeccion - 1).setTipo(pedirTipoContenido(lectura));
        } else if (opcionSeccion == 4) {
            tarea.getSecciones().get(numeroSeccion - 1).setContenido(pedirContenido(lectura));
        } else if (opcionSeccion == 5) {
            tarea.getSecciones().get(numeroSeccion - 1).setEjemplo(pedirEjemplo(lectura));
        } else if (opcionSeccion == 6) {
            tarea.getSecciones().get(numeroSeccion - 1).setExplicacion(pedirExplicacion(lectura));
        } else if (opcionSeccion == 7) {
            tarea.getSecciones().get(numeroSeccion - 1).setPista(pedirPista(lectura));
        } else if (opcionSeccion == 8) {
            tarea.getSecciones().get(numeroSeccion - 1).setResultadoEsperado(pedirResultadoEsperado(lectura));
        }
    }
    public void eliminarSeccion(Tarea tarea, Scanner lectura) {
        for (int i = 0; i < tarea.getSecciones().size(); i++) {
            System.out.println(i + 1 + ". " + tarea.getSecciones().get(i).getTitulo());
        }
        System.out.println("Ingrese el número de la sección que desea eliminar: ");
        int numeroSeccion = lectura.nextInt();
        Seccion seccion = tarea.getSecciones().get(numeroSeccion - 1);
        tarea.eliminarSeccion(seccion);
        System.out.println("Sección eliminada con éxito.");
    }
    public void verTarea(Tarea tarea) {
        System.out.println("Título: " + tarea.getTitulo());
        System.out.println("Descripción: " + tarea.getDescripcion());
        System.out.println("Objetivo: " + tarea.getObjetivo());
        System.out.println("Duración: " + tarea.getDuracionEsperada());
        System.out.println("Obligatoria: " + tarea.isObligatoria());
        System.out.println("Fecha de entrega: " + tarea.getFechaLimite());
        System.out.println("Secciones: ");
        for (int i = 0; i < tarea.getSecciones().size(); i++) {
            Seccion seccion = tarea.getSecciones().get(i);
            System.out.println(i + 1 + ". " + seccion.getTitulo());
            System.out.println("Descripción: " + seccion.getDescripcion());
            System.out.println("Tipo de contenido: " + seccion.getTipo());
            System.out.println("Contenido: " + seccion.getContenido());
            System.out.println("Ejemplo: " + seccion.getEjemplo());
            System.out.println("Explicación: " + seccion.getExplicacion());
            System.out.println("Pista: " + seccion.getPista());
            System.out.println("Resultado esperado: " + seccion.getResultadoEsperado());
        }
        consolaProfesor.mostrarConsolaActividad();
    }
}
