package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import engine.Compte;
import engine.DateP;
import engine.EFacture;
import engine.Paiement;
import interfesse.Facture;

public class CFacture implements ActionListener{

	private CInsideProject c;
	private Facture vue;
	
	public CFacture(CInsideProject c,Facture vue) {
		this.c=c;
		this.vue=vue;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox<?> cb = (JComboBox<?>) e.getSource();
			if (cb.getName().equals("doit")) {
				if(cb.getSelectedItem().equals("Autre")) {
					vue.getEntreprise().setEnabled(true);
				}
				if(cb.getSelectedItem()!="Autre") {
					vue.getEntreprise().setEnabled(false);
				}
			}
			
		} else if(e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Annuler")) {
				vue.dispose();
			}
			if (b.getText().equals("Valider")) {
				if (vue.getNomFacture().getText().isEmpty()) {
					System.out.println("facture vide");
					//TODO fenêtre erreur nom facture
				} 
				else if (vue.getDoit().getSelectedItem().equals("Autre")&&vue.getEntreprise().getText().isEmpty()) {
					//TODO fenêtre erreur choix entreprise 
					System.out.println("entreprise vide");
				} 
				else if (!DateP.getLinkedDate(vue.getDate().getText()).isDateValide()) {
					//TODO fenêtre date invalide
					System.out.println("date vide");
				} 
				else {
					String doit ="";
					if (!vue.getDoit().getSelectedItem().equals("Autre")) {
						doit = (String) vue.getDoit().getSelectedItem();
					} else {
						doit = vue.getEntreprise().getText();
					}
					c.addFacture(new EFacture(
							vue.getNomFacture().getText(),
							Compte.valueOf((String)vue.getComptes().getSelectedItem()),
							vue.getDate().getText(),
							doit,
							(Float)vue.getRemise().getValue(),
							(Float)vue.getRabais().getValue(),
							(Float)vue.getRistourne().getValue(),
							(Float)vue.getPrix().getValue(),
							(Integer)vue.getQte().getValue(),
							Paiement.valueOf((String)vue.getPaiements().getSelectedItem())));
					c.updateList();
					vue.dispose();
				}
			}
		}
		
	}

}
