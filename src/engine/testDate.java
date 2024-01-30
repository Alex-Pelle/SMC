package engine;

import java.time.LocalDate;

import DAO.Connexion;
import DAO.DaoFacture;

public class testDate {

	public static void main(String[] args) {
		String d = "30/01/2024";
		d=DateP.oneMonthLater(d);
		d=DateP.toOriginalFormat(d);
		System.out.println(d);
		System.out.println(DateP.getLinkedDate(d).getAnnee());
		System.out.println(new CustomDate(DateP.getLinkedDate(d).getAnnee(),DateP.getLinkedDate(d).getMois(),DateP.getLinkedDate(d).getJour()).toSQL().toString());

		

	}

}
