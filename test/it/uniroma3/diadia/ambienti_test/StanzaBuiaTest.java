package it.uniroma3.diadia.ambienti_test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {

	@Test
	public void test_getDescrizione_stanzaBuiaSenzaOggetto() {
		Stanza stanza = new StanzaBuia("Stanza", "lanterna");
		assertEquals("Qui c'è un buio pesto", stanza.getDescrizione());
	}
	@Test
	public void test_getDescrizione_stanzaBuiaConOggetto() {
		Stanza stanza = new StanzaBuia("Stanza", "lanterna");
		stanza.addAttrezzo(new Attrezzo("lanterna", 3));
		assertEquals("Stanza\n" + "Uscite: \n" + "Attrezzi nella stanza: lanterna (3kg) ", stanza.getDescrizione());
	}
}
