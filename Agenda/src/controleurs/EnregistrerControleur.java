package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * classe qui g�re l'enregistrement des propri�t�s "contacts"
 * dans le fichier externe "phone.properties" gr�ce � la m�thode "enregistrer()"
 * @author Thomas CHARMES
 *
 */
@SuppressWarnings("rawtypes")
public class EnregistrerControleur implements ActionListener, DocumentListener{

	private boolean alreadyEnabled = false;
	private JButton button;
	DefaultListModel model;
	JList view;
	JMenuItem enregistrer;
	
	/**
	 * Constructeur de la classe pour le bouton "Enregistrer"
	 * @param hireButtonEnregistrer
	 * @param listView
	 */
	public EnregistrerControleur(JButton hireButtonEnregistrer, JList listView) {
		this.button = hireButtonEnregistrer;
		this.view = listView;
	}

	/**
	 * constructeur de la classe pour le sous-menu "Enregistrer"
	 * @param enregistrer
	 * @param contactsNames
	 * @param listModel
	 * @param listView
	 */
	public EnregistrerControleur(JMenuItem enregistrer, JTextField contactsNames, DefaultListModel listModel,
			JList listView) {
		this.enregistrer=enregistrer;
		this.model=listModel;
		this.view=listView;
	}


	/**
	 * m�thode qui enregistre les contacts dans un fichier externe "phone.properties"
	 */
	public static void enregistrer() {
		// D�finition du fichier de stockage
		String emplacementDuFichierDeSauvegarde = "phone.properties";
		// Cr�ation du fichier avec les valeurs de la liste
		try (OutputStream out = new FileOutputStream(emplacementDuFichierDeSauvegarde)){
			repertoire.CarnetAdresses.proprietesLuesDepuisFichier.store(out, "fichier d'enregistrement");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	// M�thodes h�rit�es des listener
	@Override
	public void insertUpdate(DocumentEvent e) {
		enableButton();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		handleEmptyTextField(e);
		enableButton();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		enableButton();
	}
	
	private void enableButton() {
		if(!alreadyEnabled) {
			button.setEnabled(true);
		}
	}

	private boolean handleEmptyTextField(DocumentEvent e) {
		if(e.getDocument().getLength() <= 0) {
			button.setEnabled(true);
			alreadyEnabled = true;
			return true;
		}
		return false;	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		enregistrer();
	}
}
