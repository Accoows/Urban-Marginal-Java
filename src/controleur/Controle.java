package controleur;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuClient;
import modele.JeuServeur;
import modele.Label;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

/**
 * Controleur de l'applications
 * @author emds
 *
 */
public class Controle implements Global {
	
	// propri�t�s
	private EntreeJeu frmEntreeJeu ;
	private Jeu leJeu ;
	private Arene frmArene ;
	private ChoixJoueur frmChoixJoueur ;
	private Connection connection ;

	/**
	 * M�thode de d�marrage
	 * @param args
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * R�cu�pration de la connexion
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection ;
		if (leJeu instanceof JeuServeur) {
			leJeu.setConnection(connection);
		}
	}
	
	/**
	 * Reception d'une information de l'ordi distant, et transfert vers le jeu
	 * @param connection
	 * @param info
	 */
	public void receptionInfo(Connection connection, Object info) {
		leJeu.reception(connection, info);
	}
	
	/* **********************************************************************************************
	 * Ev�nements provenant de la vue
	 * **********************************************************************************************/
	
	/**
	 * G�re les �v�nements provenant de la vue
	 * @param uneFrame
	 * @param info
	 */
	public void evenementVue(JFrame uneFrame, Object info) {
		// quelle est la frame qui demande ?
		if (uneFrame instanceof EntreeJeu) {
			evenementEntreeJeu(info);
		}else if (uneFrame instanceof ChoixJoueur) {
			evenementChoixJoueur(info);
		}
	}
	
	public void evenementModele(Object unjeu, String ordre, Object info){
		if(unjeu instanceof JeuServeur){
			evenementJeuServeur(ordre, info);
		}if(unjeu instanceof JeuClient) {
			evenementJeuClient(ordre, info);
		}
	}
	
	public void evenementJeuServeur(String ordre, Object info){
		if(ordre.equals("ajout mur")){
			frmArene.ajoutMur((JLabel)info);
		}else if(ordre.equals("envoi panel murs")) {
			((JeuServeur)leJeu).envoi((Connection)info, frmArene.getJpnMurs());
		}else if(ordre.equals("ajout joueur")) {
			frmArene.ajoutJoueur((JLabel)info);
		}
	}
	
	public void evenementJeuClient(String ordre, Object info) {
		if(ordre.equals("ajout panel murs")) {
			frmArene.ajoutPanelMurs((JPanel)info);
		}else if(ordre.equals("ajout joueur")) {
			frmArene.ajoutModifJoueur(((Label)info).getNumLabel(), ((Label)info).getJLabel());
		}
	}
	
	/**
	 * G�re les �v�nements provenant de la frame EntreeJeu
	 * @param info
	 */
	private void evenementEntreeJeu(Object info) {
		if ((String)info=="serveur") {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this) ;
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			((JeuServeur)leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		}else{
			if ((new ClientSocket((String)info, PORT, this)).isConnexionOk()) {
				this.leJeu = new JeuClient(this) ;
				this.leJeu.setConnection(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			} 
		}
		
	}

	/**
	 * G�re les �v�nements provenant de la frame ChoixJoueur
	 * @param info
	 */
	private void evenementChoixJoueur(Object info) {
		// envoi de l'information vers le serveur
		((JeuClient)leJeu).envoi(info);
		// fermeture de la frame du choix du jouer
		frmChoixJoueur.dispose();
		// ouverture de la frame de l'arene de jeu
		frmArene.setVisible(true);
	}

	
	
	/* **********************************************************************************************
	 * Ev�nements provenant du mod�le
	 * **********************************************************************************************/

	
	

}
