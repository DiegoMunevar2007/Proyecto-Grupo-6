package lprs.logica.contenido.pregunta;

import java.io.Serializable;

public class Opcion implements Serializable {
    private String opcion;
    private String explicacion;

    public Opcion(String opcion, String explicacion) {
        this.opcion = opcion;
        this.explicacion = explicacion;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public String getExplicacion() {
        return explicacion;
    }

}
