package engine;

public abstract class AbstractFacture implements Comparable{
	
	
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
	private Paiement paiment;
	private String nom;

	public AbstractFacture(EProjet projetReferant,String nom,Compte compte, String date, String doit, Float remise, Float rabais, Float ristourne, Float prix, Integer quantite, Paiement paiement) {
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
		
		if(this.doit.equals(this.projetReferant.getEntrepriseBase())) {
			setPaiementFournisseur();
		} else {
			setPaiementClient();
		}
		
	}
	
	private void setPaiementFournisseur() {
		switch(this.paiment) {
		case A_30J : 
			this.compteEntree = Compte.FOURNISSEUR;
			break;
		case AU_COMPTANT_PAR_CHEQUE : 
			this.compteEntree = Compte.BANQUE;
			break;
		default:
			break;
		}
	}

	private void setPaiementClient() {
		switch(this.paiment) {
		case A_30J : 
			this.compteEntree = Compte.CLIENT;
			break;
		case AU_COMPTANT_PAR_CHEQUE : 
			this.compteEntree = Compte.BANQUE;
			break;
		default:
			break;
		}
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
	
	public Float getPrixHT() {
		Float prixHT = this.prix*this.quantite;
		prixHT -= (prixHT*this.remise/100);
		prixHT -= (prixHT*this.rabais/100);
		prixHT -= (prixHT*this.ristourne/100);
		return prixHT;
	}
	
	public Float getTVA() {
		return this.getPrixTTC()-this.getPrixHT();
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

	public String getDate() {
		return date;
	}

	public EProjet getProjetReferant() {
		return projetReferant;
	}
}
