package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	
	private Labirinto() {
		this.stanzaIniziale = null;
		this.stanzaFinale = null;
		creaStanze();
	}
	
	private Labirinto(String nomeFile){
		CaricatoreLabirinto c;
		
		try {
			c = new CaricatoreLabirinto(nomeFile);
			c.carica();
			this.stanzaIniziale = c.getStanzaIniziale();
			this.stanzaFinale = c.getStanzaVincente();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//creaStanze();
	}

	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaFinale() {
		return stanzaFinale;
	}

	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}
	
	/**
     * NON PIÙ IN USO
     */
    public void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		StanzaMagica labIA = new StanzaMagica("Laboratorio IA", 1);
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzione.nord, biblioteca);
		atrio.impostaStanzaAdiacente(Direzione.est, aulaN11);
		atrio.impostaStanzaAdiacente(Direzione.sud, aulaN10);
		atrio.impostaStanzaAdiacente(Direzione.ovest, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.est, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.ovest, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.nord, atrio);
		aulaN10.impostaStanzaAdiacente(Direzione.est, aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzione.ovest, laboratorio);
		aulaN10.impostaStanzaAdiacente(Direzione.sud, labIA);
		laboratorio.impostaStanzaAdiacente(Direzione.est, atrio);
		laboratorio.impostaStanzaAdiacente(Direzione.ovest, aulaN11);
		biblioteca.impostaStanzaAdiacente(Direzione.sud, atrio);
		labIA.impostaStanzaAdiacente(Direzione.nord, aulaN10);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio e finisce nella biblioteca
		this.setStanzaIniziale(atrio);
		this.setStanzaFinale(biblioteca);
		
    }
    
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}
	public static LabirintoBuilder newBuilder(String nomeFile) {
		return new LabirintoBuilder(nomeFile);		
	}
    public static class LabirintoBuilder {

    	private Labirinto labirinto;
    	private Map<String, Stanza> stanze;
    	private List<String> nomiDegliAttrezziInLabirinto;
    	private Stanza ultimaStanza;
    	
    	public LabirintoBuilder() {
    		labirinto = new Labirinto();
        	stanze = new HashMap<>();
        	nomiDegliAttrezziInLabirinto = new ArrayList<>();
        	ultimaStanza = null;
    	}

    	public LabirintoBuilder(String nomeFile) {
			this();
			this.labirinto = new Labirinto(nomeFile);
		}

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
    	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int sogliaMagica) {
    		Stanza stanzaMagica = new StanzaMagica(nomeStanzaMagica, sogliaMagica);
    		this.stanze.put(nomeStanzaMagica, stanzaMagica);
    		this.ultimaStanza = stanzaMagica;
    		return this;
    	}
    	public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, Direzione direzioneBloccata, String nomeChiave) {
    		Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, direzioneBloccata, nomeChiave);
    		this.stanze.put(nomeStanzaBloccata, stanzaBloccata);
    		this.ultimaStanza = stanzaBloccata;
    		return this;
    	}
    	public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeOggettoCheIlluminaLaStanza) {
    		Stanza stanzaBuia = new StanzaBuia(nomeStanzaBuia, new Attrezzo(nomeOggettoCheIlluminaLaStanza, 0));
    		this.stanze.put(nomeStanzaBuia, stanzaBuia);
    		this.ultimaStanza = stanzaBuia;
    		return this;
    	}

    	public LabirintoBuilder addAdiacenza(String nomePrimaStanza, String nomeSecondaStanza, Direzione direzione) {
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

}
