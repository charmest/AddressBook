package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleurs.AjouterControleur;
import controleurs.EnregistrerControleur;
import controleurs.ModifierControleur;
import controleurs.SupprimerControleur;
import exceptions.ImportContactException;
import exceptions.ModificationContactException;

/**
 * Cette classe permet la création de la fenêtre principale qui affiche la liste des contacts
 * ainsi que leurs informations associées.
 * voir README pour de plus amples informations
 * @author Thomas CHARMES
 *
 */
public class FenetrePrincipale extends JPanel implements ListSelectionListener, WindowListener{
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private static JList listView;
	
	@SuppressWarnings("rawtypes")
	private static DefaultListModel listModel;
	
	private static final String ajoutString = "Ajouter";
	private static final String supprimerString = "Supprimer";
	private static final String modifierString = "Modifier";
	private static final String enregistrerString = "Enregistrer";
	
	/**
	 * création des boutons
	 */
	private static JButton btAjouter;
	private JButton hireButtonSupprimer;
	private JButton hireButtonModifier;
	private JButton hireButtonEnregistrer;
	
	/**
	 * création de la zone d'affichage des numéros
	 */
	private static JTextField contactsNum;
	
	/**
	 * création des menus
	 */
	public static JMenuBar menuBar;
	static JMenu menu;
	static JMenuItem ajouter;
	static JMenuItem modifier;
	static JMenuItem enregistrer;
	
