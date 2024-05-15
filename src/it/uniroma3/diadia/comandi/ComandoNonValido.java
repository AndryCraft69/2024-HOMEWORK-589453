package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoNonValido implements Comando {
	
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando non valido");
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

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
