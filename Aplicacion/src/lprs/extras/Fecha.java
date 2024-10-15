package lprs.extras;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    /**
     * Constructor para crear un objeto Fecha.
     *
     * @param dia  el día de la fecha
     * @param mes  el mes de la fecha
     * @param anio el año de la fecha
     */
    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    /**
     * Obtiene el día de la fecha.
     *
     * @return el día de la fecha
     */
    public int getDia() {
        return dia;
    }

    /**
     * Obtiene el mes de la fecha.
     *
     * @return el mes de la fecha
     */
    public int getMes() {
        return mes;
    }

    /**
     * Obtiene el año de la fecha.
     *
     * @return el año de la fecha
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Establece el día de la fecha.
     *
     * @param dia el día de la fecha
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Establece el mes de la fecha.
     *
     * @param mes el mes de la fecha
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Establece el año de la fecha.
     *
     * @param anio el año de la fecha
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }
}
