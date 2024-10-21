package lprs.manejador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lprs.logica.learningPath.LearningPath;

public class ManejadorLP {
	private HashMap<String, LearningPath> learningPathsHash;
	// Dejamos la lista tambien?
	private ArrayList<LearningPath> learningPathsDisponibles; 
	
	public ManejadorLP(){
		learningPathsHash = new HashMap<String, LearningPath>();
		learningPathsDisponibles =new ArrayList<LearningPath>();
	}
	/**
	 * Obtener un learning path por su ID
	 * 
	 * @param ID el ID del learning path
	 * @return el learning path con el ID dado
	 */
	public LearningPath getLearningPath(String ID) {
		return learningPathsHash.get(ID);
	}

	/**
	 * Añadir un learning path
	 * 
	 * @param learningPath
	 */
	public void addLearningPath(LearningPath learningPath) {
		learningPathsDisponibles.add(learningPath);
		learningPathsHash.put(learningPath.getID(), learningPath);
	}

	/**
	 * Obtener todos los learning paths
	 * 
	 * @return una lista con todos los learning paths
	 */
	public ArrayList<LearningPath> getLearningPaths() {
		return learningPathsDisponibles;
	}

	
	public List<LearningPath> learningPathsDisponibles() {
		return getLearningPaths();
	}
}
