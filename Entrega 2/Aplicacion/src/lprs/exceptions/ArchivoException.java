package lprs.exceptions;

public class ArchivoException extends Exception {

    public ArchivoException(String archivo) {
        super("Archivo no encontrado: " + archivo);
    }

}