	/**
	 * constructeur de la classe
	 * @throws ImportContactException
	 * @throws ModificationContactException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FenetrePrincipale() throws ImportContactException, ModificationContactException, FileNotFoundException, IOException {
		super(new BorderLayout());
		listModel = new repertoire.CarnetAdresses();
		// Association entre vue et répertoire (modele)
		listView = new JList(listModel);
		listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listView.setSelectedIndex(0);
        listView.addListSelectionListener(this);
        listView.setVisibleRowCount(7);
        JScrollPane listScrollPane = new JScrollPane(listView);
        contactsNum = new JTextField();
        contactsNum.setEditable(false);
        contactsNum.setBackground(Color.WHITE);
        //Création des controleurs
        hireButtonSupprimer = new JButton(supprimerString);
        hireButtonModifier = new JButton(modifierString);
        hireButtonEnregistrer = new JButton(enregistrerString);
        SupprimerControleur hireSupprimerListener = new SupprimerControleur(hireButtonSupprimer, contactsNum, listModel, listView);
        hireButtonSupprimer.setActionCommand(supprimerString);
        hireButtonSupprimer.addActionListener(new SupprimerControleur(listModel, listView));
        hireButtonSupprimer.setEnabled(false);
        ModifierControleur hireModifierListener = new ModifierControleur(hireButtonModifier, contactsNum, listModel, listView);
        hireButtonModifier.setActionCommand(modifierString);
        hireButtonModifier.addActionListener(new ModifierControleur(listModel, listView));
        hireButtonModifier.setEnabled(false);
        EnregistrerControleur hireEnregistrerListener = new EnregistrerControleur(hireButtonEnregistrer, /* contactsNames, listModel,*/ listView);
        hireButtonEnregistrer.setActionCommand(enregistrerString);
        hireButtonEnregistrer.addActionListener(new EnregistrerControleur(hireButtonEnregistrer, /* contactsNames, listModel,*/ listView));
        hireButtonEnregistrer.setEnabled(false);
        contactsNum.getDocument().addDocumentListener(hireSupprimerListener);
        contactsNum.getDocument().addDocumentListener(hireModifierListener);
        contactsNum.getDocument().addDocumentListener(hireEnregistrerListener);
        btAjouter = new JButton(ajoutString);
        btAjouter.setActionCommand(ajoutString);
        btAjouter.addActionListener(new AjouterControleur(btAjouter, contactsNum, listModel, listView));
        //Création du panel qui utilise boxLayout
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(btAjouter);
        buttonPane.add(hireButtonSupprimer);
        buttonPane.add(hireButtonModifier);
        buttonPane.add(hireButtonEnregistrer);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel contactsNumPane = new JPanel();
        contactsNumPane.setLayout(new BoxLayout(contactsNumPane, BoxLayout.LINE_AXIS));
        contactsNumPane.add(contactsNum);
        contactsNumPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(listScrollPane, BorderLayout.PAGE_START);
        add(buttonPane, BorderLayout.PAGE_END);
        add(contactsNumPane, BorderLayout.CENTER);
        //Ajout des éléments depuis le fichier "phone.properties"
        for(Map.Entry<Object, Object> info : repertoire.CarnetAdresses.proprietesLuesDepuisFichier.entrySet()) {
        	listModel.addElement(info.getKey());
        }
	}
	
	/**
	 * Cette méthode est requise par ListSelectionListener
	 * Activation des boutons après une modification de la liste des contacts
	 */
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting()==false) {
			if(listView.getSelectedIndex() != -1) {
				hireButtonModifier.setEnabled(true);
				hireButtonSupprimer.setEnabled(true);
				hireButtonEnregistrer.setEnabled(true);
			}
			else {
				hireButtonModifier.setEnabled(false);
				hireButtonSupprimer.setEnabled(false);
			}
			//Affichier les informations de l'élément cliqué
			if(!(listView.getSelectedIndex()==-1)) {
				Object nom = listView.getSelectedValue();
				Object num = repertoire.CarnetAdresses.proprietesLuesDepuisFichier.get(nom);
				//Affichage
				contactsNum.setText((String) num);
			}
		}
	}
	
	/**
	 * création de la fenetre
	 * @throws ImportContactException
	 * @throws ModificationContactException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void createAndShowWindow() throws ImportContactException, ModificationContactException, FileNotFoundException, IOException {
		JFrame frame = new JFrame("répertoire");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 250, 200, 500);
		JComponent newContentPane = new FenetrePrincipale();
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		// Création du menu :
        // Barre de menu :
        menuBar = new JMenuBar();
        // Sous-menus contacts et enregistrer 
        menu = new JMenu("Contacts");
        enregistrer = new JMenuItem(enregistrerString);
        menu.setMnemonic(KeyEvent.VK_C);
        enregistrer.setMnemonic(KeyEvent.VK_S);
        // Sous-menus de contacts
        ajouter = new JMenuItem(ajoutString, KeyEvent.VK_A);
        modifier = new JMenuItem(modifierString);
        // Ajout de listener aux menus
        ajouter.addActionListener(new AjouterControleur(ajouter,contactsNum, listModel, listView));
        modifier.addActionListener(new ModifierControleur(modifier,contactsNum, listModel, listView));
        enregistrer.addActionListener(new EnregistrerControleur(enregistrer,contactsNum, listModel, listView));        
        menu.add(ajouter);
        menu.add(modifier);
        menu.add(enregistrer);
        // Ajout du menus contacts et enregistrement à la barre de menu
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				try {
					createAndShowWindow();
				} 
				catch (ImportContactException e) {	
					e.printStackTrace();
				} 
				catch (ModificationContactException e) {	
					e.printStackTrace();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} 
				catch (IOException e) {	
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if(hireButtonEnregistrer.isEnabled()) {
			JOptionPane voulezvoussauvegarder = new JOptionPane("Voulez-vous enregistrer les modifications apportées ?");
			@SuppressWarnings("static-access")
			int option = voulezvoussauvegarder.showConfirmDialog(this,"Quitter sans sauvegarder", "Quitter sans sauvegarder", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				EnregistrerControleur.enregistrer();
			}
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e) {}
	
	@Override
	public void windowIconified(WindowEvent e) {}
	
	@Override
	public void windowDeiconified(WindowEvent e) {}
	
	@Override
	public void windowActivated(WindowEvent e) {}
	
	@Override
	public void windowDeactivated(WindowEvent e) {}

}