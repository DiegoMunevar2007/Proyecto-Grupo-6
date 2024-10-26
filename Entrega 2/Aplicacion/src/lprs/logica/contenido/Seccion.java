package lprs.logica.contenido;

import java.io.Serializable;
import java.util.ArrayList;

public class Seccion implements Serializable{
	
	private int numero;
	private String titulo;
	private String tipo;
	private String descripcion;
	private String contenido;
	private String ejemplo;
	private String explicacion;
	private String pista;
	private String resultadoEsperado;
	public ArrayList<Tarea> tareaSeccion;
	
	public Seccion(int numero, String titulo, String tipo, String descripcion, String contenido, String ejemplo,
			String explicacion, String pista, String resultadoEsperado) {
		super();
		this.numero = numero;
		this.titulo = titulo;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.contenido = contenido;
		this.ejemplo = ejemplo;
		this.explicacion = explicacion;
		this.pista = pista;
		this.resultadoEsperado = resultadoEsperado;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getEjemplo() {
		return ejemplo;
	}
	public void setEjemplo(String ejemplo) {
		this.ejemplo = ejemplo;
	}
	public String getExplicacion() {
		return explicacion;
	}
	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}
	public String getPista() {
		return pista;
	}
	public void setPista(String pista) {
		this.pista = pista;
	}
	public String getResultadoEsperado() {
		return resultadoEsperado;
	}
	public void setResultadoEsperado(String resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}
	
	

}
