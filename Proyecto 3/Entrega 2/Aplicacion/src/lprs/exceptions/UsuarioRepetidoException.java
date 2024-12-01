package lprs.exceptions;

public class UsuarioRepetidoException extends Exception {

    public UsuarioRepetidoException(String usuario) {
        super(crearMensaje(usuario));
    }

    public static String crearMensaje(String usuario) {
        return "El usuario " + usuario + " ya existe.";
    }
}
