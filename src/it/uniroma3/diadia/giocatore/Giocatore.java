package it.uniroma3.diadia.giocatore;

public class Giocatore {
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		borsa = new Borsa();
	}
	
	public Giocatore (int cfu) {
		this();
		this.cfu = cfu;
	}
	
	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}	
	
	public Borsa getBorsa() {
		return borsa;
	}
	
	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}
}
