package exceptions;

/**
 * Classe qui g�re les exceptions lors de la modification d'un contact
 * @author Thomas CHARMES
 *
 */
public class ModificationContactException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModificationContactException(String message) {
		super(message);
	}
}
