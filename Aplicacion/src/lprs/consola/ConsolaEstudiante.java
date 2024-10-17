package lprs.consola;

import java.util.Scanner;

import lprs.logica.cuentas.Estudiante;

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
    }
}
