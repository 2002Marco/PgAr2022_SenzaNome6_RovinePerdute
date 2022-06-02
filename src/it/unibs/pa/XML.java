package it.unibs.pa;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class XML {
	
public static XMLStreamReader creaReader (String nomeFile, String nomeConEstensione) {
		
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(nomeFile, new FileInputStream(nomeConEstensione));
			} catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage());
			}
		return xmlr;
	}
	
	public static XMLStreamWriter creaWriter (String nomeFile) {
		
		XMLOutputFactory xmlof = null;
		XMLStreamWriter xmlw = null;
		try {
		xmlof = XMLOutputFactory.newInstance();
		xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(nomeFile), "utf-8");
		xmlw.writeStartDocument("utf-8", "1.0");
		} catch (Exception e) {
		System.out.println("Errore nell'inizializzazione del writer:");
		System.out.println(e.getMessage());
		}
		
		return xmlw;
	}
	
	public static List<Citta> prendiProssimaCitta(String nomeFile, String nomeConEstensione) throws XMLStreamException {
		XMLStreamReader xmlr = creaReader(nomeFile, nomeConEstensione);
		int id = 0, x = 0, y= 0, h =0;
		String nome = "";
		ArrayList <Integer> collegamenti = new ArrayList <>();
		List <Citta> citta = new ArrayList <Citta>();
		
		do {
			  			
			switch(xmlr.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				if (xmlr.getLocalName().equals("city")) {
					id = Integer.parseInt(xmlr.getAttributeValue(0));
					x = Integer.parseInt(xmlr.getAttributeValue(2));
					y = Integer.parseInt(xmlr.getAttributeValue(3));
					h = Integer.parseInt(xmlr.getAttributeValue(4));
					nome = xmlr.getAttributeValue(1);
				}
				if (xmlr.getLocalName().equals("link")) {
					collegamenti.add(Integer.parseInt(xmlr.getAttributeValue(0)));
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				if(xmlr.getLocalName().equals("city")) {
					citta.add(new Citta(id, nome, new Coordinata(x, y, h), collegamenti));
					collegamenti = new ArrayList<Integer>();
				}
				break;
			}
			xmlr.next();
			
		}while(xmlr.hasNext());
		return citta;		
	}

}


