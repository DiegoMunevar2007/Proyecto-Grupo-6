package lprs.consola;

import java.util.List;

import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class ConsolaEstudiante extends ConsolaPrincipal {
    Estudiante estudiante;

    public ConsolaEstudiante(Estudiante estudiante) {
        super();
        this.estudiante = estudiante;
    }

    public void mostrarConsolaEstudiante() {
        System.out.println("Bienvenido + " + estudiante.getUsuario());
        String[] opciones = { "Ver mis Leaning Paths", "Ver mis avances", "Salir" };
        mostrarOpciones(3, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            mostrarLearningPaths();
        } else if (opcion == 2) {
        }
    }

    public void mostrarLearningPaths() {
        List<LearningPath> learningPathsEstudiante = estudiante.getLearningPathsInscritos();
        if (learningPathsEstudiante.isEmpty()) {
            System.out.println("No tienes Learning Paths inscritos.");
            return;
        }
        for (LearningPath lP : learningPathsEstudiante) {
            System.out.println(lP.getTitulo());
        }
    }
}
