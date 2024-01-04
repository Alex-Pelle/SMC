package engine;
import java.lang.*;


public class Text {
	private String text;
	
	public Text(String text) {
		
		this.text=text;
	}
	
	public String getText() {
		
		return this.text;
	}
	
	public boolean checkMultiplication() {
		for (int i = 0; i < this.getText().length();i++) {
			if (this.getText().charAt(i)=='*') {
				return true;
			}
		}
		return false;
	}
	
	public int posStar() {
		for (int i = 0; i < this.getText().length();i++) {
			if (this.getText().charAt(i)=='*') {
				return this.text.indexOf('*');
			}
		}
		throw new ArithmeticException("pas de multiplication");
	}
	
	public boolean isDot() {
		for (int i = 0; i < this.getText().length();i++) {
			if (this.getText().charAt(i)=='.' || this.getText().charAt(i)==',') {
				return true;
			}
		}
		return false;
	}
	
	public int posDot() {
		for (int i = 0; i < this.getText().length();i++) {
			if (this.getText().charAt(i)=='.') {
				return this.text.indexOf('.');
			}
			if (this.getText().charAt(i)==',') {
				return this.text.indexOf(',');
			}
		}
		throw new ArithmeticException("pas de virgule");
	}
	
	public void addDigit(char caracter) {
		this.text+=caracter;
	}
	
	public void reset() {
		this.text="";
	}
	
	public boolean isNegative() {
		if (this.text.charAt(0)=='-') {
			return true;
		} else {
			return false;
		}
	}
	
	
	public boolean isNumeric() {
		if (this.dotCount()<=1) {
			if (this.isNegative()) {
				for (int i = 1; i < this.getText().length();i++) {
					if (Character.isLetter(this.getText().charAt(i))) {
						return false;
					}
				}
				return true;
			} else {
				for (int i = 0; i < this.getText().length();i++) {
					if (Character.isLetter(this.getText().charAt(i))) {
						return false;
					}
				}
				return true;
			}
		} else {
			return false;
		}
		
		
	}
	
	public float stringToFloat() {
		if (!this.isNull()) {
			if (this.isNumeric()) {
				if (!this.isNegative()) {
					return Float.parseFloat(this.getText());
				} else {
					return (-1)*Float.parseFloat(this.getText());
				}
			} else {
				throw new IllegalArgumentException("Not a number");
			}
		} else {
			return 0F;
		}
	}
	
	public boolean isNull() {
		if (this.getText()==null || this.getText()=="" || this.getText().length()==0) {
			return true;
		} else {
			return false;
		}
	}
	
	public float multiplication() {
		Text t1 = new Text("");
		Text t2 = new Text("");
		float f1;
		float f2;
		
		for(int i = 0; i < this.posStar(); i++) {
			t1.text += this.text.charAt(i);
		}
		for(int i = this.posStar()+1; i < this.text.length(); i++) {
			t2.text += this.text.charAt(i);
		}
		
		if (t1.isDot()) {
			f1 = t1.notEntier();
		}else {
			f1 = t1.stringToFloat();
		}
		
		if (t2.isDot()) {
			f2 = t2.notEntier();
		}else {
			f2 = t2.stringToFloat();
		}
		
		return f1*f2;
	}
	
	public float toFloat() {
		if (this.isNumeric()) {
			if (!this.isNull()) {
				if (!this.checkMultiplication()) {
					if (this.isDot()) {
						return this.notEntier();
					} else {
						return this.stringToFloat();
					}	
				} else {
					return this.multiplication();
				}
			}else {
				return 0f;
			}
		} else {
			throw new IllegalArgumentException("Not a float");
		}
		
	}
	
	public float notEntier() {
		Text t1 = new Text("");
		Text t2 = new Text("");
		
		for(int i = 0; i < this.posDot(); i++) {
			t1.text += this.text.charAt(i);
		}
		for(int i = this.posDot()+1; i < this.text.length(); i++) {
			t2.text += this.text.charAt(i);
		}
		
		if (!this.isNegative()) {
			return t1.stringToFloat()+(t2.stringToFloat()/(float)(Math.pow(10, t2.text.length())));
		} else {
			return (-1)*(t1.stringToFloat()+(t2.stringToFloat()/(float)(Math.pow(10, t2.text.length()))));
		}
		
		 
	}
	
	public int dotCount() {
		int count=0;
		for (int i=0;i<this.text.length();i++) {
			if (this.getText().charAt(i)=='.') {
				count++;
			}
		}
		return count;
	}
		
}
