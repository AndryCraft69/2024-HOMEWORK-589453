package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNome;
import it.uniroma3.diadia.attrezzi.ComparatorePerPeso;
import it.uniroma3.diadia.attrezzi.ComparatorePerPesoENome;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzoDaAggiungere) {
		if(attrezzoDaAggiungere != null) {
			if (this.getPeso() + attrezzoDaAggiungere.getPeso() > this.getPesoMax()) 
				return false;
			attrezzi.put(attrezzoDaAggiungere.getNome(), attrezzoDaAggiungere);
			return true;
		}
		else 
			return false;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return attrezzi.get(nomeAttrezzo);
	}
	
	public int getPeso() {
		int peso = 0;
		
		for(Attrezzo attrezzo: this.attrezzi.values()) {
			peso += attrezzo.getPeso();
		}
		
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}
	
	public boolean hasAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.containsValue(attrezzo);
	}
	 
	public Attrezzo removeAttrezzo(String nomeAttrezzoDaRimuovere) {
		return this.attrezzi.remove(nomeAttrezzoDaRimuovere);
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			
			for(Attrezzo attrezzo: this.attrezzi.values()) {
				s.append(attrezzo.toString() + " ");
			}
		}
		else {
			s.append("Borsa vuota");
		}
		
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		ComparatorePerPeso comparatore = new ComparatorePerPeso();
		List<Attrezzo> attrezziOrdinatiPerPeso = new ArrayList<>();
		attrezziOrdinatiPerPeso.addAll(this.attrezzi.values());
		Collections.sort(attrezziOrdinatiPerPeso, comparatore);
		return attrezziOrdinatiPerPeso;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		ComparatorePerNome comparatore = new ComparatorePerNome();
		List<Attrezzo> attrezziOrdinatiPerNome = new ArrayList<>();
		attrezziOrdinatiPerNome.addAll(this.attrezzi.values());
		Collections.sort(attrezziOrdinatiPerNome, comparatore);
		SortedSet<Attrezzo> attrezziOrdinatiPerNomeInsieme = new TreeSet<Attrezzo>(comparatore);
		attrezziOrdinatiPerNomeInsieme.addAll(attrezziOrdinatiPerNome);
		return attrezziOrdinatiPerNomeInsieme;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<Integer, Set<Attrezzo>>();
		
		for(Attrezzo attrezzo: this.attrezzi.values()) {
			int pesoCorrente = attrezzo.getPeso();
			if(mappa.containsKey(pesoCorrente)) {
				mappa.get(pesoCorrente).add(attrezzo);
			}
			else {
				Set<Attrezzo> set = new TreeSet<Attrezzo>(new ComparatorePerNome());
				set.add(attrezzo);
				mappa.put(pesoCorrente, set);
			}
		}
		
		return mappa;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatorePerPesoENome comparatore = new ComparatorePerPesoENome();
		List<Attrezzo> attrezziOrdinatiPerPesoENome = new ArrayList<>();
		attrezziOrdinatiPerPesoENome.addAll(this.attrezzi.values());
		Collections.sort(attrezziOrdinatiPerPesoENome, comparatore);
		SortedSet<Attrezzo> attrezziOrdinatiPerPesoENomeInsieme = new TreeSet<Attrezzo>(comparatore);
		attrezziOrdinatiPerPesoENomeInsieme.addAll(attrezziOrdinatiPerPesoENome);
		return attrezziOrdinatiPerPesoENomeInsieme;
	}
}

