package engine;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EJournal {

	public static JTable genenerateJournal(EProjet p) {
		ArrayList<Object[]> a = new ArrayList<>();
		for(EFacture f : p.getFactures()) {
			Object[] date = {null, null, null, f.getDate(), null, null, null};
			Object[] cp1 = {f.getCompteSortie().getNumero(), null, f.getCompteSortie().getIntitule(), null, null, f.getPrixTTC(), null};
			Object[] cp2 = {f.getCompteEntree().getNumero(), null, f.getCompteEntree().getIntitule(), null, null, f.getPrixTTC(), null};
			Object[] transition = {null, null, null, null, null, null, null};
			a.add(date);
			a.add(cp1);
			a.add(cp2);
			a.add(transition);
		}
		
		Object[][] modele = new Object[7][a.size()];
		for(int i = 0 ; i<a.size();i++) {
			modele[0][i] = a.get(i)[0];
			modele[1][i] = a.get(i)[1];
			modele[2][i] = a.get(i)[2];
			modele[3][i] = a.get(i)[3];
			modele[4][i] = a.get(i)[4];
			modele[5][i] = a.get(i)[5];
			modele[6][i] = a.get(i)[6];
		}
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				modele,
				new String[] {
					"Emploi", "Ressource", "Libelle", "Date", "Libelle", "Emploi", "Ressource"
				}
			));
		return table;
	
	}
	
}
