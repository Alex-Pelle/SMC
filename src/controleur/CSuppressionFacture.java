package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import DAO.Connexion;
import DAO.DaoFacture;
import engine.EProjet;
import interfesse.PopupDeleteFacture;
import interfesse.PopupDeleteProject;
import interfesse.ProjectInit;

public class CSuppressionFacture implements ActionListener{

	private PopupDeleteFacture p;
	private CInsideProject c;
	private String nomFactureSelectionne;
	
	public CSuppressionFacture(PopupDeleteFacture p, CInsideProject c, String s) {
		this.p = p;
		this.c = c;
		this.nomFactureSelectionne = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("OK")) {
			try {
				new DaoFacture(Connexion.getConnexion()).delete(c.getFacture(this.nomFactureSelectionne).getProjetReferant().getIdProjet(),c.getFacture(this.nomFactureSelectionne).getNom());
				c.delFacture(nomFactureSelectionne);
				c.updateList();
				p.dispose();
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
