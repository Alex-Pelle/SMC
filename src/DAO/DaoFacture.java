package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import engine.Compte;
import engine.CustomDate;
import engine.DateP;
import engine.EFacture;
import engine.Paiement;

public class DaoFacture implements Dao<EFacture, Object> {

	private Connexion c;
	private DaoProjet d;
	
	public DaoFacture(Connexion c) {
		this.c=c;
		this.d = new DaoProjet(c);
	}
	
	public static void createTable(Connexion connexion) throws SQLException {
		String createTableSql = "CREATE TABLE Facture("
				+ "   Id_Projet INT, "
				+ "   Nom VARCHAR(50), "
				+ "   Compte VARCHAR(50), "
				+ "   DateF DATE, "
				+ "   Doit VARCHAR(50), "
				+ "   Remise DECIMAL(15,3), "
				+ "   Rabais DECIMAL(15,3), "
				+ "   Ristourne DECIMAL(15,3), "
				+ "   Prix DECIMAL(15,3), "
				+ "   Quantite INT, "
				+ "   Paiement VARCHAR(50), "
				+ "   PRIMARY KEY(Id_Projet, Nom), "
				+ "   FOREIGN KEY(Id_Projet) REFERENCES Projet(Id_Projet))";

		try (Statement createTable = connexion.getConnection().createStatement()) {
			createTable.execute(createTableSql);
			System.out.println("Table 'Facture' créée avec succès");
		}
	}

	public static boolean dropTable(Connexion connexion) throws SQLException {
		try(Statement deleteTable = connexion.getConnection().createStatement()){
			System.out.println("Table 'Facture' supprimée avec succès");
			return deleteTable.execute("drop table Facture");
		}
	}
	
	@Override
	public List<EFacture> getAll() throws Exception {
		try(Statement getAll = c.getConnection().createStatement()){
			ResultSet resultat = getAll.executeQuery("SELECT * FROM Facture");
			List<EFacture> sortie = new ArrayList<>();
			while(resultat.next()) {
				EFacture facture = new EFacture(
						this.d.getById(resultat.getInt("Id_Projet")).get(),
						resultat.getString("Nom"),
						Compte.valueOf(resultat.getString("Compte")),
						resultat.getDate("DateF").toString(),
						resultat.getString("Doit"),
						resultat.getFloat("Remise"),
						resultat.getFloat("Rabais"),
						resultat.getFloat("Ristourne"),
						resultat.getFloat("Prix"),
						resultat.getInt("Quantite"),
						Paiement.valueOf(resultat.getString("Paiement")
						
						));
				sortie.add(facture);
			}
			return sortie;
		}
	}

	@Override
	public Optional<EFacture> getById(Object... id) throws Exception {
		try(PreparedStatement getById = c.getConnection().prepareStatement(
				"SELECT * "
						+ "FROM Facture"
						+ "WHERE Id_Projet = ?"
						+ "AND Nom = ?")){
			getById.setInt(1, (Integer) id[0]);
			getById.setString(2, (String) id[1]);
			ResultSet resultat = getById.executeQuery();
			EFacture facture = null;
			if(resultat.next()) {
				 facture = new EFacture(
							this.d.getById(resultat.getInt("Id_Projet")).get(),
							resultat.getString("Nom"),
							Compte.valueOf(resultat.getString("Compte")),
							resultat.getDate("DateF").toString(),
							resultat.getString("Doit"),
							resultat.getFloat("Remise"),
							resultat.getFloat("Rabais"),
							resultat.getFloat("Ristourne"),
							resultat.getFloat("Prix"),
							resultat.getInt("Quantite"),
							Paiement.valueOf(resultat.getString("Paiement")
							
							));
			}
			return Optional.ofNullable(facture);
		}
	}

