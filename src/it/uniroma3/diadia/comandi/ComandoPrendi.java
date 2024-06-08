package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(super.getParametro());
		
		if(attrezzoDaPrendere != null) {
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) 
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
			else 
				super.getIo().mostraMessaggio("Borsa piena");
		}
		else super.getIo().mostraMessaggio("Attrezzo non presente");
	}

}
