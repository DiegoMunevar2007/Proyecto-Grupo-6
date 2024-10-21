package lprs.logica.cuentas;

import lprs.principal.LPRS;

public abstract class Usuario {
	protected String usuario;
	protected String contrasenia;
	protected String tipo;
	protected LPRS lprsActual;
	
	public Usuario(String usuario, String contrasenia, LPRS lprsActual) {
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.lprsActual=lprsActual;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public String getTipo() {
		return tipo;
	}

	

}
