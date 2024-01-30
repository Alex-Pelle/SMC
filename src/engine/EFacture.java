package engine;

import java.util.Objects;

public class EFacture extends AbstractFacture{

	public EFacture() {
		super(getProjetReferant(),getNom(),getCompteSortie(), getDate(), getDoit(), getRemise(), getRabais(), getRistourne(), getPrix(), getQuantite(), getPaiment());
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}