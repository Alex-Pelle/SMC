package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import engine.EProjet;
import interfesse.PopupDeleteProject;
import interfesse.ProjectInit;

public class CSuppressionProjet implements ActionListener{

	private PopupDeleteProject p;
	private CProjet c;
	private String nomProjetSelectionne;
	
	public CSuppressionProjet(PopupDeleteProject p, CProjet c, String s) {
		this.p = p;
		this.c = c;
		this.nomProjetSelectionne = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("OK")) {
			c.delProjet(nomProjetSelectionne);
			p.dispose();
			c.updateList();
		}
		if(b.getText().equals("Annuler")) {
			p.dispose();
		}
	}

}
