package vue;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import controleurs.AjouterControleur;

/**
 * Cette classe permet la cr�ation d'une fen�tre sp�ciale pour saisir un nom lors d'un ajout de contact
 * Cela aurait pu �tre fait gr�ace � une bo�te de dialogue
 * @author Thomas CHARMES
 *
 */
public class FenetreAjout extends JFrame implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btValiderNom;
	public static JTextField txtNom;

	@SuppressWarnings("rawtypes")
	DefaultListModel model;
	@SuppressWarnings("rawtypes")
	JList view;

	/**
	 * Constructeur de la classe
	 * @param model
	 * @param view
	 */
	@SuppressWarnings("rawtypes")
	public FenetreAjout (DefaultListModel model, JList view) {
		this.model = model;
		this.view = view;
		// Param�trage de la fen�tre
		setTitle("Nom du nouveau contact");
		setBounds(500, 500, 200, 150);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btValiderNom = new JButton("Valider le nom");
		txtNom = new JTextField(10);
		txtNom.setText("");
		contentPane.add(new JLabel("Nouveau Nom"));
		contentPane.add(txtNom);
		contentPane.add(btValiderNom);
		btValiderNom.addActionListener(this);
		this.setVisible(true);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = txtNom.getText();
		int index = view.getSelectedIndex();
		if (index == -1) { // Pas de s�l�ction, insertion au d�but
			index = 0;
		}
		else {
			index++; // Ajout apr�s l'indice s�lectionn�
		}
		if (e.getSource()==btValiderNom) {
			// Insertion de l'�l�ment � l'index indiqu�
			model.insertElementAt(name, index);
		}
		//selection du nouvel item
		view.setSelectedIndex(index);
		view.ensureIndexIsVisible(index);
		// Cr�ation de la fen�tre de cr�ation du num�ro correspondant
		controleurs.AjouterControleur.creerFenetreAjoutNumero();
		// Pour que la fen�tre de nom se ferme � l'ouverture de la fen�tre de num�ro
		dispose();
		if(name.equals("")||AjouterControleur.alreadyInList(name)) {
			Toolkit.getDefaultToolkit().beep();
			txtNom.requestFocusInWindow();
			txtNom.selectAll();
			return;
		}		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}