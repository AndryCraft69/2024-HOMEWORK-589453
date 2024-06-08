package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;

	private boolean finita;	
	static final private int CFU_INIZIALI = 20;

	public Partita(){
		this(Labirinto.newBuilder().getLabirinto());
	}

	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.giocatore = new Giocatore();
		this.finita = false;
		this.giocatore.setCfu(CFU_INIZIALI);
		this.setStanzaCorrente(labirinto.getStanzaIniziale());
	}

	public Stanza getStanzaVincente() {
		return labirinto.getStanzaFinale();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return stanzaCorrente;
	}
	
	public Labirinto getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
	
	public void setFinita(boolean finita) {
		this.finita = finita;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return getStanzaCorrente() == labirinto.getStanzaFinale();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		giocatore.setCfu(cfu);	
	}

	public boolean giocatoreIsVivo() {
		return this.giocatore.getCfu() != 0;
	}	
}
