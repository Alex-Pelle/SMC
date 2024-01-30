package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.EJournal;
import engine.EProjet;
import interfesse.JBB;
import engine.EFacture;
import java.util.List;

public class CJBB implements ActionListener{

	
	private JBB vue;
	
	public CJBB(List<EFacture> factures, JBB vue) {
		this.vue=vue;
		this.vue.setTableJournal(EJournal.genenerateJournal(factures));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