	@Override
	public boolean add(EFacture value) throws Exception {
		try(PreparedStatement add = c.getConnection().prepareStatement(""
				+ "INSERT INTO Projet ("
				+ "Id_Projet, "
				+ "Nom, "
				+ "Compte, "
				+ "DateF, "
				+ "Doit, "
				+ "Remise, "
				+ "Rabais, "
				+ "Ristourne, "
				+ "Prix, "
				+ "Quantite, "
				+ "Paiement)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?)")){
			add.setInt(1, value.getProjetReferant().getIdProjet());
			add.setString(2, value.getNom());
			add.setString(3, value.getCompteSortie().getIntitule());
			add.setTimestamp(4, new CustomDate(DateP.getLinkedDate(value.getDate()).getAnnee(),DateP.getLinkedDate(value.getDate()).getMois(),DateP.getLinkedDate(value.getDate()).getJour()).toSQL());
			add.setString(5, value.getDoit());
			add.setFloat(6, value.getRemise());
			add.setFloat(7, value.getRabais());
			add.setFloat(8, value.getRistourne());
			add.setFloat(9, value.getPrix());
			add.setInt(10, value.getQuantite());
			add.setString(11, value.getPaiment().name());
			return add.execute();
		}
	}

	@Override
	public boolean update(EFacture value) throws Exception {
		try(PreparedStatement add = c.getConnection().prepareStatement(""
				+ "UPDATE Facture SET ("
				+ "Compte = ? , "
				+ "DateF = ? , "
				+ "Doit = ? , "
				+ "Remise = ? , "
				+ "Rabais = ? , "
				+ "Ristourne = ? , "
				+ "Prix = ? , "
				+ "Quantite = ? , "
				+ "Paiement = ? "
				+ "WHERE Id_Projet = ? AND Nom = ?")){
			add.setInt(10, value.getProjetReferant().getIdProjet());
			add.setString(11, value.getNom());
			add.setString(1, value.getCompteSortie().getIntitule());
			add.setTimestamp(2, new CustomDate(DateP.getLinkedDate(value.getDate()).getAnnee(),DateP.getLinkedDate(value.getDate()).getMois(),DateP.getLinkedDate(value.getDate()).getJour()).toSQL());
			add.setString(3, value.getDoit());
			add.setFloat(4, value.getRemise());
			add.setFloat(5, value.getRabais());
			add.setFloat(6, value.getRistourne());
			add.setFloat(7, value.getPrix());
			add.setInt(8, value.getQuantite());
			add.setString(9, value.getPaiment().name());
			return add.execute();
		}
	}

	@Override
	public boolean delete(Object... value) throws Exception {
		try (PreparedStatement delete = c.getConnection().prepareStatement(
				"DELETE FROM Facture where Id_Projet = ? AND Nom = ?")) {
			delete.setInt(1, (Integer) value[0]);
			delete.setString(2, (String)value[1]);
			return delete.execute();
		}
	}

	@Override
	public String visualizeTable() throws Exception {
		String s = "_______________Facture_______________________" + "\n";
		List<EFacture> l = this.getAll();
		for(EFacture a : l) {
			s+=a.toString()+"\n";
		}
		s+="\n\n\n";
		return s;
	}
	
	public List<EFacture> getFacturesByProjet(Integer idProjet) throws Exception {
		try(PreparedStatement getFacturesByProjet = c.getConnection().prepareStatement("SELECT * FROM Facture WHERE Id_Projet = ?")){
			getFacturesByProjet.setInt(1, idProjet);
			ResultSet resultat = getFacturesByProjet.executeQuery();
			List<EFacture> sortie = new ArrayList<>();
			while(resultat.next()) {
				EFacture facture = new EFacture(
						this.d.getById(resultat.getInt("Id_Projet")).get(),
						resultat.getString("Nom"),
						Compte.valueOf(resultat.getString("Compte")),
						resultat.getDate("DateF").toString(),
						resultat.getString("Doit"),
						resultat.getFloat("Remise"),
						resultat.getFloat("Rabais"),
						resultat.getFloat("Ristourne"),
						resultat.getFloat("Prix"),
						resultat.getInt("Quantite"),
						Paiement.valueOf(resultat.getString("Paiement")
						
						));
				sortie.add(facture);
			}
			return sortie;
		}
	}

}
