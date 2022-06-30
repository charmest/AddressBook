package controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import exceptions.ModificationContactException;
import vue.FenetreModifierNumero;

/**
 * classe qui gère la modification de contacts de l'agenda
 * @author Thomas CHARMES
 *
 */
public class ModifierControleur implements ActionListener, DocumentListener{

	@SuppressWarnings("unused")
	private JButton button;
	@SuppressWarnings("unused")
	private JTextField contactsNames;
	@SuppressWarnings("rawtypes")
	static
	DefaultListModel model;
	JMenuItem modifier;
	@SuppressWarnings("rawtypes")
	static JList view;
	
	/**
	 * constructeur de la classe pour le bouton "Modifier"
	 * @param hireButtonModifier
	 * @param contactsNames
	 * @param listModel
	 * @param listView
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public ModifierControleur(JButton hireButtonModifier, JTextField contactsNames, DefaultListModel listModel,
			JList listView) {
		this.button=hireButtonModifier;
		this.model=listModel;
		this.contactsNames=contactsNames;
		this.view=listView;
	}

	/**
	 * constructeur de la classe, il peut être utilisé pour un bouton ou pour un sous-menu
	 * @param listModel
	 * @param listView
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public ModifierControleur(DefaultListModel listModel, JList listView) {
		this.model=listModel;
		this.view=listView;
	}

	/**
	 * constructeur de la classe pour le sous-menu "Modifier"
	 * @param modifier
	 * @param contactsNames
	 * @param listModel
	 * @param listView
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public ModifierControleur(JMenuItem modifier, JTextField contactsNames, DefaultListModel listModel,
			JList listView) {
		this.model=listModel;
		this.contactsNames=contactsNames;
		this.view=listView;
		this.modifier=modifier;
	}

	/**
	 * méthode qui modifie le numero de téléphone du nom passé en paramètre.
	 * Le nouveau numero est également passé en paramètre.
	 * @param nom, doit exister dans le répertoire avant la modification
	 * @param numero, doit avoir une longueur d'exactement 10 chiffres
	 * @return true si la modification a bien eu lieu
	 * @throws ModificationContactException
	 */
	public static boolean modifierContact(String nom, String numero) throws ModificationContactException {
		if (!repertoire.CarnetAdresses.proprietesLuesDepuisFichier.containsKey(nom)) {
			throw new ModificationContactException("le nom saisi ne figure pas dans la liste des contacts");
		}
		else {
			if (numero.trim().length() != 10) {
				throw new ModificationContactException("Le numéro de téléphone saisi ne comporte pas exactement 10 chiffres");
			}
			else {
				repertoire.CarnetAdresses.proprietesLuesDepuisFichier.setProperty(nom, numero);
				return true;
			}
		}
	}

	public static void creerFenetreModifier() {
		new FenetreModifierNumero(model, view);
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {}

	@Override
	public void removeUpdate(DocumentEvent e) {}

	@Override
	public void changedUpdate(DocumentEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {		
		creerFenetreModifier();
	}
	
}