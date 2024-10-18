package lprs.persistencia;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;

import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

public class PersistenciaUsuario {

    private static String direccionArchivo = System.getProperty("user.dir") + "/datos";

    public static void guardarUsuario() throws IOException {
        Collection<Usuario> usuarios = LPRS.getUsuarios();
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

    public static void cargarUsuarios() throws Exception {
        String contenido;
        try {
            if (!Files.exists(Paths.get(direccionArchivo + "/usuarios.json"))) {
                throw new Exception("Archivo no encontrado: usuarios.json");
            }

            contenido = new String(Files.readAllBytes(Paths.get(direccionArchivo + "/usuarios.json")));
        } catch (Exception e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
            throw e;
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
                    LPRS.crearUsuario(usuario, contrasena, tipo);
                } catch (Exception e) {
                    System.out.println("Error creando usuario: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Error creando usuarios o leyendo JSON " + e.getMessage());
        }
    }

}
