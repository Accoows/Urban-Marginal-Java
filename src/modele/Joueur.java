package modele;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controleur.Global;
import outils.connexion.Connection;

public class Joueur extends Objet implements Global {
	
	private String pseudo;
	private int numPerso;
	private Label message;
	private JeuServeur jeuServeur;
	private int vie; // vie restante du joueur
	private int orientation; //tourné vers la gauche (0) ou vers la droite (1)
	private int etape;

	
	public Joueur(JeuServeur jeuServeur) {
		this.jeuServeur = jeuServeur;
		vie = 10;
		etape = 1;
		orientation = DROITE;
	}
	
	public void affiche(String etat, int etape) {
		// Position et image du personnage
		label.getJLabel().setBounds(posX, posY, L_PERSO, H_PERSO);
		label.getJLabel().setIcon(new ImageIcon(PERSO + numPerso + etat + etape + "d" + orientation + EXTIMAGE));
		// Position du message
		message.getJLabel().setBounds(posX - 10, posY + H_PERSO, L_PERSO + 10, H_MESSAGE);
		message.getJLabel().setText(pseudo + ": " + vie);
		
		jeuServeur.envoi(label);
		jeuServeur.envoi(message);
	}
	
	public void initPerso(String pseudo, int numPerso, Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		this.numPerso = numPerso;
		this.pseudo = pseudo;
		
		label = new Label(Label.getNbLabel(), new JLabel());
		Label.setNbLabel(Label.getNbLabel()+1);
		label.getJLabel().setHorizontalAlignment(SwingConstants.CENTER);
		label.getJLabel().setVerticalAlignment(SwingConstants.CENTER);
		jeuServeur.nouveauLabelJeu(label);
		
		message = new Label(Label.getNbLabel(), new JLabel());
		Label.setNbLabel(Label.getNbLabel()+1);
		message.getJLabel().setHorizontalAlignment(SwingConstants.CENTER);
		message.getJLabel().setFont(new Font("Dialog", Font.PLAIN, 8));
		jeuServeur.nouveauLabelJeu(message);
		
		premierePosition(lesJoueurs, lesMurs);
		
		affiche(MARCHE, etape);
	}
	
	private boolean toucheJoueur(Hashtable<Connection, Joueur> lesJoueurs) {
		for (Joueur unJoueur : lesJoueurs.values()) {
			if (!unJoueur.equals(this)) {
				if (toucheObjet(unJoueur)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean toucheMur(ArrayList<Mur> lesMurs) {
		for (Mur unMur : lesMurs) {
			if (toucheObjet(unMur)) {
				return true;
			}
		}
		return false;
	}
	
	private void premierePosition(Hashtable<Connection, Joueur> lesJoueurs, ArrayList<Mur> lesMurs) {
		label.getJLabel().setBounds(0, 0, L_PERSO, H_PERSO);
		do {
			posX = (int) Math.round(Math.random() * (L_ARENE - L_PERSO));
			posY = (int) Math.round(Math.random() * (H_ARENE - H_PERSO - H_MESSAGE));
		} while(toucheJoueur(lesJoueurs) || toucheMur(lesMurs));
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public Label getMessage() {
		return message;
	}

}
