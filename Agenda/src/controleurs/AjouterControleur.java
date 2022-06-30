package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.ImportContactException;
import exceptions.ModificationContactException;
import vue.FenetreAjout;
import vue.FenetreAjoutNumero;

/**
 * Classe qui g�re l'insertion de nouveaux contacts dans l'agenda
 * Cette classe a la charge d'instancier une nouvelle fen�tre d'ajout de contact
 * puis d'ajouter ce contact � la liste en utilisant la m�thode insererContact(nom, numero) 
 * @author Thomas CHARMES
 *  
 */
@SuppressWarnings("rawtypes")
public class AjouterControleur implements ActionListener, DocumentListener {
	/**
	 * Bool�en qui renvoie vrai si le bouton est cliquable
	 */
	private boolean alreadyEnabled = false;
	private JButton button;
	
	/**
	 * Ces variables sont utilis�es pour lier le contr�leur � la vue et au mod�le
	 * L'architechture de ce code est en MVC
	 * Cela est fait dans le(s) constructeur(s)
	 */
	static DefaultListModel model;
	static JList view;
	
	/**
	 * Constructeur de la classe utilis� pour le bouton "Ajouter"
	 * @param button
	 * @param contactsNames
	 * @param model
	 * @param view
	 */
	@SuppressWarnings("static-access")
	public AjouterControleur(JButton button, JTextField contactsNames, DefaultListModel model, JList view) {
		this.button = button;
		AjouterControleur.model = model;
		this.view = view;
	}

	/**
	 * Constructeur de la classe utilis� pour le sous-menu "Ajouter"
	 * @param ajouter
	 * @param contactsNames
	 * @param listModel
	 * @param listView
	 */
	@SuppressWarnings("static-access")
	public AjouterControleur(JMenuItem ajouter, JTextField contactsNames, DefaultListModel listModel, JList listView) {
		this.model=listModel;
		this.view=listView;
	}

	/**
	 * m�thode utilis�e pour ins�rer un contact � une liste puis ajouter cette liste � la property
	 * insertion possible si le nom n'existe pas d�j� dans les contacts,
	 * sinon on modifie les propri�t�s du nom existant
	 * @param nom
	 * @param numero, doit �tre de longueur 10 exactement
	 * @return true si l'insertion a bien eu lieu
	 * @throws ImportContactException
	 * @throws ModificationContactException
	 * voir modifierContact(String nom, String numero)
	 */
	public static boolean insererContact(String nom, String numero) throws ImportContactException, ModificationContactException {
		// V�rification de la longeur du num�ro
		if (numero.trim().length() == 10) {
			// V�rification de la non-existance de ce nom dans la liste 
			if (!repertoire.CarnetAdresses.proprietesLuesDepuisFichier.containsKey(nom)) {
				// Cr�ation du contact � partir des donn�es nom et numero
				HashMap<String, String> contact = new HashMap<String, String>();
				contact.put(nom, numero);
				// Insertion du contact dans la liste
				repertoire.CarnetAdresses.proprietesLuesDepuisFichier.putAll(contact);
			}
			// Si le nom existe d�j� dans la liste, on modifie le num�ro associ� sans recr�er le m�me nom
			else {
				ModifierControleur.modifierContact(nom, numero);
			}	
			// L'insertion s'est bien pass�e
			return true;
		}
		// Si le num�ro ne fait pas exactement 10 caract�res
		else {
			throw new ImportContactException("Erreur lors de l'importation des contacts : le num�ro de t�l�phone ne contient pas exactement 10 chiffres");
		}
	}

	// Requis par ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		creerFenetreAjoutNom();;
	}

	// Cette m�thode teste si le nom est d�j� dans la liste
	public static boolean alreadyInList(String name) {
		return model.contains(name);
	}
	
	// Requis par DocumentListener
	@Override
	public void insertUpdate(DocumentEvent e) {
		enableButton();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		handleEmptyTextField(e);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		if(!handleEmptyTextField(e)) {
			enableButton();
		}
	}
	
	private void enableButton() {
		if(!alreadyEnabled) {
			button.setEnabled(true);
		}
	}
	
	private boolean handleEmptyTextField(DocumentEvent e) {
		if(e.getDocument().getLength() <= 0) {
			button.setEnabled(false);
			alreadyEnabled = false;
			return true;
		}
		return false;
	}

	public static void creerFenetreAjoutNom() {
		new FenetreAjout(model, view);
	}
	
	public static void creerFenetreAjoutNumero() {
		new FenetreAjoutNumero(model, view);
	}
}
