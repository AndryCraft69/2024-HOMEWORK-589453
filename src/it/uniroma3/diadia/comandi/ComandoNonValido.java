package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.IO;

public class ComandoNonValido implements Comando {
	
	static final private IO ioConsole = new IOConsole();
	
	@Override
	public void esegui(Partita partita) {
		ioConsole.mostraMessaggio("Comando non valido");
	}

	@Override
	public void setParametro(String parametro) {
	}
	
	@Override
	public String getNome() {
		return null;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
