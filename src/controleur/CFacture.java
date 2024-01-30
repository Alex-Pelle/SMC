package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

import DAO.Connexion;
import DAO.DaoFacture;
import engine.Compte;
import engine.CreateFacture;
import engine.DateInvalide;
import engine.DateP;
import engine.EFacture;
import engine.Paiement;
import interfesse.EntrepriseInvalide;
import interfesse.ErreurDate;
import interfesse.ErreurDateFactureInvalide;
import interfesse.ErreurFacture;
import interfesse.Facture;

public class CFacture implements ActionListener{

	private CInsideProject c;
	private Facture vue;
	private Optional<String> nomFacture;
	
	public CFacture(CInsideProject c,Facture vue, Optional<String> nomFacture) {
		this.c=c;
		this.vue=vue;
		this.nomFacture=nomFacture;
	}
	
	public void editer() {
		if(nomFacture.isPresent()) {
			EFacture f = c.getFacture(nomFacture.get());
			vue.getNomFacture().setText(f.getNom());
			vue.getNomFacture().setEnabled(false);
			if(f.getDoit()!=c.getP().getEntrepriseBase()) {
				vue.getDoit().setSelectedItem("Autre");
				vue.getEntreprise().setEnabled(true);
				vue.getEntreprise().setText(f.getDoit());
			} else {
				vue.getDoit().setSelectedItem(f.getDoit());
				vue.getEntreprise().setEnabled(false);
			}
			vue.getComptes().setSelectedItem(f.getCompteSortie().name());
			vue.getQte().setValue(f.getQuantite());
			vue.getPrix().setValue(f.getPrix());
			vue.getRemise().setValue(f.getRemise());
			vue.getRabais().setValue(f.getRabais());
			vue.getRistourne().setValue(f.getRistourne());
			vue.getPaiements().setSelectedItem(f.getPaiment().name());
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox) {
			JComboBox<?> cb = (JComboBox<?>) e.getSource();
			if (cb.getName().equals("doit")) {
				if(cb.getSelectedItem().equals("Autre")) {
					vue.getEntreprise().setEnabled(true);
					vue.fillComboBox((String) cb.getSelectedItem());
				}
				if(cb.getSelectedItem()!="Autre") {
					vue.getEntreprise().setEnabled(false);
					vue.fillComboBox((String) cb.getSelectedItem());
				}
			}

		} else if(e.getSource() instanceof JButton) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("Annuler")) {
				vue.dispose();
			}
			if (b.getText().equals("Valider")) {
				if (vue.getNomFacture().getText().isEmpty()) {
					ErreurFacture.main(null);
				} 
				else if (vue.getDoit().getSelectedItem().equals("Autre")&&vue.getEntreprise().getText().isEmpty()) {
					EntrepriseInvalide.main(null);
				} 
				else if (!DateP.getLinkedDate(vue.getDate().getText()).isDateValide()) {
					ErreurDate.main(null);
				} 
				else {
					try {
						String doit ="";
						if (!vue.getDoit().getSelectedItem().equals("Autre")) {
							doit = (String) vue.getDoit().getSelectedItem();
						} else {
							doit = vue.getEntreprise().getText();
						}
						List<EFacture> factures = CreateFacture.createFacture(c.getP(), vue.getNomFacture().getText(), Compte.valueOf((String)vue.getComptes().getSelectedItem()), vue.getDate().getText(), doit, (Float)vue.getRemise().getValue(), (Float)vue.getRabais().getValue(), (Float)vue.getRistourne().getValue(), (Float)vue.getPrix().getValue(), (Integer)vue.getQte().getValue(), Paiement.valueOf((String)vue.getPaiements().getSelectedItem()));
						for(EFacture f : factures) {
							new DaoFacture(Connexion.getConnexion()).add(f);
							c.addFacture(f);
							c.updateList();
						}
						vue.dispose();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						if (e1 instanceof DateInvalide) {
							ErreurDateFactureInvalide.main(e1.getMessage());
						}
						
					}

					
				}
			}

		} 

	}

}
