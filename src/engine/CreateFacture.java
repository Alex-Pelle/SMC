package engine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import DAO.Connexion;
import DAO.DaoFacture;

public class CreateFacture {
	
	public static List<Facturable> createFacture(EProjet projetReferant,String nom,Compte compte, String date, String doit, Float remise, Float rabais, Float ristourne, Float prix, Integer quantite, Paiement paiement) throws Exception {
		List<Facturable> sortie = new LinkedList<Facturable>();
		switch(compte) {
		case SALAIRES:
			Integer nbMois = new DaoFacture(Connexion.getConnexion()).countMois(projetReferant.getIdProjet());
			String d = date;
			for(int i = 0; i<nbMois;i++) {
				sortie.add(new ESalaire(projetReferant,"Salaire : "+d,Compte.SALAIRES,d,projetReferant.getEntrepriseBase(),0F,0F,0F,(float)projetReferant.getSalaire(),0,Paiement.AU_COMPTANT_PAR_CHEQUE));	
				d=DateP.oneMonthLater(d);
				d=DateP.toOriginalFormat(d);
			}
			break;
		default : 
			switch(paiement) {
			case A_30J :
				EFacture h = new EFacture(projetReferant,nom,compte,date,doit,remise,rabais,ristourne,prix,quantite,paiement); 
				String dateA30J = DateP.oneMonthLater(date);
				dateA30J=DateP.toOriginalFormat(dateA30J);
				nom+=" 30J ";
				if (projetReferant.getEntrepriseBase().equals(doit)) {
					EFacture f = new EFacture(projetReferant,nom,Compte.FOURNISSEUR,dateA30J,doit,remise,rabais,ristourne,prix,quantite,Paiement.AU_COMPTANT_PAR_CHEQUE);
					if (checkDate(projetReferant,h)) {
						sortie.add(h);
						sortie.add(f);
						sortie.add(new ETVA(projetReferant,nom+" TVA",Compte.TVA_DEDUCTIBLE,date,doit,0F,0F,0F,f.getTVA(),0,Paiement.AU_COMPTANT_PAR_CHEQUE));
					} else {
						throw new DateInvalide("La date minimale de d'insersion d'une facture pour ce projet est le : "+DateP.getFirstDayString(projetReferant.getDatePaiementSalaire()));
					}
				} else {
					EFacture f = new EFacture(projetReferant,nom,Compte.CLIENT,dateA30J,doit,remise,rabais,ristourne,prix,quantite,Paiement.AU_COMPTANT_PAR_CHEQUE);
					if (checkDate(projetReferant,h)) {
						sortie.add(h);
						sortie.add(f);
						sortie.add(new ETVA(projetReferant,nom+" TVA",Compte.TVA_COLLECTEE,date,doit,0F,0F,0F,f.getTVA(),0,Paiement.AU_COMPTANT_PAR_CHEQUE));
					} else {
						throw new DateInvalide("La date minimale de d'insersion d'une facture pour ce projet est le : "+DateP.getFirstDayString(projetReferant.getDatePaiementSalaire()));
					}
					
				}
				break;
			case AU_COMPTANT_PAR_CHEQUE : 
				EFacture f = new EFacture(projetReferant,nom,compte,date,doit,remise,rabais,ristourne,prix,quantite,paiement);
				sortie.add(f);
				if (projetReferant.getEntrepriseBase().equals(doit)) {
					if (checkDate(projetReferant,f)) {
						sortie.add(new ETVA(projetReferant,nom+" TVA",Compte.TVA_DEDUCTIBLE,date,doit,0F,0F,0F,f.getTVA(),0,Paiement.AU_COMPTANT_PAR_CHEQUE));
					}else {
						throw new DateInvalide("La date minimale de d'insersion d'une facture pour ce projet est le : "+DateP.getFirstDayString(projetReferant.getDatePaiementSalaire()));
					}
				} else {
					if (checkDate(projetReferant,f)) {
						sortie.add(new ETVA(projetReferant,nom+" TVA",Compte.TVA_COLLECTEE,date,doit,0F,0F,0F,f.getTVA(),0,Paiement.AU_COMPTANT_PAR_CHEQUE));
					}else {
						throw new DateInvalide("La date minimale de d'insersion d'une facture pour ce projet est le : "+DateP.getFirstDayString(projetReferant.getDatePaiementSalaire()));
					}			
				}
			}
		}
		return sortie;
		
	}
	
	private static boolean checkDate(EProjet p,EFacture f) {
		
		DateP dp = DateP.getLinkedDate(DateP.toOriginalFormat(p.getDatePaiementSalaire()));
		DateP df = DateP.getLinkedDate(f.getDate());
		
		System.out.println(dp.getMois());
		System.out.println(df.getMois());
		System.out.println(dp.getDate());
		System.out.println(dp.getAnnee());
		System.out.println(df.getAnnee());
		
		return ((dp.getMois()<=df.getMois())&&(dp.getAnnee()<=df.getAnnee()));
	}

}
