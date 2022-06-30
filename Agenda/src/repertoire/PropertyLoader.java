package repertoire;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe qui r�cup�re les propri�t�s du fichier de type properties pass� en param�tre
 * @author Thomas CHARMES
 *
 */
public class PropertyLoader {
	/**
	 * m�thode de chargement des donn�es
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