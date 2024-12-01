package lprs.exceptions;

import lprs.logica.learningPath.LearningPath;

public class ClonarLPException extends Exception {
    public ClonarLPException(LearningPath lp) {
        super("No puedes clonar el Learning Path " + lp.getID() + " porque eres el dueño de este Learning Path.");
    }

}
