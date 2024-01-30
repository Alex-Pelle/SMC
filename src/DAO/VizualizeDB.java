package DAO;

public class VizualizeDB {

	public static void main(String[] args) throws Exception {
		System.out.println(new DaoProjet(Connexion.getConnexion()).visualizeTable());
		System.out.println(new DaoFacture(Connexion.getConnexion()).visualizeTable());

	}

}
