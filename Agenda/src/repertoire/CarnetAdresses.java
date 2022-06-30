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
 * Classe qui g�re l'insertion, la modification et la suppression de contacts.
 * Elle g�re �galement l'enregistrement des propri�t�s "contacts" dans un fichier externe "phone.properties"
 * Ce qui est en commentaire a �t� utilis� pour effectuer des tests
 * @author Thomas Charmes
 *
 */
@SuppressWarnings("rawtypes")
public class CarnetAdresses extends DefaultListModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Propri�t� pour stocker les informations
    //	public static Properties contacts;
	
	public static Properties proprietesLuesDepuisFichier;
			
	// Cr�ation d'une HashMap pour les contacts
	public static HashMap<String,String> listeDExemplesEnDur;

	/**
	 * constructeur de la classe, instancie la liste
	 * @throws ImportContactException
	 * @throws ModificationContactException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CarnetAdresses() throws ImportContactException, ModificationContactException, FileNotFoundException, IOException{
		// R�cup�ration des contacts depuis le fichier "phone.properties"
		proprietesLuesDepuisFichier = PropertyLoader.load("phone.properties"); 
	}
	
	public static void creerCarnetAdresses() throws FileNotFoundException, ImportContactException, ModificationContactException, IOException {
		new CarnetAdresses();
	}

	/**
	 * m�thode principale pour ex�cuter le programme
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