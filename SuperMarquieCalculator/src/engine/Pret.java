package engine;

import javax.swing.JTable;

public class Pret {

	private float montant;
	private float montantncst;
	private float indice;
	private float duree;
	private JTable table;
	private int etat;
	private int annee;
	private int mois;
	
	
	public Pret(Text montant, float indice, float duree, JTable table, int etat,int annee,int mois) {
		if (!montant.isNull()) {
			if (!montant.checkMultiplication()) {
				if (montant.isDot()) {
					this.montant = montant.notEntier();
				} else {
					this.montant = montant.stringToFloat();
				}	
			} else {
				this.montant = montant.multiplication();
			}
		}else {
			this.montant = 0f;
		}
		this.montantncst = this.montant;
		this.indice = indice;
		this.duree= duree;
		this.table = table;
		this.etat = etat;
		this.annee = annee;
		this.mois = mois;
		
	}
	
	private boolean isUnderOne(float f) {
		if (f<10) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	// Annuité constantes 
	public float anncst() {
		System.out.println(this.indice);
		return this.montant*
				((this.indice/100)/
						(1-(float)Math.pow((double)((1+(this.indice/100))),-(double)this.duree)));
		
	}
	
	public float amornncst() {
		System.out.println(this.montantncst+"---------------------");
		System.out.println((this.indice/100)*this.montantncst+"-----------------------");
		return this.anncst()-(this.indice/100)*this.montantncst;
	}
	
	public void captitalrestantamor() {
		this.montantncst -= this.amornncst();
	}
	
	
	public void fillTableAnn() {
		System.out.println("couille");
		switch (this.etat) {
			//paiement mensuel annuité cst
			case 0 : 
				for (int row = 0 ; row<this.duree+1;row++) {
					for (int col = 0 ; col<5;col++) {
						switch (col) {
							case 0 : 
								if (!isUnderOne(this.montantncst)) {
									casselescouilles(row, col, 1);
								}
								break;
							case 1 :
								if (row == 0) {
									this.table.setValueAt(this.montant, row, col);
								} else {
									this.captitalrestantamor();
									if (isUnderOne(this.montantncst)) {
										deleteLastLine(row, col);
										if (this.montantncst<0) {
											this.table.setValueAt(this.anncst()+(-1)*this.montantncst, row-1, col+2);
										} else {
											this.table.setValueAt(this.anncst()+this.montantncst, row-1, col+2);
										}
										
									} else {
										this.table.setValueAt(this.getMontantncst(), row, col);
									}
								}
								
								break;
							case 2 :
								if (!this.isUnderOne(this.montantncst)) {
									this.table.setValueAt(this.montantncst*(this.indice/100), row, col);
								}
								break;
							case 3 :
								if (!this.isUnderOne(this.montantncst)) {
									this.table.setValueAt(this.anncst(), row, col);
								}
								break;
							case 4 : 
								if (!this.isUnderOne(this.montantncst)) {
									this.table.setValueAt(this.amornncst(), row, col);
								} else {
									this.table.setValueAt(this.table.getValueAt(row-1, col-3), row-1, col);
									deleteLastLine(row, col);
								}
								
								break;
							
						}
					} 
				}
				break;
			//paiement trimestriel annuité cst
			case 1 :
				for (int row = 0 ; row<this.duree+1;row++) {
					for (int col = 0 ; col<5;col++) {
						switch (col) {
						case 0 : 
							if (!isUnderOne(this.montantncst)) {
								casselescouilles(row, col, 3);
							}
							break;
						case 1 :
							if (row == 0) {
								this.table.setValueAt(this.montant, row, col);
							} else {
								this.captitalrestantamor();
								if (isUnderOne(this.montantncst)) {
									deleteLastLine(row, col);
									if (this.montantncst<0) {
										this.table.setValueAt(this.anncst()+(-1)*this.montantncst, row-1, col+2);
									} else {
										this.table.setValueAt(this.anncst()+this.montantncst, row-1, col+2);
									}
									
								} else {
									this.table.setValueAt(this.getMontantncst(), row, col);
								}
							}
							
							break;
						case 2 :
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.montantncst*(this.indice/100), row, col);
							}
							break;
						case 3 :
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.anncst(), row, col);
							}
							break;
						case 4 : 
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.amornncst(), row, col);
							} else {
								this.table.setValueAt(this.table.getValueAt(row-1, col-3), row-1, col);
								deleteLastLine(row, col);
							}
							
