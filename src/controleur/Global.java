package controleur;

/**
 * Regroupement des constantes de l'application
 * @author emds
 *
 */
public interface Global {
	
	public static final int PORT = 6666 ;
	
	// fichiers
	public static final String
		SEPARATOR = "//",
		CHEMIN = "media" + SEPARATOR,
		CHEMINFONDS = CHEMIN + "fonds" + SEPARATOR,
		CHEMINPERSOS = CHEMIN + "personnages" + SEPARATOR,
		FONDCHOIX = CHEMINFONDS + "fondchoix.jpg",
		PERSO = CHEMINPERSOS + "perso",
		EXTIMAGE = ".gif",
		FONDARENE = CHEMINFONDS+"fondarene.jpg",
		CHEMINMURS = CHEMIN + "murs" + SEPARATOR,
		MUR = CHEMINMURS + "mur.gif"; // image du mur
		
	// TAILLES (personnages)
	public static final int
		GAUCHE = 0,
		DROITE = 1,
		NBPERSOS = 3,
		H_PERSO = 44,
		L_PERSO = 39,
		H_MESSAGE = 8;
	
	// etats
	public static final String
		MARCHE = "marche",
		BLESSE = "touche",
		MORT = "mort" ;
	
	
	// messages serveurs
	public static final String SEPARE = "~" ;
	public static final int
		PSEUDO = 0 ;
	
	// Dimensions de l'arène
	public static final int
		H_ARENE = 600,
		L_ARENE = 800,
		H_CHAT = 200,
		H_SAISIE = 25,
		MARGE = 5; // elle va servir pour les écarts entre différents objets
	
	// Taille des murs	
	public static final int
		NBMURS = 20,
		H_MUR = 35,
		L_MUR = 34 ;
	
}
