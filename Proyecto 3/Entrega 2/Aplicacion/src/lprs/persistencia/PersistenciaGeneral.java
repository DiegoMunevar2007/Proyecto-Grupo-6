package lprs.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import lprs.principal.LPRS;

public class PersistenciaGeneral implements Persistencia {

    public static void guardarDatos(LPRS lprs, String archivo) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File(direccionArchivo + archivo)));
        oos.writeObject(lprs);
        oos.close();
    }

    public static LPRS cargarDatos(String archivo) throws Exception {
        LPRS lprs = null;
        if (Files.exists(Paths.get(direccionArchivo + archivo))) {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File(direccionArchivo + archivo)));
            lprs = (LPRS) ois.readObject();
            ois.close();
        } else {
            lprs = new LPRS();
        }
        return lprs;
    }
}