							break;
						
						}
					} 
				}
				break;
			//paiement semestriel annuité cst
			case 2 : 
				for (int row = 0 ; row<this.duree+1;row++) {
					for (int col = 0 ; col<5;col++) {
						switch (col) {
						case 0 : 
							if (!isUnderOne(this.montantncst)) {
								casselescouilles(row, col, 6);
							}
							break;
						case 1 :
							if (row == 0) {
								this.table.setValueAt(this.montant, row, col);
							} else {
								this.captitalrestantamor();
								if (isUnderOne(this.montantncst)) {
									deleteLastLine(row, col);
									if (this.montantncst<0) {
										
										this.table.setValueAt(this.anncst()+(-1)*this.montantncst, row-1, col+2);
									} else {
										this.table.setValueAt(this.anncst()+this.montantncst, row-1, col+2);
									}
									
								} else {
									this.table.setValueAt(this.getMontantncst(), row, col);
								}
							}
							
							break;
						case 2 :
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.montantncst*(this.indice/100), row, col);
							}
							break;
						case 3 :
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.anncst(), row, col);
							}
							break;
						case 4 : 
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.amornncst(), row, col);
							} else {
								this.table.setValueAt(this.table.getValueAt(row-1, col-3), row-1, col);
								deleteLastLine(row, col);
							}
							
							break;
						
							
						}
					} 
				}
				break;
			//paiement annuel annuité cst
			case 3 :
				for (int row = 0 ; row<this.duree+1;row++) {
					for (int col = 0 ; col<5;col++) {
						switch (col) {
						case 0 : 
							if (!isUnderOne(this.montantncst)) {
								casselescouillesleretourdujedi(row, col);
							} 
							break;
						case 1 :
							if (row == 0) {
								this.table.setValueAt(this.montant, row, col);
							} else {
								this.captitalrestantamor();
								if (isUnderOne(this.montantncst)) {
									deleteLastLine(row, col);
									if (this.montantncst<0) {
										this.table.setValueAt(this.anncst()+(-1)*this.montantncst, row-1, col+2);
									} else {
										this.table.setValueAt(this.anncst()+this.montantncst, row-1, col+2);
									}
									
								} else {
									this.table.setValueAt(this.getMontantncst(), row, col);
								}
							}
							
							break;
						case 2 :
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.montantncst*(this.indice/100), row, col);
							}
							break;
						case 3 :
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.anncst(), row, col);
							}
							break;
						case 4 : 
							if (!this.isUnderOne(this.montantncst)) {
								this.table.setValueAt(this.amornncst(), row, col);
							} else {
								this.table.setValueAt(this.table.getValueAt(row-1, col-3), row-1, col);
								deleteLastLine(row, col);
							} 
							
							break;
						
						}
					} 
				}
				break;
		}
		
	}
	// Amortissement constant
	public float amorcst() {
		return this.montant/this.duree;
	}
	
	public float annnncst() {
		return (this.montantncst*(this.indice/100)+this.amorcst());
	}
	
	public void captitalrestantann() {
		this.montantncst -= this.amorcst();
	}
	public float getMontantncst() {
		return montantncst;
	}
	public void reset() {
		for (int row = 0 ; row<25;row++) {
			for (int col = 0 ; col<5;col++) { 
				this.table.setValueAt(null, row, col);
			}
		}
	}
	
	public void fillTableAmor() {
		System.out.println("ouioui");
		switch (this.etat) {
			//paiement mensuel annuité cst
			case 0 : 
				for (int row = 0 ; row<this.duree+1;row++) {
					for (int col = 0 ; col<5;col++) {
						switch (col) {
							case 0 :
								if (!isUnderOne(this.montantncst)) {
									casselescouilles(row, col, 1);
								}
								break;
							case 1 :
								if (row == 0) {
									this.table.setValueAt(this.montant, row, col);
								} else {
									this.captitalrestantann();
									if (isUnderOne(this.montantncst)) {
										deleteLastLine(row, col);
										if (this.montantncst<0) {
											this.table.setValueAt(this.amorcst()+this.montantncst, row-1, col+2);
										} else {
											this.table.setValueAt(this.amorcst()-this.montantncst, row-1, col+2);
										}
										
									} else {
										this.table.setValueAt(this.getMontantncst(), row, col);
									}
									
								}
								break;
							case 2 :
								if (this.montantncst>0) {
									this.table.setValueAt(this.montantncst*(this.indice/100), row, col);
								}
								
								break;
							case 4 :
								if (this.montantncst>0) {
									this.table.setValueAt(this.amorcst(), row, col);
								}
								
								break;
							case 3 : 
								if (this.montantncst>0) {
									this.table.setValueAt(this.annnncst(), row, col);
								} else {
									deleteLastLine(row, col);
								}
								break;
						}
					} 
				}
				break;
			//paiement trimestriel annuité cst
			case 1 :
				for (int row = 0 ; row<this.duree+1;row++) {
					for (int col = 0 ; col<5;col++) {
						switch (col) {
							case 0 : 
								if (!isUnderOne(this.montantncst)) {
									casselescouilles(row, col, 3);
								}
								break;
							case 1 :
								if (row == 0) {
									this.table.setValueAt(this.montant, row, col);
								} else {
									this.captitalrestantann();
									if (isUnderOne(this.montantncst)) {
										deleteLastLine(row, col);
										if (this.montantncst<0) {
											this.table.setValueAt(this.amorcst()+this.montantncst, row-1, col+2);
										} else {
											this.table.setValueAt(this.amorcst()-this.montantncst, row-1, col+2);
										}
										
									} else {
										this.table.setValueAt(this.getMontantncst(), row, col);
									}
									
								}
								break;
							case 2 :
								if (this.montantncst>0) {
									this.table.setValueAt(this.montantncst*(this.indice/100), row, col);
								} 
								
								break;
							case 4 :
								if (this.montantncst>0) {
									this.table.setValueAt(this.amorcst(), row, col);
								}
								
								break;
							case 3 : 
								if (this.montantncst>0) {
									this.table.setValueAt(this.annnncst(), row, col);
								} 
								break;
						}
					} 
				}
				break;
			//paiement semestriel annuité cst
			case 2 : 
				for (int row = 0 ; row<this.duree+1;row++) {
					for (int col = 0 ; col<5;col++) {
						switch (col) {
							case 0 :
								if (!isUnderOne(this.montantncst)) {
									casselescouilles(row, col, 6);
								}
								break;
							case 1 :
								if (row == 0) {
									this.table.setValueAt(this.montant, row, col);
								} else {
									this.captitalrestantann();
									if (isUnderOne(this.montantncst)) {
										deleteLastLine(row, col);
										if (this.montantncst<0) {
											this.table.setValueAt(this.amorcst()+this.montantncst, row-1, col+2);
										} else {
											this.table.setValueAt(this.amorcst()-this.montantncst, row-1, col+2);
										}
										
									} else {
										this.table.setValueAt(this.getMontantncst(), row, col);
									}
									
								}
								break;
							case 2 :
								if (this.montantncst>0) {
									this.table.setValueAt(this.montantncst*(this.indice/100), row, col);
								}
								
								break;
							case 4 :
								if (this.montantncst>0) {
									this.table.setValueAt(this.amorcst(), row, col);
								}
								
								break;
							case 3 : 
								if (this.montantncst>0) {
									this.table.setValueAt(this.annnncst(), row, col);
								} else {
									deleteLastLine(row, col);
								}
								break;
							
						}
					} 
				}
				break;
			//paiement annuel annuité cst
			case 3 :
				for (int row = 0 ; row<this.duree+1;row++) {
					for (int col = 0 ; col<5;col++) {
						switch (col) {
							case 0 : 
								if (!isUnderOne(this.montantncst)) {
									casselescouillesleretourdujedi(row, col);
								} 
							
							break;
							case 1 :
								if (row == 0) {
									this.table.setValueAt(this.montant, row, col);
								} else {
									this.captitalrestantann();
									if (isUnderOne(this.montantncst)) {
										deleteLastLine(row, col);
										if (this.montantncst<0) {
											this.table.setValueAt(this.amorcst()+this.montantncst, row-1, col+2);
										} else {
											this.table.setValueAt(this.amorcst()-this.montantncst, row-1, col+2);
										}
										
									} else {
										this.table.setValueAt(this.getMontantncst(), row, col);
									}
									
								}
								break;
							case 2 :
								if (this.montantncst>0) {
									this.table.setValueAt(this.montantncst*(this.indice/100), row, col);
								} 
								
								break;
							case 4 :
								if (this.montantncst>0) {
									this.table.setValueAt(this.amorcst(), row, col);
								} 
								
								break;
							case 3 : 
								if (this.montantncst>0) {
									this.table.setValueAt(this.annnncst(), row, col);
								} else {
									deleteLastLine(row, col);
								}
								break;
							
						}
					} 
				}
				break;
		}
		
	}

	public void deleteLastLine(int row, int col) {
		this.table.setValueAt(null, row, col-1);
		this.table.setValueAt(null, row, col);
		this.table.setValueAt(null, row, col+1);
		this.table.setValueAt(null, row, col+2);
		this.table.setValueAt(null, row, col+3);
	}

	public void casselescouillesleretourdujedi(int row, int col) {
		this.table.setValueAt("   X/"+mois+'/'+(annee+row), row, col);
		
	}

	public void casselescouilles(int row, int col, int v) {
		int moisr;
		int anneer;
	
		moisr = mois+row*v;
		anneer = 0;
		if (moisr>12) {
			anneer = 0;
			while (moisr>12) {
				moisr -= 12;
				anneer += 1;
			}
		}
		this.table.setValueAt("   X/"+moisr+'/'+(annee+anneer), row, col);
	}
	
}
