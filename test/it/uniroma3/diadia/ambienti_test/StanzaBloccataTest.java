package it.uniroma3.diadia.ambienti_test;

import static org.junit.Assert.*;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Test;

public class StanzaBloccataTest {
	Stanza stanza = new StanzaBloccata("Stanza", "nord", "chiave");
	
	public void setUp() {
		stanza.addAttrezzo(new Attrezzo("chiave", 3));
	}

	@Test
	public void test_getStanzaAdiacente_stanzaConChiave() {
		setUp();
		Stanza stanzaANord = new Stanza("Seconda stanza");
		stanza.impostaStanzaAdiacente("nord", stanzaANord);
		assertEquals(stanzaANord, stanza.getStanzaAdiacente("nord"));
	}
	@Test
	public void test_getStanzaAdiacente_stanzaSenzaChiave() {
		Stanza stanzaANord = new Stanza("Seconda stanza");
		stanza.impostaStanzaAdiacente("nord", stanzaANord);
		assertEquals(stanza, stanza.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void test_getDescrizione_stanzaConChiave() {
		setUp();
		assertEquals("Stanza\n" + "Uscite: \n"+ "Attrezzi nella stanza: chiave (3kg) ", stanza.getDescrizione());
	}
	@Test
	public void test_getDescrizione_stanzaSenzaChiave() {
		assertEquals("Stanza\n" + "Uscite: \n"+ "Attrezzi nella stanza: \n" + "La direzione nord è bloccata", stanza.getDescrizione());
	}

}
