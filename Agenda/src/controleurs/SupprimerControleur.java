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

import exceptions.SuppressionContactException;

/**
 * classe qui gère la suppression de contacts de l'agenda
 * @author Thomas CHARMES
 *
 */
@SuppressWarnings("rawtypes")
public class SupprimerControleur implements ActionListener, DocumentListener{

	DefaultListModel model;
	JList view;
	JTextField contactsNames;
	DefaultListModel listModel;
	JMenuItem supprimer;
	
	/**
	 * constructeur de la classe pour le bouton "Supprimer"
	 * @param hireButtonSupprimer
	 * @param contactsNames
	 * @param listModel
	 * @param listView
	 */
	public SupprimerControleur(JButton hireButtonSupprimer, JTextField contactsNames, DefaultListModel listModel,
			JList listView) {
		this.view = listView;
		this.contactsNames = contactsNames;
		this.listModel = listModel;
	}

	/**
	 * constructeur de la classe global
	 * @param listModel
	 * @param listView
	 */
	public SupprimerControleur(DefaultListModel listModel, JList listView) {
		this.model=listModel;
		this.view=listView;
	}

	/**
	 * constructeur de la classe pour le sous-menu "Supprimer"
	 * @param supprimer
	 * @param contactsNames
	 * @param listModel
	 * @param listView
	 */
	public SupprimerControleur(JMenuItem supprimer, JTextField contactsNames, DefaultListModel listModel,
			JList listView) {
		this.supprimer=supprimer;
		this.contactsNames=contactsNames;
		this.listModel=listModel;
		this.view=listView;
	}

	/**
	 * méthode qui supprime un contact du répertoire
	 * @param object, doit exister dans le répertoire avant la suppression
	 * @return true si la suppression a bien eu lieu
	 * @throws SuppressionContactException
	 */
	public static boolean supprimerContact(Object object) throws SuppressionContactException {
		if (!repertoire.CarnetAdresses.proprietesLuesDepuisFichier.containsKey(object)) {
			throw new SuppressionContactException("le nom saisi ne figure pas dans la liste des contacts");
		}
		else { 
			repertoire.CarnetAdresses.proprietesLuesDepuisFichier.remove(object);
		}
		return true;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {}

	@Override
	public void removeUpdate(DocumentEvent e) {}

	@Override
	public void changedUpdate(DocumentEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Récupération de l'index du nom sélectionné
		int index = view.getSelectedIndex();
		// Récupération de la valeur ajoutée à cet index
		Object nomasupprimer = view.getSelectedValue();
		// Suppression de l'affichage
		model.remove(index);
		//Déplacement de l'index après la suppression
		int size = model.getSize();		
		if (size != 0) {
            if (index == model.getSize()) {
                index--;
            }
        }
		view.setSelectedIndex(index--);
		view.ensureIndexIsVisible(index--);
		 //Supprimer le contact associé au nom sélectionné du répertoire
		try {
			supprimerContact(nomasupprimer);
		} catch (SuppressionContactException e1) {
			e1.printStackTrace();
		}
	}
}