package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando {
	
	private IO io;
	
	String nomeAttrezzoDaPrendere;

	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzoDaPrendere = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzoDaPrendere);
		
		if(attrezzoDaPrendere != null) {
			if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzoDaPrendere)) 
				partita.getStanzaCorrente().removeAttrezzo(attrezzoDaPrendere);
			else 
				io.mostraMessaggio("Borsa piena");
		}
		else io.mostraMessaggio("Attrezzo non presente");
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

	@Override
	public void setIO(IO io) {
		this.io = io;
	}

}
