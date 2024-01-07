package engine;

import java.util.Objects;

public class EFacture {

	private Compte compteSortie;
	private Compte compteEntree;
	private String doit;
	private String date;
	private Float remise;
	private Float rabais;
	private Float ristourne;
	private Float prix;
	private Integer quantite;
	private EProjet projetReferant;
	
	public String getDate() {
		return date;
	}

	private Paiement paiment;
	private String nom;
	

	public EFacture(EProjet projetReferant,String nom,Compte compte, String date, String doit, Float remise, Float rabais, Float ristourne, Float prix, Integer quantite, Paiement paiement) {
		this.projetReferant=projetReferant;
		this.remise=remise;
		this.ristourne=ristourne;
		this.rabais=rabais;
		this.prix=prix;
		this.quantite=quantite;
		this.nom=nom;
		this.compteSortie=compte;
		this.date=date;
		this.doit=doit;
		this.paiment=paiement;
		
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
		Float prixTTC = this.prix*this.quantite;
		prixTTC -= (prixTTC*this.remise/100);
		prixTTC -= (prixTTC*this.rabais/100);
		prixTTC -= (prixTTC*this.ristourne/100);
		prixTTC *= 1.2F;
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

	public Float getRemise() {
		return remise;
	}

	public Float getRabais() {
		return rabais;
	}

	public Float getRistourne() {
		return ristourne;
	}

	public Float getPrix() {
		return prix;
	}

	public Integer getQuantite() {
		return quantite;
	}



	public EProjet getProjetReferant() {
		return projetReferant;
	}



	@Override
	public String toString() {
		return "EFacture [compteSortie=" + compteSortie + ", compteEntree=" + compteEntree + ", doit=" + doit
				+ ", date=" + date + ", remise=" + remise + ", rabais=" + rabais + ", ristourne=" + ristourne
				+ ", prix=" + prix + ", quantite=" + quantite + ", projetReferant=" + projetReferant + ", paiment="
				+ paiment + ", nom=" + nom + "]";
	}



	

}
