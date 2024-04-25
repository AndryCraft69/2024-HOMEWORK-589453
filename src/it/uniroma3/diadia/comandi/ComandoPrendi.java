package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	
	static final private IO ioConsole = new IOConsole();
	
	String nomeAttrezzoDaPrendere;

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzoDaPrendere);
		
		if(attrezzoDaPrendere != null) {
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) 
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
			else 
				ioConsole.mostraMessaggio("Borsa piena");
		}
		else ioConsole.mostraMessaggio("Attrezzo non presente");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPrendere = parametro;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}

}
