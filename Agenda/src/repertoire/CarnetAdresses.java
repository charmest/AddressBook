package repertoire;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.DefaultListModel;

import exceptions.ImportContactException;
import exceptions.ModificationContactException;
import exceptions.SuppressionContactException;

/**
 * Classe qui gère l'insertion, la modification et la suppression de contacts.
 * Elle gère également l'enregistrement des propriétés "contacts" dans un fichier externe "phone.properties"
 * Ce qui est en commentaire a été utilisé pour effectuer des tests
 * @author Thomas Charmes
 *
 */
@SuppressWarnings("rawtypes")
public class CarnetAdresses extends DefaultListModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Propriété pour stocker les informations
    //	public static Properties contacts;
	
	public static Properties proprietesLuesDepuisFichier;
			
	// Création d'une HashMap pour les contacts
	public static HashMap<String,String> listeDExemplesEnDur;

	/**
	 * constructeur de la classe, instancie la liste
	 * @throws ImportContactException
	 * @throws ModificationContactException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CarnetAdresses() throws ImportContactException, ModificationContactException, FileNotFoundException, IOException{
		// Récupération des contacts depuis le fichier "phone.properties"
		proprietesLuesDepuisFichier = PropertyLoader.load("phone.properties"); 
	}
	
	public static void creerCarnetAdresses() throws FileNotFoundException, ImportContactException, ModificationContactException, IOException {
		new CarnetAdresses();
	}

	/**
	 * méthode principale pour exécuter le programme
	 * @throws ImportContactException
	 * @throws ModificationContactException
	 * @throws SuppressionContactException
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws ImportContactException, ModificationContactException, SuppressionContactException, FileNotFoundException, IOException {
		creerCarnetAdresses();
	}
}