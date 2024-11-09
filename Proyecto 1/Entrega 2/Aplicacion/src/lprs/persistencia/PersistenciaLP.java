package lprs.persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import lprs.exceptions.ArchivoException;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.manejador.ManejadorLP;
import lprs.manejador.ManejadorSesion;

public class PersistenciaLP implements Persistencia {

	public PersistenciaLP() {

	}

	public void guardarLP2(ManejadorSesion manejadorS, ManejadorLP manejadorLP) throws IOException {
		// Se obtienen todos los learning paths creados en formato ArrayList
		ArrayList<LearningPath> lPS = manejadorLP.getLearningPaths();
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(new File(direccionArchivo + "/learningpaths.dat")));
		oos.writeObject(lPS);
		oos.close();
	}

	public void cargarLP2(ManejadorSesion manejadorS, ManejadorLP manejadorLP) throws IOException {
		ArrayList<LearningPath> lPS = new ArrayList<LearningPath>();
		try {
			if (!Files.exists(Paths.get(direccionArchivo + "/learningpaths.dat"))) {
				throw new ArchivoException("Archivo no encontrado: learningpaths.dat");
			} else {
				// Se lee el archivo
				ObjectInputStream ois = new ObjectInputStream(
						new FileInputStream(new File(direccionArchivo + "/learningpaths.dat")));
				lPS = (ArrayList<LearningPath>) ois.readObject();
				ois.close();
			}
		} catch (ArchivoException e) {
			System.out.println("Error leyendo el archivo: " + e.getMessage());
			System.out.println("Creando archivo de learning paths vacio");
			OutputStream os = new FileOutputStream(direccionArchivo + "/learningpaths.dat");
			os.close();
		} catch (Exception e) {
			System.out.println("Error leyendo el archivo: " + e.getMessage());
		}
		for (LearningPath lP : lPS) {
			manejadorLP.addLearningPath(lP);
			System.out.println("Learning Path: " + lP.getTitulo());
		}
	}

	public void guardarLP(ManejadorSesion manejadorS, ManejadorLP manejadorLP) throws IOException {
		// Se obtienen todos los learning paths creados en formato ArrayList
		ArrayList<LearningPath> lPS = manejadorLP.getLearningPaths();
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

			// Se agrega el learning path al arreglo de learning paths
			jLearningPaths.put(jLearningPath);
		}
		// Se agregan todos los learning paths a un objeto "LearningPaths"
		jObject.put("LearningPaths", jLearningPaths);
		// Se escribe :D
		PrintWriter writer = new PrintWriter(direccionArchivo + "/learningpaths.json");
		jObject.write(writer, 2, 0);
		writer.close();
	}

	public void cargarLP(ManejadorSesion manejadorS, ManejadorLP manejadorLP) throws IOException {
		String contenido = "";
		try {
			if (!Files.exists(Paths.get(direccionArchivo + "/learningpaths.json"))) {
				throw new ArchivoException("Archivo no encontrado: learningpaths.json");
			} else {
				contenido = new String(Files.readAllBytes(Paths.get(direccionArchivo + "/learningpaths.json")));
			}
		} catch (ArchivoException e) {
			System.out.println("Error leyendo el archivo: " + e.getMessage());
			System.out.println("Creando archivo de learning paths vacio");
			PrintWriter writer = new PrintWriter(direccionArchivo + "/learningpaths.json");
			writer.write("{\"LearningPaths\":[]}");
			writer.close();
			contenido = new String(Files.readAllBytes(Paths.get(direccionArchivo + "/learningpaths.json")));
		}
		try {
			JSONObject jObject = new JSONObject(contenido);
			JSONArray jLearningPaths = jObject.getJSONArray("LearningPaths");
			System.out.println("Total de Learning Paths: " + jLearningPaths.length()); // Print para debug
			for (int i = 0; i < jLearningPaths.length(); i++) {
				JSONObject jLearningPath = jLearningPaths.getJSONObject(i);
				String ID = jLearningPath.getString("ID");
				String titulo = jLearningPath.getString("titulo");
				String descripcion = jLearningPath.getString("descripcion");
				String nivelDificultad = jLearningPath.getString("nivelDificultad");
				JSONArray jObjetivos = jLearningPath.getJSONArray("objetivos");
				ArrayList<String> objetivos = new ArrayList<String>();
				for (int j = 0; j < jObjetivos.length(); j++) {
					objetivos.add(jObjetivos.getString(j));
				}
				String profesor = jLearningPath.getString("profesor");
				Profesor profesorCreador = (Profesor) manejadorS.obtenerUsuario(profesor);
				String idLearningPath = profesorCreador.crearLearningPath(titulo, descripcion, nivelDificultad,
						objetivos);
				LearningPath lP = manejadorLP.getLearningPath(idLearningPath);
				lP.setID(ID);
				JSONArray jEstudiantesInscritos = jLearningPath.getJSONArray("estudiantesInscritos");
				for (int j = 0; j < jEstudiantesInscritos.length(); j++) {
					Estudiante estudiante = (Estudiante) manejadorS.obtenerUsuario(jEstudiantesInscritos.getString(j));
					estudiante.inscribirLearningPath(ID);
				}
				System.out.println("Learning Path: " + titulo); // Print para debug
			}
		} catch (Exception e) {
			System.out.println("Error leyendo el archivo: " + e.getMessage());
		}
	}

}
