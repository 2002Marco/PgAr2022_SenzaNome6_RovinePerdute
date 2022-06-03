package it.unibs.pa;

public class Coordinata {
	
	private int x;
	private int y;
	private int h;
	
	
	public Coordinata(int x, int y, int h) {
		this.x = x;
		this.y = y;
		this.h = h;
	}


	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	public int getH() {
		return h;
	}

	
	
	public Double distanzaEuclidea(Coordinata altro) {		//metodo che calcola la distanza in base alla distanza di due punti nel piano
		return Math.sqrt(Math.pow((this.x - altro.x), 2) + Math.pow((this.y - altro.y), 2));
	}
	
	public int differenzaAltezza(Coordinata altro) {	//metodo che calcola la distanza in base all'altezza
		return Math.abs(this.h - altro.h);
	}
	
	public String toString() {
		String toReturn = new String();
		toReturn = toReturn.concat(String.format("(%d ,%d, %d)", x, y ,h));
		return toReturn;
	}

}
