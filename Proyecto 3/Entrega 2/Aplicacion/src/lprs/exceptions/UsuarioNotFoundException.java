package lprs.exceptions;

public class UsuarioNotFoundException extends Exception {
    String usuarioString;

    public UsuarioNotFoundException(String message, String usuario) {
        super(message);
        usuarioString = usuario;

    }

    public String getUsuario() {
        return usuarioString;
    }

}
