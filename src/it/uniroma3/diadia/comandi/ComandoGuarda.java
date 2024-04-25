package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.IO;

public class ComandoGuarda implements Comando {
	
	static final private IO ioConsole = new IOConsole();

	@Override
	public void esegui(Partita partita) {
		ioConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
