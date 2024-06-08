package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza {
	private Direzione direzioneBloccata;
	private Attrezzo chiave;
	
	public StanzaBloccata(String nome, Direzione direzione, String nomeChiave) {
		super(nome);
		this.direzioneBloccata = direzione;
		this.chiave = new Attrezzo(nomeChiave, 0);
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {		
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
