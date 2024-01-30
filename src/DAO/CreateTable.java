package DAO;

import java.sql.SQLException;

public class CreateTable {

	public static void main(String[] args) throws SQLException {
		DaoFacture.dropTable(Connexion.getConnexion());
		DaoProjet.dropTable(Connexion.getConnexion());
		DaoProjet.createTable(Connexion.getConnexion());
		DaoFacture.createTable(Connexion.getConnexion());
	}

}
