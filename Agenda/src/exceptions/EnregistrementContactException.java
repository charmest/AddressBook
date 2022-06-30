package exceptions;

/**
 * Classe qui gère les exceptions lors de l'enregistrement d'un contact
 * @author Thomas CHARMES
 *
 */
public class EnregistrementContactException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EnregistrementContactException(String message) {
		super(message);
	}

}
