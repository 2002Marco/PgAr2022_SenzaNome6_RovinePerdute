package it.unibs.pa;

import java.util.Deque;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class Main {
	
	private static final String MSG_SCEGLI_FILE = "Scegli un file (inserisci il numero) ->";
	public static final String[] FILE ={"PgAr_Map_5", "PgAr_Map_12", "PgAr_Map_50", "PgAr_Map_200", "PgAr_Map_2000", "PgAr_Map_10000"};

	public static void main(String[] args) throws XMLStreamException {
		
	
		for(int  i= 0; i < FILE.length; i++) {
			System.out.println(String.format("%d) %s", i + 1 , FILE[i]));
		}
		
		int scelta = InputDati.leggiIntero(MSG_SCEGLI_FILE, 1, FILE.length);
		
		String file = FILE[scelta - 1];
		XMLStreamWriter output = XML.creaWriter("output.xml");
		
		
		Mappa m = new Mappa(file, file + ".xml");
		Deque <Citta> percorsoT = m.percorsoMinimo(0);
		XML.outputPercorso("Tonathiu", percorsoT, output);
		Deque <Citta> percorsoM = m.percorsoMinimo(1);
		XML.outputPercorso("Metztil", percorsoM, output);
		
		output.writeEndDocument();
		output.flush();
		output.close();
		
		System.out.println("Il percorso piu veloce e' stato trovato!");
	}

}
