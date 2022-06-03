package it.unibs.pa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Citta {
	
	private int id;
	private String nome;
	private Coordinata posizione;
	private ArrayList <Integer> collegamenti; //L'id delle citta collegate 
	private Set <Citta> links;	//le citta collegate
	
	private Double distanza; //distanza dal nodo iniziale 
	private Citta cittaPrecedente;	//da quale citta siamo passati prima di arriavre qui
	
	
	public Citta(int id, String nome, Coordinata posizione, ArrayList <Integer> collegamenti) {
		this.id = id;
		this.nome = nome;
		this.posizione = posizione;
		this.collegamenti = collegamenti;
		this.links = new HashSet <Citta>();
		this.distanza = Double.POSITIVE_INFINITY;		
		this.cittaPrecedente = null;
	}
	
	

	public void setCittaPrecedente(Citta cittaPrecedente) {
		this.cittaPrecedente = cittaPrecedente;
	}
	
	

	public Citta getCittaPrecedente() {
		return cittaPrecedente;
	}



	public Double getDistanza() {
		return distanza;
	}



	public void setDistanza(Double distanza) {
		this.distanza = distanza;
	}



	public int getId() {
		return id;
	}


	public String getNome() {
		return nome;
	}


	public Coordinata getPosizione() {
		return posizione;
	}


	public Set <Citta> getLinks() {
		return links;
	}
	
	public Set <Citta> creaLinks() {
		for(Integer i : collegamenti) {
			links.add(Mappa.getMappa().get(i));
		}
		return links;
	}

}
