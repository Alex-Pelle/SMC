package engine;

public enum Mois {

	JANVIER,
	FEVRIER,
	MARS,
	AVRIL,
	MAI,
	JUIN,
	JUILLET,
	AOUT,
	SEPTEMBRE,
	OCTOBRE,
	NOVEMBRE,
	DECEMBRE,
	INVALIDE;
	
	public static Mois getCorrespondance(String mois) {
		Integer m = Integer.parseInt(mois);
		
		switch (m) {
		case 1:
			return Mois.JANVIER;
		case 2:
			return Mois.FEVRIER;
		case 3:
			return Mois.MARS;
		case 4:
			return Mois.AVRIL;
		case 5:
			return Mois.MAI;
		case 6:
			return Mois.JUIN;
		case 7:
			return Mois.JUILLET;
		case 8:
			return Mois.AOUT;
		case 9:
			return Mois.SEPTEMBRE;
		case 10:
			return Mois.OCTOBRE;
		case 11:
			return Mois.NOVEMBRE;
		case 12:
			return Mois.DECEMBRE;
		default :
			return Mois.INVALIDE;
		}
	}
	
	public static int getMaxJour(DateP date, Mois mois) {
		if (mois != Mois.INVALIDE) {
			if (mois == Mois.JANVIER || mois == Mois.MARS || 
					mois == Mois.MAI || mois == Mois.JUILLET ||
					mois == Mois.AOUT || mois == Mois.OCTOBRE ||
					mois == Mois.DECEMBRE) {
				return 31;
			} else if (mois != Mois.FEVRIER) {
				return 30;
			} else if(date.isBissextile()){
				return 29;
			} else {
				return 28;
			}
		} else {
			return 0;
		}
		
		
	}
	
	
}
