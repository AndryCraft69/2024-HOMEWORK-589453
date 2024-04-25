package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String chiave;
	
	public StanzaBloccata(String nome, String direzioneBloccata, String chiave) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.chiave = chiave;
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
