package engine;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import engine.EFacture;
import java.util.List;

public class EJournal {
	
	static class MyTableCellRenderer extends DefaultTableCellRenderer {
		
		
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Vérifier si le texte de la cellule est "Noir"
            if ("".equals(value)) {
                // Remplir la cellule avec la couleur noire
                cellComponent.setBackground(Color.BLACK);
                cellComponent.setForeground(Color.WHITE); // Définir la couleur du texte en blanc pour contraster
            } else {
                // Réinitialiser les couleurs pour les autres cellules
                cellComponent.setBackground(table.getBackground());
                cellComponent.setForeground(table.getForeground());
            }

            return cellComponent;
        }
    }
	
	
	public static void achatVente(EFacture f,ETVA tva, ArrayList<Object[]> a) {
		Object[] date = {null, null, null, f.getDate(), null, null, null};
		Object[] cp1 = {f.getCompteSortie().getNumero(), null, f.getCompteSortie().getIntitule(), null, null, f.getPrixHT(), null};
		Object[] cp2 = {null, f.getCompteEntree().getNumero(), null, null, f.getCompteEntree().getIntitule(), null, f.getPrixTTC()};
		Object[] cp3 = {tva.getCompteSortie().getNumero(),null,null,tva.getCompteSortie().getIntitule(),null,tva.getPrix(),null};
		Object[] transition = {"" , "", "", "", "", "", ""};
		a.add(date);
		a.add(cp1);
		a.add(cp3);
		a.add(cp2);
		a.add(transition);
	}
	
	public static void autre(EFacture f, ArrayList<Object[]> a) {
		Object[] date = {null, null, null, f.getDate(), null, null, null};
		Object[] cp1 = {f.getCompteSortie().getNumero(), null, f.getCompteSortie().getIntitule(), null, null, f.getPrixTTC(), null};
		Object[] cp2 = {null, f.getCompteEntree().getNumero(), null, null, f.getCompteEntree().getIntitule(), null, f.getPrixTTC()};
		Object[] transition = {"" , "", "", "", "", "", ""};
		a.add(date);
		a.add(cp1);
		a.add(cp2);
		a.add(transition);
	}
	

	public static JTable genenerateJournal(List<EFacture> factures) {
		ArrayList<Object[]> a = new ArrayList<>();
		Iterator<EFacture> it = factures.iterator();
		while(it.hasNext()) {
			
			Object fact = it.next();
			System.out.println(fact.getClass());
			if (fact instanceof ETVA) {
				ETVA f = (ETVA)fact;
				EJournal.achatVente(it.next(), f, a);
			} else {
				EJournal.autre((EFacture)fact, a);
			}

		}
			
		
		
		Object[][] modele = new Object[a.size()][7];
		for(int i = 0 ; i<a.size();i++) {
			modele[i][0] = a.get(i)[0];
			modele[i][1] = a.get(i)[1];
			modele[i][2] = a.get(i)[2];
			modele[i][3] = a.get(i)[3];
			modele[i][4] = a.get(i)[4];
			modele[i][5] = a.get(i)[5];
			modele[i][6] = a.get(i)[6];
		}
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
				modele,
				new String[] {
					"Emploi", "Ressource", "Libelle", "Date", "Libelle", "Emploi", "Ressource"
				}
			));
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
		table.getColumnModel().getColumn(1).setPreferredWidth(10);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(10);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(10);
		table.getColumnModel().getColumn(6).setPreferredWidth(10);
		
		
		
		table.setDefaultRenderer(Object.class, new MyTableCellRenderer());

		
		return table;
	
	}
	
}
