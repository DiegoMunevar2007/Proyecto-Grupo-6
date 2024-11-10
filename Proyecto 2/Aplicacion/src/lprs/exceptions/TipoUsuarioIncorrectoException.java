package lprs.exceptions;

public class TipoUsuarioIncorrectoException extends Exception {

    public TipoUsuarioIncorrectoException(String tipoUsuario) {
        super(crearMensaje(tipoUsuario));
    }

    public static String crearMensaje(String tipoUsuario) {
        return "El tipo de usuario " + tipoUsuario + " no es correcto.";
    }

}
