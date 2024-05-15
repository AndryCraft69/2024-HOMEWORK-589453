package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo != null) {
			if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) 
				return false;
			if (this.numeroAttrezzi==10) 
				return false;
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
		else 
			return false;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzo = null;
		for (int i= 0; i < this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				attrezzo = attrezzi[i];
		return attrezzo;
	}
	
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	 
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoDaRimuovere = null;
		
		for(int i=0; i < numeroAttrezzi; i++) {
			if(attrezzi[i].getNome().equals(nomeAttrezzo)) {
				attrezzoDaRimuovere = attrezzi[i];
				for(int j=i; j < numeroAttrezzi-1; j++) {
					attrezzi[j] = attrezzi[j+1];
				}
				attrezzi[numeroAttrezzi-1] = null;
				numeroAttrezzi--;
			}
		}
		
		return attrezzoDaRimuovere;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}