package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Partita;

public class ComandoAiuto extends AbstractComando {
	
	static final private String[] elencoComandi = {"vai", "aiuto", "prendi", "posa", "fine", "guarda", "regala", "saluta", "interagisci"};

	@Override
	public void esegui(Partita partita) {
		StringBuilder elencoComandiString = new StringBuilder();
		
		for(int i=0; i< elencoComandi.length; i++) 
			elencoComandiString.append(elencoComandi[i] + " ");
		
		super.getIo().mostraMessaggio(elencoComandiString.toString());
	}
}
