package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.Connexion;
import DAO.DaoFacture;
import engine.EFacture;
import engine.EProjet;
import interfesse.Facture;
import interfesse.JBB;
import interfesse.PopupDeleteFacture;
import interfesse.PopupDeleteProject;
import interfesse.ProjectInit;
import interfesse.Projet;

public class CInsideProject implements ActionListener , ListSelectionListener {

	private EProjet p;
	private Projet vue;
	private String nomFactureSelectionne;

	public CInsideProject(EProjet p, Projet vue) {
		this.p=p;
		this.vue=vue;
	}

	public EProjet getP() {
		return p;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<?> l = (JList<?>) e.getSource();
		if (l.getSelectedValue()!=null) {
			this.nomFactureSelectionne = l.getSelectedValue().toString();
		}
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("Nouvelle facture")) {
			Facture.main(this,Optional.ofNullable(null));
		} 
		if(b.getText().equals("Supprimer facture") && this.nomFactureSelectionne!=null) {
			PopupDeleteFacture.main(this, nomFactureSelectionne);
		} 
		if(b.getText().equals("Editer facture") && this.nomFactureSelectionne!=null) {
			Facture.main(this,Optional.ofNullable(this.nomFactureSelectionne));
		} 
		if(b.getText().equals("Générer")){
			try {
				JBB.main(new DaoFacture(Connexion.getConnexion()).getByProjectDateOrdered(p.getIdProjet()));
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(b.getText().equals("Fermer")){
			vue.dispose();
		}
	}

	public void addFacture(EFacture f) {
		System.out.println(f.hashCode());
		this.p.addFacture(f);
	}

	public void delFacture(String s) throws Exception {
		this.p.removeFacture(this.getFacture(s));
		
	}

	public EFacture getFacture(String s) {
		for(EFacture f : this.p.getFactures()) {
			if(f.getNom().equals(s))  {
				return f;
			}
		}
		return null;
	}

	public void updateList() throws Exception {
		DefaultListModel<String> lm = new DefaultListModel<String>();
		List<EFacture> list = new DaoFacture(Connexion.getConnexion()).getFacturesByProjet(p.getIdProjet());
		if (list.size()>0) {
			for(EFacture f : list) {
				lm.addElement(f.getNom());
				
			}
		}
		p.setFactures(list);
		vue.getFactures().setModel(lm);
	}
}
