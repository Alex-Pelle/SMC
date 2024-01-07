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

import engine.EFacture;
import engine.EProjet;
import interfesse.Facture;
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

		}
		if(b.getText().equals("Fermer")){
			vue.dispose();
		}
	}

	public void addFacture(EFacture f) {
		this.p.addFacture(f);
	}

	public void delFacture(String s) {
		for(EFacture f : this.p.getFactures()) {
			if(f.getNom().equals(s))  {
				this.p.getFactures().remove(f);
				System.out.println("Projet supprimé avec succès");
			}
		}
	}

	public EFacture getFacture(String s) {
		for(EFacture f : this.p.getFactures()) {
			if(f.getNom().equals(s))  {
				return f;
			}
		}
		return null;
	}

	public void updateList() {
		DefaultListModel<String> lm = new DefaultListModel<String>();
		if (this.p.getFactures().size()>0) {
			for(EFacture f : this.p.getFactures()) {
				lm.addElement(f.getNom());
			}
		}
		vue.getFactures().setModel(lm);


	}



}
