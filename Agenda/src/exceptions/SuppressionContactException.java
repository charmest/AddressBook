package exceptions;

/**
 * Classe qui g�re les exceptions lors de la suppression d'un contact
 * @author Thomas CHARMES
 *
 */
public class SuppressionContactException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SuppressionContactException(String message) {
		super(message);
	}

}
