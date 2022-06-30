package repertoire;

import java.util.Properties;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Cette m�thode a �t� cr��e au d�but du projet pour tester des fonctionnalit�s au niveau des contacts
 * elle n'est pas utilis�e dans le projet mais c'est une trace �crite des essais effectu�s 
 * avant la r�alisation du projet
 * @author Thomas CHARMES
 *
 */
public class Test {
	
	// R�cup�rer les propri�t�s de la JVM et afficher les avec les valeurs respectives
	// 1 couple cl�/valeur par ligne
	
	public static void sauvegarde() {
		
		String emplacementDuFichierDeSauvegarde = "phones.properties";
		
		// Cr�ation d'une HashMap pour les contacts
		HashMap<String,String> contacts = new HashMap<String,String>();
		contacts.put("Thomasstodonte","0667207113");
		contacts.put("Michel","0677889901");
		
		// Cr�ation de properties
		Properties proprietesAgenda = new Properties();
		proprietesAgenda.putAll(contacts);
		
		for (Object contact : proprietesAgenda.entrySet()) {
			System.out.println(contact);
		}
		
		// Enregistrement
		try (OutputStream out = new FileOutputStream(emplacementDuFichierDeSauvegarde)){
			proprietesAgenda.store(out, "fichier d'enregistrement");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		
		// Cr�ation de la propri�t� syst�me et affichage
		Properties proprietesSystem = new Properties();
		proprietesSystem = System.getProperties();
		
		for (Object info : proprietesSystem.entrySet()) {
			System.out.println(info);
		}

	}
}