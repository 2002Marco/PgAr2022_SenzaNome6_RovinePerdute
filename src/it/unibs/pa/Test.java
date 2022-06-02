package it.unibs.pa;

import java.util.Deque;

import javax.xml.stream.XMLStreamException;

public class Test {

	public static void main(String[] args) throws XMLStreamException {
		
		Mappa m = new Mappa("PgAr_Map_10000", "PgAr_Map_10000.xml", 1);
		Deque <Citta> percorso = m.percorsoMinimo();
		
		for(Citta c: percorso) {
			System.out.println(c);
			System.out.println();
		}
	}

}
