package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(super.getParametro());
		
		if(attrezzoDaPosare != null) {
			if(partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) 
				partita.getGiocatore().getBorsa().removeAttrezzo(super.getParametro());
			else 
				super.getIo().mostraMessaggio("Stanza piena");
		}
		else 
			super.getIo().mostraMessaggio("Attrezzo non presente");
	}

}
