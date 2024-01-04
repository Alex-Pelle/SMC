package engine;

import java.util.HashMap;
import java.util.LinkedList;

public class EProjet {

	private String nom;
	private String entrepriseBase;
	private LinkedList<EFacture> factures = new LinkedList<>();
	
	public EProjet(String nom, String entrepriseBase) {
		this.nom = nom;
		this.entrepriseBase = entrepriseBase;
	}

	public String getNom() {
		return nom;
	}

	public String getEntrepriseBase() {
		return entrepriseBase;
	}

	public LinkedList<EFacture> getFactures() {
		return factures;
	}
	
	public void addFacture(EFacture facture) {
		if(facture.getDoit().equals(this.entrepriseBase)) {
			setPaiementClient(facture);
		} else {
			setPaiementFournisseur(facture);
		}
		this.factures.add(facture);
	}
	
	public void removeFacture(EFacture facture) {
		this.factures.remove(facture);
	}

	@Override
	public String toString() {
		return nom;
	}

	private void setPaiementFournisseur(EFacture facture) {
		switch(facture.getPaiment()) {
		case A_30J : 
			facture.setCompteEntree(Compte.FOURNISSEUR);
			break;
		case AU_COMPTANT_PAR_CHEQUE : 
			facture.setCompteEntree(Compte.BANQUE);
			break;
		default:
			break;
		}
	}

	private void setPaiementClient(EFacture facture) {
		switch(facture.getPaiment()) {
		case A_30J : 
			facture.setCompteEntree(Compte.CLIENT);
			break;
		case AU_COMPTANT_PAR_CHEQUE : 
			facture.setCompteEntree(Compte.BANQUE);
			break;
		default:
			break;
		}
	}
}
