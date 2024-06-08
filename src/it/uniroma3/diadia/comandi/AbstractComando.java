package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Partita;

public abstract class AbstractComando implements Comando{ 
	
	private IO io;
	private String parametro;
	
	public abstract void esegui(Partita partita);
	
	public String getNome() {
		String nomeComando = this.getClass().getSimpleName().substring(7);
		
		StringBuilder s = new StringBuilder()
				.append(Character.toLowerCase(nomeComando.charAt(0)))
				.append(nomeComando.substring(1));
		
		return s.toString();
	}
	
	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	public void setIO(IO io) {
		this.io = io;
	}

	public IO getIo() {
		return io;
	}
	
}
