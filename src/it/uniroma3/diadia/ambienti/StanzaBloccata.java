package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private Attrezzo chiave;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String nomeChiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.chiave = new Attrezzo(nomeChiave, 0);
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {		
		if(super.hasAttrezzo(chiave) != true) {
			return this;
		}
		else {
			return super.getStanzaAdiacente(direzione);
		}
		
	}
	
	@Override
	public String getDescrizione() {
		if(super.hasAttrezzo(chiave) == true) {
			return super.getDescrizione();
		}
		else {
			return super.getDescrizione() + "\nLa direzione " + direzioneBloccata + " è bloccata";
		}		
		
	}
	
}
