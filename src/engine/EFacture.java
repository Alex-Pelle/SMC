package engine;

import java.util.Objects;

public class EFacture {

	private Compte compteSortie;
	private Compte compteEntree;
	private Float prixTTC;
	private String doit;
	private String date;
	
	public String getDate() {
		return date;
	}

	private Paiement paiment;
	private String nom;
	

	public EFacture(String nom,Compte compte, String date, String doit, Float remise, Float rabais, Float ristourne, Float prix, Integer quantite, Paiement paiement) {
		this.nom=nom;
		this.compteSortie=compte;
		this.date=date;
		this.doit=doit;
		
		this.prixTTC = prix*quantite;
		this.prixTTC -= (this.prixTTC*remise/100);
		this.prixTTC -= (this.prixTTC*rabais/100);
		this.prixTTC -= (this.prixTTC*ristourne/100);
		this.prixTTC /= 1.2F;
		
		this.paiment=paiement;
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(compteEntree, compteSortie, date, doit, nom, paiment, prixTTC);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EFacture other = (EFacture) obj;
		return compteEntree == other.compteEntree && compteSortie == other.compteSortie
				&& Objects.equals(date, other.date) && Objects.equals(doit, other.doit)
				&& Objects.equals(nom, other.nom) && paiment == other.paiment && Objects.equals(prixTTC, other.prixTTC);
	}

	public Compte getCompteEntree() {
		return compteEntree;
	}

	public void setCompteEntree(Compte compteEntree) {
		this.compteEntree = compteEntree;
	}

	public Compte getCompteSortie() {
		return compteSortie;
	}

	public Float getPrixTTC() {
		return prixTTC;
	}

	public String getDoit() {
		return doit;
	}

	

	public Paiement getPaiment() {
		return paiment;
	}

	public String getNom() {
		return nom;
	}
}
