package engine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import DAO.Connexion;
import DAO.DaoProjet;

public class EProjet {

	private Integer idProjet;
	private String nom;
	private String entrepriseBase;
	private Float salaire;
	private Float captial;
	private String datePaiementSalaire;
	private List<EFacture> factures = new LinkedList<>();
	
	
	
	public EProjet(String nom, String entrepriseBase , Float salaire , Float captial , String datePaiementSalaire) {
		this.nom = nom;
		this.entrepriseBase = entrepriseBase;
		this.salaire=salaire;
		this.captial=captial;
		this.datePaiementSalaire=datePaiementSalaire;
	}

	public String getNom() {
		return nom;
	}

	public String getEntrepriseBase() {
		return entrepriseBase;
	}

	public List<EFacture> getFactures() {
		return factures;
	}
	
	public void addFacture(EFacture facture) {
		this.factures.add(facture);
	}
	
	public void removeFacture(EFacture facture) {
		this.factures.remove(facture);
	}

	@Override
	public String toString() {
		return nom;
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

	public void setFactures(List<EFacture> factures) {
		this.factures = factures;
	}

	public Float getSalaire() {
		return salaire;
	}

	public Float getCaptial() {
		return captial;
	}

	public String getDatePaiementSalaire() {
		return datePaiementSalaire;
	}
}
