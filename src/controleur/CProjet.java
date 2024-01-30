package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import DAO.Connexion;
import DAO.DaoProjet;
import engine.EProjet;
import interfesse.Central;
import interfesse.PopupDeleteProject;
import interfesse.ProjectInit;
import interfesse.Projet;

public class CProjet implements ActionListener , ListSelectionListener{
	
	private List<EProjet> projets = new LinkedList<>();
	private Central c;
	private String nomProjetSelectionne;
	
	
	public CProjet(Central c) throws Exception {
		this.c = c;
		projets = new DaoProjet(Connexion.getConnexion()).getAll();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("Nouveau projet")) {
			ProjectInit.main(this);;
		}
		if(b.getText().equals("Supprimer projet") && this.nomProjetSelectionne!=null) {
			PopupDeleteProject.main(this, nomProjetSelectionne);
		} 
		if(b.getText().equals("Editer projet") && this.nomProjetSelectionne!=null) {
			Projet.main(this.getProjet(nomProjetSelectionne));
		} 
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		JList<?> l = (JList<?>) e.getSource();
		if (l.getSelectedValue()!=null) {
			this.nomProjetSelectionne = l.getSelectedValue().toString();
		}
		
	}
	
	public void addProjet(EProjet p) throws Exception {
		if (this.projets.size()>0) {
			Iterator<EProjet> it = this.projets.iterator();
			boolean add = true;
			while(it.hasNext()) {
				if (it.next().equals(p)) {
					add=false;
				}
			}
			if (add) {
				this.projets.add(p);
				new DaoProjet(Connexion.getConnexion()).add(p);
			}
		} else {
			this.projets.add(p);
			new DaoProjet(Connexion.getConnexion()).add(p);
		}
		
	}
	
	public void delProjet(String s) throws Exception {
		this.projets.remove(this.getProjet(s));
		new DaoProjet(Connexion.getConnexion()).delete(this.getProjet(s).getIdProjet());
	}
	
	public EProjet getProjet(String s) {
		for(EProjet p : this.projets) {
			if(p.toString().equals(s))  {
				return p;
			}
		}
		return null;
	}
	
	public void updateList() throws Exception {
		DefaultListModel<String> lm = new DefaultListModel<String>();
		List<EProjet> list = new DaoProjet(Connexion.getConnexion()).getAll();
		if (list.size()>0) {
			for(EProjet p : list) {
				lm.addElement(p.toString());
				this.projets.add(p);
			}
		}
		c.getListProjet().setModel(lm);
	}

}
