package modele;

import java.io.Serializable;

import javax.swing.JLabel;

public class Label implements Serializable {
	
	private static Integer nbLabel;
	private Integer numLabel;
	private JLabel jLabel;
	
	public Label(Integer numLabel, JLabel jLabel){
		this.numLabel = numLabel;
		this.jLabel = jLabel;
	}
	
	public Integer getNumLabel () {
		return numLabel ;
	}
		
	public JLabel getJLabel () {
		return jLabel ;
	}
	
	public static Integer getNbLabel() {
		return nbLabel;
	}
	
	public static void setNbLabel(Integer nbLabel) {
		Label.nbLabel = nbLabel;
	}

}
