package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho più nulla...";
	private static final String MESSAGGIO_DOPO_REGALO = "Apprezzo il tuo regalo! Adesso te lo rendo più leggero.";
	private static final String PRESENTAZIONE_DI_DEFAULT = "Salve avventuriero.";
	
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	public Mago(String nome, Attrezzo attrezzo) {
		super(nome, PRESENTAZIONE_DI_DEFAULT);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		if(attrezzo != null) {
			attrezzo.setPeso(attrezzo.getPeso() / 2);
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
		}
		
		return MESSAGGIO_DOPO_REGALO;
	}
}