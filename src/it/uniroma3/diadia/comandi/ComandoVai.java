package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando {

	/**
	 * esecuzione del comando
	 */	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;

		if(super.getParametro() == null) {
			super.getIo().mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
		}
		else {
			try{
				prossimaStanza = stanzaCorrente.getStanzaAdiacente(Direzione.valueOf(super.getParametro()));

				if (prossimaStanza == null)
					super.getIo().mostraMessaggio("Direzione inesistente");
				else {
					partita.setStanzaCorrente(prossimaStanza);
					super.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
					partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
				}}
			catch(Exception IllegalArgumentException){
				super.getIo().mostraMessaggio("Direzione inesistente");
			}
		}
	}

}