package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {
	private Attrezzo oggettoCheIlluminaLaStanza;
	
	public StanzaBuia(String nome, Attrezzo oggettoCheIlluminaLaStanza) {
		super(nome);
		this.oggettoCheIlluminaLaStanza = oggettoCheIlluminaLaStanza;
	}
	
	@Override
	public String getDescrizione() {		
		if(super.getAttrezzi().contains(oggettoCheIlluminaLaStanza)) {
			return super.getDescrizione();
		}
		else {
			return "Qui c'è un buio pesto";
		}
	}
}
