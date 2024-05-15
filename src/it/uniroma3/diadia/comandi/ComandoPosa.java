package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando {
	
	private IO io;

	String nomeAttrezzoDaPosare;
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzoDaPosare = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzoDaPosare);
		
		if(attrezzoDaPosare != null) {
			if(partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) 
				partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzoDaPosare);
			else 
				io.mostraMessaggio("Stanza piena");
		}
		else 
			io.mostraMessaggio("Attrezzo non presente");
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzoDaPosare = parametro;
	}

	@Override
	public String getNome() {
		return "posa";
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
