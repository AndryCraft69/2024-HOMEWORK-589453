package it.uniroma3.diadia.ambienti_test;

import static org.junit.Assert.*;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Test;

public class StanzaBloccataTest {
	private String nomeChiave = "chiave";
	Stanza stanza = new StanzaBloccata("Stanza", Direzione.nord, nomeChiave);
	
	public void aggiungiChiave() {
		stanza.addAttrezzo(new Attrezzo(nomeChiave, 0));
	}

	@Test
	public void test_getStanzaAdiacente_stanzaConChiave() {
		aggiungiChiave();
		Stanza stanzaANord = new Stanza("Seconda stanza");
		stanza.impostaStanzaAdiacente(Direzione.nord, stanzaANord);
		assertEquals(stanzaANord, stanza.getStanzaAdiacente(Direzione.nord));
	}
	@Test
	public void test_getStanzaAdiacente_stanzaSenzaChiave() {
		Stanza stanzaANord = new Stanza("Seconda stanza");
		stanza.impostaStanzaAdiacente(Direzione.nord, stanzaANord);
		assertEquals(stanza, stanza.getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	public void test_getDescrizione_stanzaConChiave() {
		aggiungiChiave();
		assertEquals("Stanza\n" + "Uscite: \n"+ "Attrezzi nella stanza: chiave (0kg) ", stanza.getDescrizione());
	}
	@Test
	public void test_getDescrizione_stanzaSenzaChiave() {
		assertEquals("Stanza\n" + "Uscite: \n"+ "Attrezzi nella stanza: \n" + "La direzione nord è bloccata", stanza.getDescrizione());
	}

}
