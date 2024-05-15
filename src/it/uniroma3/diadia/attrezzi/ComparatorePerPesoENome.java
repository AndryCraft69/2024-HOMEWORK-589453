package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatorePerPesoENome implements Comparator<Attrezzo> {
	
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		if(a1.getPeso() > a2.getPeso()) return 1;
		else if(a1.getPeso() < a2.getPeso()) return -1;
		else {
			return a1.getNome().compareTo(a2.getNome());
		}
	}
}
