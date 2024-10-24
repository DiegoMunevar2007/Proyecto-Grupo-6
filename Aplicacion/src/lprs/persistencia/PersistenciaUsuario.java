package lprs.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import lprs.exceptions.ArchivoException;
import lprs.logica.cuentas.Usuario;
import lprs.manejador.ManejadorSesion;

public class PersistenciaUsuario implements Persistencia {

    public PersistenciaUsuario() {

    }

    public void guardarUsuario(ManejadorSesion manejadorS) throws IOException {
        Collection<Usuario> usuarios = manejadorS.getUsuariosLista();
        JSONObject jObject = new JSONObject();
        JSONArray jUsuarios = new JSONArray();
        for (Usuario usuario : usuarios) {
            JSONObject jUsuario = new JSONObject();
            jUsuario.put("usuario", usuario.getUsuario());
            jUsuario.put("contrasena", usuario.getContrasenia());
            if (usuario.getTipo().equals("Estudiante")) {
                jUsuario.put("tipo", 1);
            } else {
                jUsuario.put("tipo", 2);
            }
            jUsuarios.put(jUsuario);
        }
        jObject.put("Usuarios", jUsuarios);
        PrintWriter writer = new PrintWriter(direccionArchivo + "/usuarios.json");
        jObject.write(writer, 2, 0);
        writer.close();
    }

    public void guardarUsuario2(ManejadorSesion manejadorS) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(direccionArchivo + "/usuarios.dat"));
        ObjectOutput oos = new ObjectOutputStream(fos);
        oos.writeObject(manejadorS.getUsuarios());
        oos.close();
    }

    public void cargarUsuarios(ManejadorSesion manejadorS) throws Exception {
        String contenido = "";
        try {
            if (!Files.exists(Paths.get(direccionArchivo + "/usuarios.json"))) {
                throw new ArchivoException("Archivo no encontrado: usuarios.json");
            } else {
                contenido = new String(Files.readAllBytes(Paths.get(direccionArchivo + "/usuarios.json")));
            }
        } catch (ArchivoException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
            System.out.println("Creando archivo de usuarios vacio");
            PrintWriter writer = new PrintWriter(direccionArchivo + "/usuarios.json");
            writer.write("{\"Usuarios\":[]}");
            writer.close();
            contenido = new String(Files.readAllBytes(Paths.get(direccionArchivo + "/usuarios.json")));
        }
        try {
            JSONObject jObject = new JSONObject(contenido);
            JSONArray jUsuarios = jObject.getJSONArray("Usuarios");
            for (int i = 0; i < jUsuarios.length(); i++) {
                JSONObject jUsuario = jUsuarios.getJSONObject(i);
                String usuario = jUsuario.getString("usuario");
                String contrasena = jUsuario.getString("contrasena");
                int tipo = jUsuario.getInt("tipo");
                try {
                    manejadorS.crearUsuario(usuario, contrasena, tipo);
                } catch (Exception e) {
                    System.out.println("Error creando usuario: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error creando usuarios o leyendo JSON " + e.getMessage());
        }
    }

    public void cargarUsuarios2(ManejadorSesion manejadorS) throws Exception {
        try {
            if (!Files.exists(Paths.get(direccionArchivo + "/usuarios.dat"))) {
                throw new ArchivoException("Archivo no encontrado: usuarios.dat");
            } else {
                FileInputStream fis = new FileInputStream(new File(direccionArchivo + "/usuarios.dat"));
                ObjectInputStream ois = new ObjectInputStream(fis);
                manejadorS.setUsuarios((HashMap<String, Usuario>) ois.readObject());
                ois.close();
            }
        } catch (ArchivoException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
            System.out.println("Creando archivo de usuarios vacio");
            manejadorS.setUsuarios(new HashMap<String, Usuario>());
        }
    }

}
