package engine;

public class Indice {
	
	private float ca;
	private float cv;
	private float cf;
	
	public Indice(Text ca,Text cv,Text cf) {
		if (!ca.isNull()) {
			if (!ca.checkMultiplication()) {
				if (ca.isDot()) {
					this.ca = ca.notEntier();
				} else {
					this.ca = ca.stringToFloat();
				}	
			} else {
				this.ca = ca.multiplication();
			}
		}else {
			this.ca = 0f;
		}
		
		if (!cv.isNull()) {
			if (!cv.checkMultiplication()) {
				if (cv.isDot()) {
					this.cv = cv.notEntier();
				} else {
					this.cv = cv.stringToFloat();
				}	
			} else {
				this.cv = cv.multiplication();
			}
		} else {
			this.cv = 0f;
		}
		
		if (!cf.isNull()) {
			if (!cf.checkMultiplication()) {
				if (cf.isDot()) {
					this.cf = cf.notEntier();
				} else {
					this.cf = cf.stringToFloat();
				}	
			} else {
				this.cf = cf.multiplication();
			}
		} else {
			this.cf = 0f;
		}	
	}
	
	
	
	public float MCV() {
		return this.ca-this.cv;
	}
	
	public float TR(){
		return (this.resultat()/this.ca)*100;
	}
	
	public float Ids() {
		return ((this.ca-this.SR())/this.ca)*100;
	}

	public float resultat() {
		return this.MCV()-this.cf;
	}
	
	public float SR() {
		return this.cf/(this.MCV()/this.ca);
	}
	
	public String toStringMCV() {
		return Float.toString(this.MCV());
	}
	
	public String toStringResultat() {
		return Float.toString(this.resultat());
	}
	
	public String toStringSR() {
		return Float.toString(this.SR());
	}
	
	public String toStringTR() {
		return Float.toString(this.TR());
	}
	
	public String toStringIds() {
		return Float.toString(this.Ids());
	}



	public float getCa() {
		return ca;
	}



	public float getCv() {
		return cv;
	}



	public float getCf() {
		return cf;
	}

}
