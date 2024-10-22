package lprs.logica.contenido;

import lprs.logica.cuentas.Usuario;

public class Resenia {
    private String ID;
    private String contenido;
    private double rating;
    private Usuario autor;

    public Resenia(String ID, String contenido, double rating, Usuario autor) {
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

    public double getRating() {
        return rating;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
