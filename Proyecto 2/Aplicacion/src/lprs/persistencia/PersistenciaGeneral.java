package lprs.persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import lprs.principal.LPRS;

public class PersistenciaGeneral implements Persistencia {

    public static void guardarDatos(LPRS lprs) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File(direccionArchivo + "/Persistencia.bin")));
        oos.writeObject(lprs);
        oos.close();
    }

    public static LPRS cargarDatos() throws Exception {
        LPRS lprs = null;
        if (Files.exists(Paths.get(direccionArchivo + "/Persistencia.bin"))) {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(new File(direccionArchivo + "/Persistencia.bin")));
            lprs = (LPRS) ois.readObject();
            ois.close();
        } else {
            lprs = new LPRS();
        }
        return lprs;
    }
}
