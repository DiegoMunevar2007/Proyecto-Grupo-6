package lprs.logica.contenido;

import lprs.logica.cuentas.Usuario;

public class Resenia {
    private String ID;
    private String contenido;
    private float rating;
    private Usuario autor;

    public Resenia(String ID, String contenido, float rating, Usuario autor) {
        this.ID = ID;
        this.contenido = contenido;
        this.rating = rating;
        this.autor = autor;
    }

    public String getID() {
        return ID;
    }

    public String getContenido() {
        return contenido;
    }

    public float getRating() {
        return rating;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
