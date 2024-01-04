package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import engine.EProjet;
import interfesse.Central;
import interfesse.PopupDeleteProject;
import interfesse.ProjectInit;
import interfesse.Projet;

public class CProjet implements ActionListener , ListSelectionListener{
	
	private List<EProjet> projets = new LinkedList<>();
	private Central c;
	private PState etat;
	private String nomProjetSelectionne;
	
	private enum PState {
		DEBUT,
		NOUVEAU,
		PROJET_SELECTIONNE,
		SUPPRIMER;
	}
	
	public CProjet(Central c) {
		this.c = c;
		etat = PState.DEBUT;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if(b.getText().equals("Nouveau projet")) {
			ProjectInit.main(this);
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
	
	public void addProjet(EProjet p) {
		this.projets.add(p);
	}
	
	public void delProjet(String s) {
		for(EProjet p : this.projets) {
			if(p.toString().equals(s))  {
				this.projets.remove(p);
				System.out.println("Projet supprimé avec succès");
			}
		}
	}
	
	public EProjet getProjet(String s) {
		for(EProjet p : this.projets) {
			if(p.toString().equals(s))  {
				return p;
			}
		}
		return null;
	}
	
	public void updateList() {
		DefaultListModel<String> lm = new DefaultListModel<String>();
		for(EProjet p : this.projets) {
			lm.addElement(p.toString());
		}
		c.getListProjet().setModel(lm);
	}

}
