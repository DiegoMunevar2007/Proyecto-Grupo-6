package lprs.persistencia;

import java.io.IOException;

import lprs.principal.LPRS;

public class CentralPersistencia {
    PersistenciaLP persistenciaLP;
    PersistenciaUsuario persistenciaUsuario;
    LPRS lprsActual;

    public CentralPersistencia(LPRS lprsActual) {
        this.lprsActual = lprsActual;
        persistenciaLP = new PersistenciaLP();
        persistenciaUsuario = new PersistenciaUsuario();
    }

    public void guardarDatos() {
        try {
            persistenciaLP.guardarLP(lprsActual.getManejadorSesion(), lprsActual.getManejadorLP());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            persistenciaUsuario.guardarUsuario(lprsActual.getManejadorSesion());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
