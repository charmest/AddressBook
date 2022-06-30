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
 * Classe qui gère l'insertion de nouveaux contacts dans l'agenda
 * Cette classe a la charge d'instancier une nouvelle fenêtre d'ajout de contact
 * puis d'ajouter ce contact à la liste en utilisant la méthode insererContact(nom, numero) 
 * @author Thomas CHARMES
 *  
 */
@SuppressWarnings("rawtypes")
public class AjouterControleur implements ActionListener, DocumentListener {
	/**
	 * Booléen qui renvoie vrai si le bouton est cliquable
	 */
	private boolean alreadyEnabled = false;
	private JButton button;
	
	/**
	 * Ces variables sont utilisées pour lier le contrôleur à la vue et au modèle
	 * L'architechture de ce code est en MVC
	 * Cela est fait dans le(s) constructeur(s)
	 */
	static DefaultListModel model;
	static JList view;
	
	/**
	 * Constructeur de la classe utilisé pour le bouton "Ajouter"
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
	 * Constructeur de la classe utilisé pour le sous-menu "Ajouter"
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
	 * méthode utilisée pour insérer un contact à une liste puis ajouter cette liste à la property
	 * insertion possible si le nom n'existe pas déjà dans les contacts,
	 * sinon on modifie les propriétés du nom existant
	 * @param nom
	 * @param numero, doit être de longueur 10 exactement
	 * @return true si l'insertion a bien eu lieu
	 * @throws ImportContactException
	 * @throws ModificationContactException
	 * voir modifierContact(String nom, String numero)
	 */
	public static boolean insererContact(String nom, String numero) throws ImportContactException, ModificationContactException {
		// Vérification de la longeur du numéro
		if (numero.trim().length() == 10) {
			// Vérification de la non-existance de ce nom dans la liste 
			if (!repertoire.CarnetAdresses.proprietesLuesDepuisFichier.containsKey(nom)) {
				// Création du contact à partir des données nom et numero
				HashMap<String, String> contact = new HashMap<String, String>();
				contact.put(nom, numero);
				// Insertion du contact dans la liste
				repertoire.CarnetAdresses.proprietesLuesDepuisFichier.putAll(contact);
			}
			// Si le nom existe déjà dans la liste, on modifie le numéro associé sans recréer le même nom
			else {
				ModifierControleur.modifierContact(nom, numero);
			}	
			// L'insertion s'est bien passée
			return true;
		}
		// Si le numéro ne fait pas exactement 10 caractères
		else {
			throw new ImportContactException("Erreur lors de l'importation des contacts : le numéro de téléphone ne contient pas exactement 10 chiffres");
		}
	}

	// Requis par ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		creerFenetreAjoutNom();;
	}

	// Cette méthode teste si le nom est déjà dans la liste
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
