package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {

	private Labirinto labirinto = new Labirinto();
	private Map<String, Stanza> stanze = new HashMap<>();
	private List<String> nomiDegliAttrezziInLabirinto = new ArrayList<>();
	private Stanza ultimaStanza = new Stanza();

	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
		Stanza stanzaIniziale = new Stanza(nomeStanzaIniziale);
		this.labirinto.setStanzaIniziale(stanzaIniziale);
		this.stanze.put(nomeStanzaIniziale, stanzaIniziale);
		this.ultimaStanza = stanzaIniziale;
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nomeStanzaVincente) {
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		this.labirinto.setStanzaFinale(stanzaVincente);
		this.stanze.put(nomeStanzaVincente, stanzaVincente);
		this.ultimaStanza = stanzaVincente;
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza stanza = new Stanza(nomeStanza);
		this.stanze.put(nomeStanza, stanza);
		this.ultimaStanza = stanza;
		return this;
	}

	public LabirintoBuilder addAdiacenza(String nomePrimaStanza, String nomeSecondaStanza, String direzione) {
		Stanza primaStanza = null;
		Stanza secondaStanza = null;
		
		for(Stanza stanza: stanze.values()) {
			if(nomePrimaStanza.equals(stanza.getNome())) {
				primaStanza = stanza;
			}
			if(nomeSecondaStanza.equals(stanza.getNome())) {
				secondaStanza = stanza;
			}
		}
		
		if(primaStanza!=null && secondaStanza!=null) {
			primaStanza.impostaStanzaAdiacente(direzione, secondaStanza);
		}
		
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int pesoAttrezzo) {
		if(!this.nomiDegliAttrezziInLabirinto.contains(nomeAttrezzo)) {
			this.ultimaStanza.addAttrezzo(new Attrezzo(nomeAttrezzo, pesoAttrezzo));
			this.nomiDegliAttrezziInLabirinto.add(nomeAttrezzo);
		}
		
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	public Map<String, Stanza> getListaStanze() {
		return this.stanze;
	}
	
	
}
