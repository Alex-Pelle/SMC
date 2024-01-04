package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import engine.EProjet;
import interfesse.JBB;

public class CJournal implements ActionListener {
	
	private JBB j;
	private EProjet p;
	
	public CJournal(EProjet p, JBB j) {
		this.j = j;
		this.p = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
