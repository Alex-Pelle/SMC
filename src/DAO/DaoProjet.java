package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import engine.EFacture;
import engine.EProjet;


public class DaoProjet implements Dao<EProjet,Integer>{

	private Connexion c;

	public DaoProjet(Connexion c) {
		this.c = c;
	}

	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Projet("
				+ "   Id_Projet INT, "
				+ "   Nom VARCHAR(50), "
				+ "   Entreprise_Base VARCHAR(50), "
				+ "   PRIMARY KEY(Id_Projet)) ";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Projet' créée avec succès");
		}
	}

	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Projet' supprimée avec succès");
			return deleteTable.execute("drop table Projet");
		}
	}

	@Override
	public List<EProjet> getAll() throws Exception {
		try(Statement getAll = c.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Projet");
			List<EProjet> sortie = new ArrayList<>();
			while(resultat.next()) {
				EProjet projet = new EProjet(
						resultat.getString("Nom"),
						resultat.getString("Entreprise_Base")
						);
				projet.setIdProjet(resultat.getInt("Id_Projet"));
				sortie.add(projet);
			}
			return sortie;
		}
	}

	@Override
	public Optional<EProjet> getById(Integer... id) throws Exception {
		try(PreparedStatement getById = c.getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Projet"
						+ "WHERE Id_Projet = ?")){
			getById.setInt(1, id[0]);
			ResultSet resultat = getById.executeQuery();
			EProjet projet = null;
			if(resultat.next()) {
				projet = new EProjet(
						resultat.getString("Nom"),
						resultat.getString("Entreprise_Base")
						);
				projet.setIdProjet(resultat.getInt("Id_Projet"));
			}
			return Optional.ofNullable(projet);
		}
	}

	@Override
	public boolean add(EProjet value) throws Exception {
		try(PreparedStatement add = c.getConnection().prepareStatement(""
				+ "INSERT INTO Projet (Id_Projet,Nom,Entreprise_Base)"
				+ "values (?,?,?)")){
			add.setInt(1, value.getIdProjet());
			add.setString(2, value.getNom());
			add.setString(3, value.getEntrepriseBase());
			return add.execute();
		}
	}

	@Override
	public boolean update(EProjet value) throws Exception {
		try(PreparedStatement update = c.getConnection().prepareStatement(
				"UPDATE Projet SET "
						+ "Nom = ? ,"
						+"Entreprise_Base = ? "
						+"WHERE Id_Projet = ?")){
			update.setString(1, value.getNom());
			update.setString(2, value.getEntrepriseBase());
			update.setInt(3, value.getIdProjet());
			return update.execute();
		}
	}

	@Override
	public boolean delete(Integer... value) throws Exception {
		try (PreparedStatement delete = c.getConnection().prepareStatement(
				"DELETE FROM Projet where Id_Projet= ?")) {
			delete.setInt(1, value[0]);
			List<EFacture> factures = new DaoFacture(c).getFacturesByProjet(value[0]);
			for(EFacture f : factures) {
				new DaoFacture(c).delete(f.getProjetReferant().getIdProjet(),f.getNom());
			}
			return delete.execute();
		}
	}

	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Projet_______________________" + "\n";
		List<EProjet> l = this.getAll();
		for(EProjet a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}
	
	public Integer getLastId() throws SQLException {
		try(PreparedStatement getLastId = c.getConnection().prepareStatement(
				"SELECT Id_Projet "
               + "FROM Projet "
               + "ORDER BY Id_Projet DESC "
               + "FETCH FIRST 1 ROW ONLY")) {
			ResultSet resultat = getLastId.executeQuery();
			Integer sortie = null;
			if (resultat.next()) {
				sortie = resultat.getInt("Id_Projet");
			}
			return sortie;
		}
	}

}
