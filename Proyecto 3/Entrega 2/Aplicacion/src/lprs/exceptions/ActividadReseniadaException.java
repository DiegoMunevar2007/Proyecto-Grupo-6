package lprs.exceptions;

public class ActividadReseniadaException extends Exception {
    public ActividadReseniadaException() {
        super("La actividad ya ha sido resenada");
    }
}
