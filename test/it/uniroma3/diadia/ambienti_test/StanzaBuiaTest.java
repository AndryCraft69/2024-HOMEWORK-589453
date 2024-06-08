package it.uniroma3.diadia.ambienti_test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	private Attrezzo lanterna = new Attrezzo("lanterna", 0);
	private Stanza stanza = new StanzaBuia("Stanza", lanterna);
	
	@Test
	public void test_getDescrizione_stanzaBuiaSenzaOggetto() {
		assertEquals("Qui c'è un buio pesto", stanza.getDescrizione());
	}
	@Test
	public void test_getDescrizione_stanzaBuiaConOggetto() {
		stanza.addAttrezzo(lanterna);
		assertEquals("Stanza\n" + "Uscite: \n" + "Attrezzi nella stanza: lanterna (0kg) ", stanza.getDescrizione());
	}
}
