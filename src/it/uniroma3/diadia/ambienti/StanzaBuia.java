package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza {
	private String oggettoCheIlluminaLaStanza;
	
	public StanzaBuia(String nome, String oggettoCheIlluminaLaStanza) {
		super(nome);		
		this.oggettoCheIlluminaLaStanza = oggettoCheIlluminaLaStanza;
	}
	
	@Override
	public String getDescrizione() {
		Attrezzo[] attrezzi = super.getAttrezzi();
		boolean lanternaPresente = false;
		
		for(Attrezzo attrezzo: attrezzi) {
			if(attrezzo != null) {
				if(attrezzo.getNome().equals(oggettoCheIlluminaLaStanza))
					lanternaPresente = true;
			}
		}
		
		if(lanternaPresente == true) {
			return super.getDescrizione();
		}
		else {
			return "Qui c'è un buio pesto";
		}
	}
}
