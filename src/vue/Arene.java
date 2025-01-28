package vue;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import controleur.Global;

/**
 * Frame de l'ar�ne du jeu
 * @author emds
 *
 */
public class Arene extends JFrame implements Global {

	private JPanel contentPane;
	private JTextField txtSaisie;
	private JPanel jpnMurs;
	private JPanel jpnJeu;
	
	
	public void ajoutMur(JLabel mur){
		jpnMurs.add(mur);
		jpnMurs.repaint();
	}
	
	public void ajoutJoueur(JLabel joueur) {
		jpnJeu.add(joueur);
		jpnJeu.repaint();
		contentPane.requestFocus();
	}
	
	public void ajoutPanelMurs(JPanel objet) {
		jpnMurs.add(objet);
		jpnMurs.repaint();
		contentPane.requestFocus();
	}
	
	public void ajoutModifJoueur(int num, JLabel unLabel) {
		try {
			jpnJeu.remove(num);
		} catch (ArrayIndexOutOfBoundsException e) {
			// A ajouter pour message console -> System.err.println();
		}
		jpnJeu.add(unLabel, num);
		jpnJeu.repaint();
	}
	
	public JPanel getJpnMurs() { //Test à supprimer si nécessaire
		if (jpnMurs == null) {
			jpnMurs = new JPanel();
			jpnMurs.setLayout(null);
			jpnMurs.setBounds(new Rectangle(0, 0, L_ARENE, H_ARENE));
			jpnMurs.setOpaque(false);
		}
		return jpnMurs;
	}

	/**
	 * Create the frame.
	 */
	public Arene() {
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, L_ARENE+3*MARGE, H_ARENE+H_CHAT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, L_ARENE, H_ARENE);
		jpnJeu.setOpaque(false);
		contentPane.add(jpnJeu);
		jpnJeu.setLayout(null);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, L_ARENE, H_ARENE);
		jpnMurs.setOpaque(false);
		contentPane.add(jpnMurs);
		jpnMurs.setLayout(null);
		
		JLabel lblFond = new JLabel("");
		lblFond.setBounds(0, 0, L_ARENE, H_ARENE);
		lblFond.setIcon(new ImageIcon(FONDARENE));
		contentPane.add(lblFond);
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setBounds(0, H_ARENE+H_SAISIE, L_ARENE, H_CHAT-H_SAISIE-7*MARGE);
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(jspChat);
		
		JTextArea txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
		
		txtSaisie = new JTextField();
		txtSaisie.setBounds(0, H_ARENE, L_ARENE, H_SAISIE);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
	}
	
}
