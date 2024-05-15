package it.uniroma3.diadia.comandi_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;

public class ComandoPosaTest {
	private Partita partita = new Partita();
	private Giocatore giocatore = new Giocatore();
	private Borsa borsa = new Borsa();
	private Comando comando = new ComandoPosa();
	private Stanza stanza = new Stanza();
	
	@Before
	public void setUp() {
		giocatore.setBorsa(borsa);
		partita.setGiocatore(giocatore);
		partita.setStanzaCorrente(stanza);
	}
	
	@Test
	public void test_esegui_oggettoNonPresenteInBorsa() {		
		comando.setParametro("piccone");
		comando.esegui(partita);
		assertFalse(stanza.hasAttrezzo(new Attrezzo("piccone", 0)));
	}
	@Test
	public void test_esegui_stanzaPiena() {	
		borsa.addAttrezzo(new Attrezzo("piccone", 3));
		
		for (int i=1; i<=10; i++) {
			Attrezzo attrezzo = new Attrezzo("piccone" + i, 3);
			stanza.addAttrezzo(attrezzo);
		}
		
		comando.setParametro("piccone");
		comando.esegui(partita);
		assertFalse(stanza.hasAttrezzo(new Attrezzo("piccone", 0)));
		assertTrue(borsa.hasAttrezzo(new Attrezzo("piccone", 0)));
	}
	@Test
	public void test_esegui_oggettoPosato() {
		borsa.addAttrezzo(new Attrezzo("piccone", 3));
		comando.setParametro("piccone");
		comando.esegui(partita);
		assertTrue(stanza.hasAttrezzo(new Attrezzo("piccone", 0)));
		assertFalse(borsa.hasAttrezzo(new Attrezzo("piccone", 0)));
	}

}
