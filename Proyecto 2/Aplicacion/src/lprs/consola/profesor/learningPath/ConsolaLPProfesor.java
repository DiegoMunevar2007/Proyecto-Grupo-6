package lprs.consola.profesor.learningPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import lprs.exceptions.ClonarLPException;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaLPProfesor {

    private LPRS lprsActual;
    private ConsolaProfesor consolaProfesor;

    public ConsolaLPProfesor(LPRS lprsActual, ConsolaProfesor consolaProfesor) {
        this.lprsActual = lprsActual;
        this.consolaProfesor = consolaProfesor;
    }

    public void mostrarConsolaLP() {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("-------------------------------------");
        String[] opciones = { "Ver mis Learning Paths hechos", "Ver los learning Paths disponibles",
                "Crear un learning path", "Volver" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            mostrarLearningPathsHechos();
        } else if (opcion == 2) {
            mostrarLearningPathsDisponibles();
        } else if (opcion == 3) {
            crearLearningPath();
        } else if (opcion == 4) {
            System.out.println("Hasta luego!");
            consolaProfesor.mostrarConsolaProfesor();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaLP();
        }

    }

    public void crearLearningPath() {
        Scanner lectura = consolaProfesor.getLectura();
        Profesor profesor = consolaProfesor.getProfesor();
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
        mostrarConsolaLP();
    }

    public void mostrarLearningPathsDisponibles() {

        Scanner lectura = consolaProfesor.getLectura();
        Profesor profesor = consolaProfesor.getProfesor();
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
            mostrarConsolaLP();
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
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        int opcion2 = lectura.nextInt();
        if (opcion2 == 1) {
            LearningPath lp = learningPathsDisponibles.get(opcion - 1);
            String ID = lp.getID();
            try {
                profesor.clonarLearningPath(ID);
                System.out.println("Learning Path clonado con éxito.");
                mostrarConsolaLP();
            } catch (ClonarLPException e) {
                System.out.println(e.getMessage());
                mostrarLearningPathsDisponibles();
            }
        } else if (opcion2 == 2) {
            mostrarConsolaLP();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarLearningPathsDisponibles();
        }
    }

    public void mostrarLearningPathsHechos() {
        Scanner lectura = consolaProfesor.getLectura();
        Profesor profesor = consolaProfesor.getProfesor();
        Collection<LearningPath> learningPathsHechos = profesor.getLearningPathsCreados();
        if (learningPathsHechos.isEmpty()) {
            System.out.println("No tienes Learning Paths creados.");
            mostrarConsolaLP();
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
            mostrarConsolaLP();
            return;
        }
        LearningPath lp = (LearningPath) learningPathsHechos.toArray()[numero - 1];

        System.out.println("¿Que desea hacer?");
        String[] opciones = { "Ver informacion detallada del Learning Path", "Modificar un Learning Path",
                "Eliminar un Learning Path", "Volver" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            mostrarLearningPathCompleto(lp);
            mostrarLearningPathsHechos();
        } else if (opcion == 2) {
            modificarLearningPath(lp);
        } else if (opcion == 3) {
            profesor.eliminarLearningPath(lp.getID());
            mostrarLearningPathsHechos();
        } else if (opcion == 4) {
            mostrarConsolaLP();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarLearningPathsHechos();
        }
    }

    public void modificarLearningPath(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        Profesor profesor = consolaProfesor.getProfesor();
        System.out.println("¿Qué desea modificar?");
        String[] opciones = { "Título", "Descripción", "Nivel de dificultad", "Objetivos", "Volver" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
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

}
