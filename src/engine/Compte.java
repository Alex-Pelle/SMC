package engine;

public enum Compte {
	

	CAPITAL(101,"CAPITAL",Classe.CLASSE_1),
	
	TERRAIN(211,"TERRAIN",Classe.CLASSE_2),
	CONSTRUCTION(213,"CONSTRUCTION",Classe.CLASSE_2),
	INSTALLATION_TECHNIQUE(215,"INSTALLATION_TECHNIQUE",Classe.CLASSE_2),
	MATERIEL_DE_TRANSPORT(2182,"MATERIEL_DE_TRANSPORT",Classe.CLASSE_2),
	MATERIEL_DE_BUREAU_ET_INFORMATIQUE(2183,"MATERIEL_DE_BUREAU_ET_INFORMATIQUE",Classe.CLASSE_2),
	
	FOURNISSEUR(401,"FOURNISSEUR",Classe.CLASSE_4),
	CLIENT(411,"CLIENT",Classe.CLASSE_4),
	
	CAISSE(53,"CAISSE",Classe.CLASSE_5),
	BANQUE(512,"BANQUE",Classe.CLASSE_5),
	
	IMPOTS_ET_TAXES(63,"IMPOTS_ET_TAXES",Classe.CLASSE_6),
	CHARGE_DE_PERSONNEL(64,"CHARGES_DE_PERSONNEL",Classe.CLASSE_6),
	ACHAT_DE_MATIERES_PREMIERE(601,"ACHAT_DE_MATIERES_PREMIERE",Classe.CLASSE_6),
	ACHAT_DE_MARCHANDISES(607,"ACHAT_DE_MARCHANDISES",Classe.CLASSE_6),
	LOCATION(613,"LOCATION",Classe.CLASSE_6),
	ENTRETIEN_ET_REPARATIONS(615,"ENTRETIEN_ET_REPARATIONS",Classe.CLASSE_6),
	FOURNITURES_DE_BUREAU(60225,"FOURNITURES_DE_BUREAU",Classe.CLASSE_6),
	
	VENTES_DE_PRODUITS_FINIS(701,"VENTES_DE_PRODUITS_FINIS",Classe.CLASSE_7),
	VENTES_DE_MARCHANDISE(707,"VENTES_DE_MARCHANDISE",Classe.CLASSE_7);
	
	private Integer numero;
	private String intitule;
	private Classe classe;

	private Compte(Integer i, String string, Classe classe) {
		this.numero=i;
		this.intitule=string;
		this.classe=classe;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getIntitule() {
		return intitule;
	}

	public Classe getClasse() {
		return classe;
	}
}
