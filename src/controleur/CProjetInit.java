package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

import DAO.Connexion;
import DAO.DaoProjet;
import engine.DateP;
import engine.EProjet;
import interfesse.Central;
import interfesse.EntrepriseInvalide;
import interfesse.ErreurDate;
import interfesse.ErreurNomProjet;
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
			if (p.getNomProjet().getText().isEmpty()) {
				ErreurNomProjet.main(null);
			} else if (p.getEntrepriseBase().getText().isEmpty()) {
				EntrepriseInvalide.main(null);
			} else if(!DateP.getLinkedDate(p.getDate().getText()).isDateValide()) {
				ErreurDate.main(null);
			} else {
				try {
					EProjet projet = new EProjet(p.getNomProjet().getText(), p.getEntrepriseBase().getText(), (Float)p.getCapital().getValue(), (Float)p.getSalaires().getValue(),p.getDate().getText());
					c.addProjet(projet);
					c.updateList();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				p.dispose();
			}
			
		}
		if(b.getText().equals("Annuler")) {
			p.dispose();
		}
	}
}
