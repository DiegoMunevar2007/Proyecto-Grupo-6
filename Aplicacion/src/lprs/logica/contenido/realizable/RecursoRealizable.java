package lprs.logica.contenido.realizable;

import lprs.exceptions.EstadoException;
import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.cuentas.Estudiante;

public class RecursoRealizable extends ActividadRealizable{

	public RecursoRealizable(RecursoEducativo actividadBase, Estudiante estudiante) {
		super(actividadBase, estudiante);
		this.estado="No completado";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void realizarActividad() {
		// TODO Auto-generated method stub
		
		System.out.println("Titulo del recurso: "+ actividadBase.getTitulo());
		
		
	}

	@Override
	public void guardarActividad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enviarActividad() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEstado(String estado) throws EstadoException {
		try {
			if (estado.equals("No completado") || estado.equals("Completado")) {
				this.estado= estado;
			}
			else {
				throw new EstadoException(this,estado);
			}
		} catch (EstadoException e) {
			e.printStackTrace();
		}
		
	}

}
