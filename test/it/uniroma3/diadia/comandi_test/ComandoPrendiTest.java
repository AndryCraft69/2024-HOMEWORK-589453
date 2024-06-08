package it.uniroma3.diadia.comandi_test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPrendi;

public class ComandoPrendiTest {
	private Partita partita = new Partita();
	private Giocatore giocatore = new Giocatore();
	private Borsa borsa = new Borsa();
	private Comando comando = new ComandoPrendi();
	private Stanza stanza = new Stanza();
	IO io = new IOConsole(new Scanner(System.in));
	
	@Before
	public void setUp() {
		giocatore.setBorsa(borsa);
		partita.setGiocatore(giocatore);
		partita.setStanzaCorrente(stanza);
	}
	
	@Test
	public void test_esegui_oggettoNonPresenteNellaStanza() {		
		comando.setParametro("piccone");
		comando.setIO(io);
		comando.esegui(partita);
		assertFalse(borsa.hasAttrezzo(new Attrezzo("piccone", 0)));
	}
	@Test
	public void test_esegui_borsaPiena() {	
		stanza.addAttrezzo(new Attrezzo("piccone", 3));
		
		for (int i=1; i<=10; i++) {
			Attrezzo attrezzo = new Attrezzo("piccone" + i, 3);
			borsa.addAttrezzo(attrezzo);
		}
		
		comando.setParametro("piccone");
		comando.setIO(io);
		comando.esegui(partita);
		assertFalse(borsa.hasAttrezzo(new Attrezzo("piccone", 3)));
		assertTrue(stanza.hasAttrezzo(new Attrezzo("piccone", 3)));
	}
	@Test
	public void test_esegui_attrezzoTroppoPesante() {
		stanza.addAttrezzo(new Attrezzo("piccone", 11));
		
		comando.setParametro("piccone");
		comando.setIO(io);
		comando.esegui(partita);
		assertFalse(borsa.hasAttrezzo(new Attrezzo("piccone", 11)));
		assertTrue(stanza.hasAttrezzo(new Attrezzo("piccone", 11)));
	}
	@Test
	public void test_esegui_oggettoPreso() {
		stanza.addAttrezzo(new Attrezzo("piccone", 3));
		comando.setParametro("piccone");
		comando.setIO(io);
		comando.esegui(partita);
		assertTrue(borsa.hasAttrezzo(new Attrezzo("piccone", 3)));
		assertFalse(stanza.hasAttrezzo(new Attrezzo("piccone", 3)));
	}

}
