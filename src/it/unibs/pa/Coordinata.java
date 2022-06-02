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


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getH() {
		return h;
	}


	public void setH(int h) {
		this.h = h;
	}
	
	public Double distanzaEuclidea(Coordinata altro) {
		return Math.sqrt(Math.pow((this.x - altro.x), 2) + Math.pow((this.y - altro.y), 2));
	}
	
	public int differenzaAltezza(Coordinata altro) {
		return Math.abs(this.h - altro.h);
	}
	
	public String toString() {
		String toReturn = new String();
		toReturn = toReturn.concat(String.format("(%d ,%d, %d)", x, y ,h));
		return toReturn;
	}

}
