package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		Attrezzo attrezzoDaRegalare = partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro());
		
		if(attrezzoDaRegalare != null && personaggio!=null) {
			super.getIo().mostraMessaggio(personaggio.riceviRegalo(attrezzoDaRegalare, partita));
		}
	}
	
}
