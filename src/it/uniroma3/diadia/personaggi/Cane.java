package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final int CFU_PERSI_DAL_MORSO = 1;
	private static final String MESSAGGIO_DOPO_IL_MORSO = "[Il cane ti ha morso] Woof Woof!";
	private static final String MESSAGGIO_DOPO_IL_REGALO = "[Il cane butta a terra un attrezzo] Bau bau!";
	private static final String PRESENTAZIONE_DI_DEFAULT = "Bau!";

	private String ciboPreferito;
	private Attrezzo attrezzo;
	
	public Cane(String nome, String presentazione, String ciboPreferito, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.ciboPreferito = ciboPreferito;
		this.attrezzo = attrezzo;
	}
	
	public Cane(String nome, String ciboPreferito, Attrezzo attrezzo) {
		super(nome, PRESENTAZIONE_DI_DEFAULT);
		this.ciboPreferito = ciboPreferito;
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu() - CFU_PERSI_DAL_MORSO);
		
		return MESSAGGIO_DOPO_IL_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		
		if(attrezzo.getNome().equals(ciboPreferito)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			return MESSAGGIO_DOPO_IL_REGALO;
		}
		else {
			return this.agisci(partita);
		}
	}

}
