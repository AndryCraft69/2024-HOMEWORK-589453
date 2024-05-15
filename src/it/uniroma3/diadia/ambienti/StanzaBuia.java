package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {
	private Attrezzo oggettoCheIlluminaLaStanza;
	
	public StanzaBuia(String nome, String nomeOggettoCheIlluminaLaStanza) {
		super(nome);		
		this.oggettoCheIlluminaLaStanza = new Attrezzo(nomeOggettoCheIlluminaLaStanza, 0);
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
