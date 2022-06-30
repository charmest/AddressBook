package repertoire;

import java.util.Properties;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

/**
 * Cette méthode a été créée au début du projet pour tester des fonctionnalités au niveau des contacts
 * elle n'est pas utilisée dans le projet mais c'est une trace écrite des essais effectués 
 * avant la réalisation du projet
 * @author Thomas CHARMES
 *
 */
public class Test {
	
	// Récupérer les propriétés de la JVM et afficher les avec les valeurs respectives
	// 1 couple clé/valeur par ligne
	
	public static void sauvegarde() {
		
		String emplacementDuFichierDeSauvegarde = "phones.properties";
		
		// Création d'une HashMap pour les contacts
		HashMap<String,String> contacts = new HashMap<String,String>();
		contacts.put("Thomasstodonte","0667207113");
		contacts.put("Michel","0677889901");
		
		// Création de properties
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
		
		// Création de la propriété système et affichage
		Properties proprietesSystem = new Properties();
		proprietesSystem = System.getProperties();
		
		for (Object info : proprietesSystem.entrySet()) {
			System.out.println(info);
		}

	}
}