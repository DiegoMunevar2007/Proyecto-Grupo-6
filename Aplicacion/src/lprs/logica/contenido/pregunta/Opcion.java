package lprs.logica.contenido.pregunta;

public class Opcion {
    private String opcion; 
    private String explicacion; 

    public Opcion(String opcion, String explicacion) {
        this.opcion = opcion;
        this.explicacion = explicacion;
    }

    public String getOpcion() {
        return opcion;
    }

    public String getExplicacion() {
        return explicacion;
    }
    
}
