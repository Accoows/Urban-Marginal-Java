package modele;

public abstract class Objet {
	
	protected Integer posX;
	protected Integer posY;
	protected Label label;
	
	/**
	 * contrôle si l'objet actuel touche l'objet passé en paramètre
	 * @param objet
	 * @return vrai si les 2 objets se touchent
	 */
	public boolean toucheObjet(Objet objet) {
		if (objet.label == null) {
			return false;
		} else {
			if (objet.label.getJLabel() == null) {
				return false;
			} else {
				int l_obj = objet.label.getJLabel().getWidth();
				int h_obj = objet.label.getJLabel().getHeight();
				int l_this = this.label.getJLabel().getWidth();
				int h_this = this.label.getJLabel().getHeight();
				return(!((this.posX+l_this<objet.posX ||
						this.posX>objet.posX+l_obj) ||
						(this.posY+h_this<objet.posY ||
						this.posY>objet.posY+h_obj)));
			}
		}
	}
	
	//--- getter posX ---
	public Integer getPosX(){
		return posX;
	}
	
	//--- getter posY ---
	public Integer getPosY(){
		return posY;
	}
	
	//--- getter label ---
	public Label getLabel(){
		return label;
	}

}
