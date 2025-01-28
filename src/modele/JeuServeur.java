package modele;

import java.util.ArrayList;
import java.util.Hashtable;

import controleur.Controle;
import controleur.Global;
import outils.connexion.Connection;

/**
 * Gestion du jeu c�t� serveur
 * @author emds
 *
 */
public class JeuServeur extends Jeu implements Global {
	
	private ArrayList<Mur> lesMurs = new ArrayList<Mur>();
	private Hashtable<Connection,Joueur> lesJoueurs = new Hashtable<Connection,Joueur>();
	private ArrayList<Joueur> lesJoueursDansLordre = new ArrayList<Joueur>();

	/**
	 * Constructeur
	 * @param controle
	 */
	public JeuServeur(Controle controle) {
		super.controle = controle ;
		Label.setNbLabel(0);
	}
	
	public void constructionMurs() {
		for(int k = 0; k < NBMURS; k++){
			lesMurs.add(new Mur());
			controle.evenementModele(this, "ajout mur", lesMurs.get(lesMurs.size()-1).getLabel().getJLabel());
		}
	}
	
	@Override
	public void setConnection(Connection connection) {
		lesJoueurs.put(connection, new Joueur(this)); // this ajouté après avoir mis eventModele dans le case
	}

	@Override
	public void reception(Connection connection, Object info) {
		System.out.println(info);
		String[] infos = ((String)info).split(SEPARE);
		switch(Integer.parseInt(infos[0])) {
			case PSEUDO :
				controle.evenementModele(this, "envoi panel murs", connection);
				for(Joueur joueur : lesJoueursDansLordre) {
					super.envoi(connection, joueur.getLabel());
					super.envoi(connection, joueur.getMessage());
				}
				lesJoueurs.get(connection).initPerso(infos[1], Integer.parseInt(infos[2]), lesJoueurs, lesMurs);
				this.lesJoueursDansLordre.add(this.lesJoueurs.get(connection));
				break;
		}
	}
	
	public void envoi(Object info) {
		for(Connection connection : lesJoueurs.keySet()) {
			super.envoi(connection, info);
		}
	}

	public void nouveauLabelJeu(Label label) {
		controle.evenementModele(this, "ajout joueur", label.getJLabel());
	}

	@Override
	public void deconnection(Connection connection) {
		// TODO Auto-generated method stub
		
	}

}
