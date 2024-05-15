package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

public class ComandoVai implements Comando {
	
	private IO io;
	
	private String direzione;
	
	/**
	* esecuzione del comando
	*/	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza prossimaStanza = null;
		
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare? Devi specificare una direzione");
		}
		else {
			prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
			
			if (prossimaStanza == null)
				io.mostraMessaggio("Direzione inesistente");
			
			else {
				partita.setStanzaCorrente(prossimaStanza);
				io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
				partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			}
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}

	@Override
	public String getNome() {
		return "vai";
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