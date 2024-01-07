package engine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

import DAO.Connexion;
import DAO.DaoProjet;

public class EProjet {

	private Integer idProjet;
	private String nom;
	private String entrepriseBase;
	private LinkedList<EFacture> factures = new LinkedList<>();
	
	public EProjet(String nom, String entrepriseBase) throws SQLException {
		this(new DaoProjet(Connexion.getConnexion()).getLastId(),nom,entrepriseBase);
	}
	
	private EProjet(Integer id,String nom, String entrepriseBase) {
		this.nom = nom;
		this.entrepriseBase = entrepriseBase;
		if (id==null) {
			this.idProjet=0;
		} else {
			this.idProjet = id+1;
		}
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

	public Integer getIdProjet() {
		return idProjet;
	}

	public void setIdProjet(Integer idProjet) {
		this.idProjet = idProjet;
	}

	@Override
	public int hashCode() {
		return Objects.hash(entrepriseBase, nom);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EProjet other = (EProjet) obj;
		return Objects.equals(entrepriseBase, other.entrepriseBase) && Objects.equals(nom, other.nom);
	}
}
