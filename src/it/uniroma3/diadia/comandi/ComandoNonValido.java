package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Partita;

public class ComandoNonValido extends AbstractComando {
	
	@Override
	public void esegui(Partita partita) {
		super.getIo().mostraMessaggio("Comando non valido");
	}
	
	@Override
	public String getNome() {
		return null;
	}

}
