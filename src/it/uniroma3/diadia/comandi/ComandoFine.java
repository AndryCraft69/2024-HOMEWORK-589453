package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Partita;

public class ComandoFine extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		super.getIo().mostraMessaggio("Grazie di aver giocato!");
		partita.setFinita(true);
	}

}
