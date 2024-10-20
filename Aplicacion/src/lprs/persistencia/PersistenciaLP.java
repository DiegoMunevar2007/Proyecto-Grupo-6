package lprs.persistencia;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import lprs.exceptions.ArchivoException;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.logica.learningPath.LearningPath;

public class PersistenciaLP implements Persistencia {

	public static void guardarLP() throws IOException {
		// Se obtienen todos los learning paths creados en formato ArrayList
		ArrayList<LearningPath> lPS = LearningPath.getLearningPaths();
		// Se crea un objeto nuevo de json
		JSONObject jObject = new JSONObject();
		// Se crea un arreglo de objetos para almacenar los learning paths.
		JSONArray jLearningPaths = new JSONArray();
		for (LearningPath lP : lPS) {
			// Hacer un nuevo objeto para cada learning path
			JSONObject jLearningPath = new JSONObject();
			// Añadir informacion del learning path
			jLearningPath.put("ID", lP.getID());
			jLearningPath.put("titulo", lP.getTitulo());
			jLearningPath.put("descripcion", lP.getDescripcion());
			jLearningPath.put("nivelDificultad", lP.getNivelDificultad());
			// Hacer un nuevo arreglo de objetivos
			JSONArray jObjetivos = new JSONArray();
			for (String objetivo : lP.getObjetivos()) {
				jObjetivos.put(objetivo);
			}
			jLearningPath.put("objetivos", jObjetivos);
			jLearningPath.put("profesor", lP.getProfesorCreador().getUsuario());
			// Obtener todos los estudiantes de un solo LP
			ArrayList<Estudiante> estudiantesInscritos = lP.getEstudiantesInscritos();
			JSONArray jEstudiantesInscritos = new JSONArray();
			for (Estudiante estudiante : estudiantesInscritos) {
				// Se agregan solo los usuarios
				jEstudiantesInscritos.put(estudiante.getUsuario());
			}
			jLearningPath.put("estudiantesInscritos", jEstudiantesInscritos);
			jLearningPaths.put(jLearningPath);
		}
		// Se agregan todos los learning paths a un objeto "LearningPaths"
		jObject.put("LearningPaths", jLearningPaths);
		// Se escribe :D  
		PrintWriter writer = new PrintWriter(direccionArchivo + "/learningpaths.json");
		jObject.write(writer, 2, 0);
		writer.close();
	}

	public static void cargarLP() throws IOException {
		String contenido = "";
		try 
		{
			if (!Files.exists(Paths.get(direccionArchivo + "/learningpaths.json"))) 
			{
				throw new ArchivoException("Archivo no encontrado: learningpaths.json");
			} 
			else 
			{
				contenido = new String(Files.readAllBytes(Paths.get(direccionArchivo + "/learningpaths.json")));
			}
		} 
		catch (ArchivoException e) 
		{
			System.out.println("Error leyendo el archivo: " + e.getMessage());
			System.out.println("Creando archivo de learning paths vacio");
			PrintWriter writer = new PrintWriter(direccionArchivo + "/learningpaths.json");
			writer.write("{\"LearningPaths\":[]}");
			writer.close();
			contenido = new String(Files.readAllBytes(Paths.get(direccionArchivo + "/learningpaths.json")));
		}
		try 
		{
			JSONObject jObject = new JSONObject(contenido);
			JSONArray jLearningPaths = jObject.getJSONArray("LearningPaths");
			System.out.println("Total de Learning Paths: " + jLearningPaths.length()); // Print para debug
			for (int i = 0; i < jLearningPaths.length(); i++) 
			{
				JSONObject jLearningPath = jLearningPaths.getJSONObject(i);
				String ID = jLearningPath.getString("ID");
				String titulo = jLearningPath.getString("titulo");
				String descripcion = jLearningPath.getString("descripcion");
				String nivelDificultad = jLearningPath.getString("nivelDificultad");
				JSONArray jObjetivos = jLearningPath.getJSONArray("objetivos");
				ArrayList<String> objetivos = new ArrayList<String>();
				for (int j = 0; j < jObjetivos.length(); j++) 
				{
					objetivos.add(jObjetivos.getString(j));
				}
				String profesor = jLearningPath.getString("profesor");
				Profesor profesorCreador = (Profesor) Usuario.obtenerUsuario(profesor);
				String idLearningPath = profesorCreador.crearLearningPath(titulo, descripcion, nivelDificultad,objetivos);
				LearningPath lP = LearningPath.getLearningPath(idLearningPath);
				lP.setID(ID);
				JSONArray jEstudiantesInscritos = jLearningPath.getJSONArray("estudiantesInscritos");
				for (int j = 0; j < jEstudiantesInscritos.length(); j++) 
				{
					Estudiante estudiante = (Estudiante) Usuario.obtenerUsuario(jEstudiantesInscritos.getString(j));
					estudiante.inscribirLearningPath(ID);
				}
				System.out.println("Learning Path: " + titulo); // Print para debug
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error leyendo el archivo: " + e.getMessage());
		}
	}

}
