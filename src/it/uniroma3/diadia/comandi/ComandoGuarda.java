package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Partita;

public class ComandoGuarda extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		if(super.getParametro() != null) {
			if(super.getParametro().equals("borsa")) {
				super.getIo().mostraMessaggio(partita.getGiocatore().getBorsa().toString());
			}
			else {
				super.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
			}
		}
		else {
			super.getIo().mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		}
	}
}
