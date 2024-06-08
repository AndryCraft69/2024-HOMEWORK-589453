package it.uniroma3.diadia_test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	@Test
	public void test_isFinita_partitaFinita() {
		Partita partita = new Partita();
		partita.setFinita(true);
		assertEquals(true, partita.isFinita());
	}
	@Test
	public void test_isFinita_partitaNonFinita() {
		Partita partita = new Partita();
		partita.setFinita(false);
		assertEquals(false, partita.isFinita());
	}
	@Test
	public void test_isFinita_CFUAZero(){
		Partita partita = new Partita();
		Giocatore giocatore = new Giocatore();
		giocatore.setCfu(0);
		partita.setGiocatore(giocatore);
		assertEquals(true, partita.isFinita());
	}
	@Test
	public void test_isFinita_CFUNonAZero(){
		Partita partita = new Partita();
		Giocatore giocatore = new Giocatore();
		giocatore.setCfu(1);
		partita.setGiocatore(giocatore);
		assertEquals(false, partita.isFinita());
	}
	@Test
	public void test_isFinita_vinta() {
		Partita partita = new Partita();
		Labirinto labirinto = Labirinto.newBuilder().getLabirinto();
		Stanza stanza = new Stanza();
		labirinto.setStanzaFinale(stanza);
		partita.setLabirinto(labirinto);
		partita.setStanzaCorrente(stanza);
		assertEquals(true, partita.isFinita());
	}
	@Test
	public void test_isFinita_nonVinta() {
		Partita partita = new Partita();
		assertEquals(false, partita.isFinita());
	}
}
