package exceptions;

/**
 * Classe qui gère les exceptions lors de l'import d'un contact
 * @author Thomas CHARMES
 *
 */
public class ImportContactException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImportContactException(String message) {
		super(message);
	}
}
