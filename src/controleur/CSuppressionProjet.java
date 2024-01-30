package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import DAO.Connexion;
import DAO.DaoProjet;
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
			try {
				c.delProjet(nomProjetSelectionne);
				p.dispose();
				c.updateList();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(b.getText().equals("Annuler")) {
			p.dispose();
		}
	}

}
