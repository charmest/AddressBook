package repertoire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe qui récupère les propriétés du fichier de type properties passé en paramètre
 * @author Thomas CHARMES
 *
 */
public class PropertyLoader {
	/**
	 * méthode de chargement des données
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Properties load(String filename) throws IOException, FileNotFoundException {
		Properties properties = new Properties(); 
		FileInputStream input = new FileInputStream(filename); 
		try
		{ 
			properties.load(input);
			return properties; 
		} 
		finally
		{ 
			input.close(); 
		} 
	}
}