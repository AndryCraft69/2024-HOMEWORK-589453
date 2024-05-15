package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoAiuto implements Comando {
	
	private String[] elencoComandi;

	private IO io;
	
	public ComandoAiuto(String[] elencoComandi) {
		this.elencoComandi = elencoComandi;
	}

	@Override
	public void esegui(Partita partita) {
		StringBuilder elencoComandiString = new StringBuilder();
		
		for(int i=0; i< elencoComandi.length; i++) 
			elencoComandiString.append(elencoComandi[i] + " ");
		
		io.mostraMessaggio(elencoComandiString.toString());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
