package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IoSimulator implements IO {
	private List<String> elencoComandiDaEseguire;
	private List<String> elencoMessaggiStampati;
	private int posUltimoComando;
	
	public IoSimulator() {
		this.elencoComandiDaEseguire = new ArrayList<String>();
		this.elencoMessaggiStampati = new ArrayList<String>();
		posUltimoComando = 0;
	}
	
	public IoSimulator(List<String> elencoComandiDaEseguire) {	
		this();
		this.elencoComandiDaEseguire = elencoComandiDaEseguire;
	}
	
	public List<String> getElencoComandiDaEseguire() {
		return this.elencoComandiDaEseguire;
	}

	public List<String> getElencoMessaggiStampati() {
		return this.elencoMessaggiStampati;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		elencoMessaggiStampati.add(messaggio);
	}

	@Override
	public String leggiRiga() {
		String stringa = elencoComandiDaEseguire.get(posUltimoComando);
		posUltimoComando++;
		
		return stringa;
	}

}
