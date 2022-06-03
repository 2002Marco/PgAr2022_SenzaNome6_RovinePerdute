package it.unibs.pa;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLStreamException;

public class Mappa {
	
	private static  List <Citta> mappa;
	private int numCitta;
	
	public Mappa(String nomeFile, String nomeFileConEstensione) throws XMLStreamException {
		mappa = XML.creaMappa(nomeFile, nomeFileConEstensione);
		for(Citta c : mappa) {
			c.creaLinks();		//crea i collegamenti di ogni citta
		}
		this.numCitta = mappa.size();
		}
	
	
	public static  List<Citta> getMappa() {
		return mappa;
	}
	
	public Deque<Citta> percorsoMinimo (int team) {
		
		for(Citta c: mappa) {		//imposto tute le distanza ad infinito (per il secondo team)
			c.setCittaPrecedente(null);
			c.setDistanza(Double.POSITIVE_INFINITY);
		}
		
		Citta cittaCorrente = mappa.get(0);		//campo base
		cittaCorrente .setDistanza(0.0);		//poniamo la distanza da se stesso a zero
		Set <Citta> cittaDaVisitare = new HashSet<Citta>();
		cittaDaVisitare.addAll(mappa);			//insieme delle citta da visitare
		
		while(cittaDaVisitare.size() != 0) {
			 
			 Set <Citta> vicini =  cittaCorrente.getLinks();
			 vicini.retainAll(cittaDaVisitare); //vicini non visitati
			 
			 for(Citta c: vicini) {		//per ogni vicino della citta corrente aggiorno la distanza dalla base 
				 double distanza = distanza(cittaCorrente, c, team);
				 if(distanza < c.getDistanza()) {
					 c.setDistanza(distanza);
					 c.setCittaPrecedente(cittaCorrente);
				 }
			 }
			 cittaDaVisitare.remove(cittaCorrente);		//rimuovo la citta corrente dalle citta da visitare
			 cittaCorrente = cittaPiuVicina(cittaDaVisitare);
			 
			 if (cittaCorrente.equals(mappa.get(numCitta -1)))		//se siamo arrivati alla rovina allora interrompiamo il ciclo
				 break; 
		}
		
		return creaPercorsoMinimo(mappa.get(numCitta - 1));
	}

	public  Citta cittaPiuVicina(Set <Citta> cittaDaVisitare) {		//metodo che restituisce la citta piu vicina dalla base
		Citta cittaPiuVicina = null;
		Double max = Double.POSITIVE_INFINITY;
		int id = 0;
		int cittaPercorse = -1;
		for(Citta c: cittaDaVisitare) {
			if (c.getDistanza()  < max) {
				max = c.getDistanza();
				cittaPiuVicina = c;
				id = c.getId();
				cittaPercorse = creaPercorsoMinimo(c).size();
			}
			else if (c.getDistanza()  ==  max && creaPercorsoMinimo(c).size() < cittaPercorse) {
				cittaPiuVicina = c;
				id = c.getId();
				cittaPercorse = creaPercorsoMinimo(c).size();
			}
			else if (c.getDistanza()  == max && creaPercorsoMinimo(c).size() == cittaPercorse && c.getId() > id) {
				cittaPiuVicina = c;
				id = c.getId();
				cittaPercorse = creaPercorsoMinimo(c).size();
			}
		}
		return cittaPiuVicina;
	}
	public  Double distanza (Citta c1, Citta c2, int team) {		//metodo che restituisce la distanza di una citta dalla base in base al team
		if (team == 0)
			return c1.getDistanza() + c1.getPosizione().distanzaEuclidea(c2.getPosizione());
		else
			return c1.getDistanza() + c1.getPosizione().differenzaAltezza(c2.getPosizione());
		
	}
	
	public  Deque <Citta> creaPercorsoMinimo(Citta c) {
		Deque <Citta> percorsoMinimo  = new ArrayDeque<Citta>();		//ricostruisco il percorso partendo dalla rovina
		
		while(c.getCittaPrecedente() != null) {
			percorsoMinimo.addFirst(c);
			c = c.getCittaPrecedente();
		}
		percorsoMinimo.addFirst(mappa.get(0));
		
		return  percorsoMinimo;
	}
}
