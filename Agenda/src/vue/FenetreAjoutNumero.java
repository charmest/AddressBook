package vue;

import java.awt.Container;
import java.awt.FlowLayout;
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
import exceptions.ImportContactException;
import exceptions.ModificationContactException;

/**
 * Cette classe permet la cr�ation d'une fen�tre sp�ciale pour saisir un num�ro lors d'un ajout de contact
 * Cela aurait pu �tre fait gr�ace � une bo�te de dialogue
 * @author Thomas CHARMES
 *
 */
public class FenetreAjoutNumero extends JFrame implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JButton btValiderNum;
	private JTextField txtNum;
	private JLabel textErreur;
	
	public JTextField getTxtNum() {
		return txtNum;
	}

	public void setTxtNum(JTextField txtNum) {
		this.txtNum = txtNum;
	}

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
	public FenetreAjoutNumero (DefaultListModel model, JList view) {
		this.model = model;
		this.view = view;
		setTitle("Num�ro du nouveau contact");
		setBounds(500, 500, 200, 150);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btValiderNum = new JButton("Valider le num");
		txtNum = new JTextField(10);
		txtNum.setText("");
		textErreur = new JLabel();
		contentPane.add(new JLabel("Nouveau Num�ro"));
		contentPane.add(txtNum);
		contentPane.add(btValiderNum);
		contentPane.add(textErreur);
		btValiderNum.addActionListener(this);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btValiderNum) {
			String nom = FenetreAjout.txtNom.getText();
			String numero = txtNum.getText();
			try {
				AjouterControleur.insererContact(nom, numero);
			} 
			catch (ImportContactException | ModificationContactException | NumberFormatException e1) {
				textErreur.setText("10 chiffres n�cessaires");
				return;
			}
			dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

}