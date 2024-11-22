package lprs.manejador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lprs.logica.learningPath.LearningPath;

public class ManejadorLP implements Serializable {
	private HashMap<String, LearningPath> learningPathsHash; //Learning path por ID-LP
	private ArrayList<LearningPath> learningPathsDisponibles;
	private HashMap<String,ArrayList<LearningPath>> learningPathKeyWord; //Learning path por palabra clave

	public ManejadorLP() {
		learningPathsHash = new HashMap<String, LearningPath>();
		learningPathsDisponibles = new ArrayList<LearningPath>();
		learningPathKeyWord = new HashMap<String,ArrayList<LearningPath>>();
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
		System.out.println("Añadiendo learning path: " + learningPath.getTitulo());
		System.out.println(learningPathsDisponibles.size());
		learningPathsDisponibles.add(learningPath);
		System.out.println(learningPathsDisponibles.size());
		learningPathsHash.put(learningPath.getID(), learningPath);
		for (String keyword : learningPath.getKeyWords()){
			if (learningPathKeyWord.containsKey(keyword)){
				learningPathKeyWord.get(keyword).add(learningPath);
			}
			else{
				ArrayList<LearningPath> learningPaths = new ArrayList<LearningPath>();
				learningPaths.add(learningPath);
				learningPathKeyWord.put(keyword, learningPaths);
			}
		}
	}

	/**
	 * Obtener todos los learning paths
	 * 
	 * @return una lista con todos los learning paths
	 */
	public void addLearningPathKeyWord(String keyword, LearningPath learningPath) {
		if (learningPathKeyWord.containsKey(keyword)){
			learningPathKeyWord.get(keyword).add(learningPath);
		}
		else{
			ArrayList<LearningPath> learningPaths = new ArrayList<LearningPath>();
			learningPaths.add(learningPath);
			learningPathKeyWord.put(keyword, learningPaths);
		}
	}
	public ArrayList<String> getKeyWords() {
        return new ArrayList<String>(learningPathKeyWord.keySet());
	}

	public ArrayList<LearningPath> getLearningPathsKeywords(String keyword) {
		return learningPathKeyWord.get(keyword);
	}

	public ArrayList<LearningPath> getLearningPaths() {
		return learningPathsDisponibles;
	}

	public List<LearningPath> learningPathsDisponibles() {
		return getLearningPaths();
	}

	public HashMap<String, LearningPath> learningPathsHashMap() {
		return learningPathsHash;
	}

	public HashMap<String, LearningPath> getLearningPathsHash() {
		return learningPathsHash;
	}

	public void setLearningPathsHash(HashMap<String, LearningPath> learningPathsHash) {
		this.learningPathsHash = learningPathsHash;
	}

	public ArrayList<LearningPath> getLearningPathsDisponibles() {
		return learningPathsDisponibles;
	}

	public void setLearningPathsDisponibles(ArrayList<LearningPath> learningPathsDisponibles) {
		this.learningPathsDisponibles = learningPathsDisponibles;
	}

	public String generarID() {
		return (Integer.toString(learningPathsHash.size()));
	}
}
