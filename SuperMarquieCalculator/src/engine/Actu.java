package engine;

import javax.swing.JTable;

public class Actu {

	
	private int echeance;
	private JTable table;
	private float actualiser;
	private float taux;
	
	
	
	public Actu(int echeance, JTable table, float actualiser,float taux) {
		
		this.echeance=-echeance;
		this.table=table;
		this.actualiser=actualiser;
		this.taux = taux;
	}
	
	public float valeurExt() {
		float f=0f;
		for(int i=0;i<this.table.getRowCount();i++) {
			if (this.table.getValueAt(i, 3)!=null) {
				Text t = new Text(""+this.table.getValueAt(i, 3));
				f+=t.toFloat()*((float)Math.pow(1+(this.taux/100),this.echeance));
			}
		}
		return f;
	}

	// valeur à actualiser unique
	public float somme() {
		float f = 0f;
		for(int row = 0;row<this.table.getRowCount();row++) {
			if (this.table.getValueAt(row, 2)!=null) {
				f += (Float)this.table.getValueAt(row, 2);
			}
		}
		return f+valeurExt();
		
	}
	
	public float valeurActualisee(float f,float t) {
		return f*((float)Math.pow(1+(this.taux/100),-t));
	}
	
	public void fillTableVU() {
		int ech = this.echeance;
		for (int row = 0 ; row<-this.echeance;row++) {
			for (int col = 0 ; col<this.table.getColumnCount();col++) {
				switch (col) {
				case 0:
					this.table.setValueAt("N"+ech, row, col);
					
					break;
				case 1:
					this.table.setValueAt(this.actualiser, row, col);
					break;
				case 2:
					this.table.setValueAt(valeurActualisee((Float)this.table.getValueAt(row, col-1),ech), row, col);
					ech++;
					break;
				
				}
				
			}
		}
	}
	
	//valeur à actualiser non unique
	
	
	public void fillTableVM() {
		int ech = this.echeance;
		for (int row = 0 ; row<-this.echeance;row++) {
			for (int col = 0 ; col<this.table.getColumnCount();col++) {
				switch (col) {
				case 0:
					this.table.setValueAt("N"+ech, row, col);
					
					break;
				case 2:
					this.table.setValueAt(valeurActualisee((Float)this.table.getValueAt(row, col-1),ech), row, col);
					ech++;
					break;
				
				}
				
			}
		}
	}
	
	public void reset() {
		for (int row = 0 ; row<this.table.getRowCount();row++) {
			for (int col = 0 ; col<this.table.getColumnCount();col++) { 
				this.table.setValueAt(null, row, col);
			}
		}
	}
}
