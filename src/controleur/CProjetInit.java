package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import engine.EProjet;
import interfesse.Central;
import interfesse.ProjectInit;

public class CProjetInit implements ActionListener{

	private ProjectInit p;
	private CProjet c;
	
	public CProjetInit(ProjectInit p, CProjet c) {
		this.p = p;
		this.c = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("OK")) {
			c.addProjet(new EProjet(p.getNomProjet().getText(), p.getEntrepriseBase().getText()));
			c.updateList();
			p.dispose();
		}
		if(b.getText().equals("Annuler")) {
			p.dispose();
		}
	}
}
