package engine;

import java.time.LocalDate;
import java.util.Objects;

public class DateP {
	
	private String date;
	
	public DateP(String date) {
		this.date="";
		for (int i=0;i<date.length();i++) {
			if(date.charAt(i)!='/' && date.charAt(i)!='-') {
				this.date+=date.charAt(i);
			}
		}
	}

	public Integer getJour() {
		return Integer.parseInt(date.substring(0, 2));
	}
	
	public Integer getMois() {
		return Integer.parseInt(date.substring(2, 4));
	}
	
	public Integer getAnnee() {
		return Integer.parseInt(date.substring(4, 8));
	}
	
	public LocalDate getLinkedLocalDate() {
		if (!this.isEmpty()) {
			return LocalDate.of(getAnnee(), getMois(), getJour());
		} else {
			return null;
		}
	}
	
	public static DateP getLinkedDate(LocalDate date) {
		if (date==null) {
			return null;
		}
		return new DateP(""+date.getDayOfMonth()+date.getMonthValue()+date.getYear());
	}
	
	public static DateP getLinkedDate(String date) {
		return new DateP(date);
	}
	
	
	
	public boolean isDateValide() {
		
		if (!date.isEmpty() && 
				isNumeric() && 
				Mois.getCorrespondance(date.substring(2, 4))!=Mois.INVALIDE &&
				Mois.getMaxJour(this, Mois.getCorrespondance(date.substring(2, 4)))!=0 &&
				Mois.getMaxJour(this, Mois.getCorrespondance(date.substring(2, 4)))>= Integer.parseInt(date.substring(0, 2))) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isNumeric() {
		for(int i=0;i<this.date.length();i++) {
			if(!Character.isDigit(this.date.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isBissextile() {
		Integer d = Integer.parseInt(this.date.substring(4));
		if ((( d % 4 == 0 ) && ( d % 100 != 0 )) || ( d % 400 == 0 )) {
	        return true;
	    }
	    else {
	    	return false;
	    }  
	}


	public String getDate() {
		return date;
	}
	
	public boolean isEmpty() {
		return (this.date.trim().isEmpty());
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return ""+this.getAnnee()+'-'+this.getMois()+'-'+this.getJour();
	}

	@Override
	public int hashCode() {
		return Objects.hash(date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateP other = (DateP) obj;
		return Objects.equals(date, other.date);
	}
	
	

}
