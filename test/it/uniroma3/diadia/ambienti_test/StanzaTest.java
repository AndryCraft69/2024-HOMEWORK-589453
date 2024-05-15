package it.uniroma3.diadia.ambienti_test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.*;

import static org.junit.Assert.*;
import org.junit.*;


public class StanzaTest {
	Stanza stanza = new Stanza();
	Stanza stanza1 = new Stanza("Stanza");
	Attrezzo attrezzo = new Attrezzo("piccone", 3);
	Attrezzo attrezzo1 = new Attrezzo("spada", 4);

	
	@Test
	public void test_addAttrezzo_attrezzoNullo() {
		assertEquals(false, stanza.addAttrezzo(null));
	}
	@Test
	public void test_addAttrezzo_attrezzoNonNullo() {
		assertEquals(true, stanza.addAttrezzo(attrezzo));
	}
	@Test
	public void test_addAttrezzo_stanzaPiena() {
		for (int i=1; i<=10; i++) {
			stanza.addAttrezzo(new Attrezzo("piccone" + i, 3));
		}
		assertEquals(false, stanza.addAttrezzo(attrezzo));
	}
	
	@Test
	public void test_getAttrezzo_attrezzoNullo() {
		assertEquals(null, stanza.getAttrezzo(null));
	}
	@Test
	public void test_getAttrezzo_attrezzoPresente() {
		stanza.addAttrezzo(attrezzo);
		assertEquals("piccone", stanza.getAttrezzo("piccone").getNome());
	}
	@Test
	public void test_getAttrezzo_attrezzoNonPresente() {
		stanza.addAttrezzo(attrezzo);
		assertEquals(null, stanza.getAttrezzo("spada"));
	}
	
	@Test
	public void test_getStanzaAdiacente_direzioneNulla() {
		assertEquals(null, stanza.getStanzaAdiacente(null));
	}
	@Test
	public void test_getStanzaAdiacente_stanzaPresenteInQuellaDirezione() {
		stanza.impostaStanzaAdiacente("nord", stanza1);
		assertEquals("Stanza", stanza.getStanzaAdiacente("nord").getNome());
	}
	@Test
	public void test_getStanzaAdiacente_stanzaNonPresenteInQuellaDirezione() {
		stanza.impostaStanzaAdiacente("nord", stanza1);
		assertEquals(null, stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void test_hasAttrezzo_attrezzoNullo() {
		assertEquals(false, stanza.hasAttrezzo(null));
	}
	@Test
	public void test_hasAttrezzo_attrezzoPresente() {
		stanza.addAttrezzo(attrezzo);
		assertEquals(true, stanza.hasAttrezzo(new Attrezzo("piccone", 0)));
	}
	@Test
	public void test_hasAttrezzo_attrezzoNonPresente() {
		stanza.addAttrezzo(attrezzo);
		assertEquals(false, stanza.hasAttrezzo(new Attrezzo("spada", 0)));
	}
	
	@Test
	public void test_removeAttrezzo_attrezzoNullo() {
		assertEquals(false, stanza.removeAttrezzo(null));
	}
	@Test
	public void test_removeAttrezzo_attrezzoPresente() {
		stanza.addAttrezzo(attrezzo);
		assertEquals(true, stanza.removeAttrezzo(attrezzo));
	}
	@Test
	public void test_removeAttrezzo_attrezzoNonPresente() {
		stanza.addAttrezzo(attrezzo);
		assertEquals(false, stanza.removeAttrezzo(attrezzo1));
	}
	
	@Test
	public void test_toString_stanzaVuota() {
		assertEquals("null\n" + "Uscite: \n" + "Attrezzi nella stanza: ", stanza.toString());
	}
	@Test
	public void test_toString_stanzaConNomeUscitaEAttrezzo() {
		stanza1.addAttrezzo(attrezzo);
		stanza1.impostaStanzaAdiacente("nord", null);
		assertEquals("Stanza\n" + "Uscite:  nord\n" + "Attrezzi nella stanza: piccone (3kg) ", stanza1.toString());
	}
	@Test
	public void test_toString_stanzaConNomeMaxUsciteEMaxAttrezzi() {
		for (int i=1; i<=10; i++) {
			stanza1.addAttrezzo(new Attrezzo("piccone" + i, 3));
		}
		stanza1.impostaStanzaAdiacente("nord", null);
		stanza1.impostaStanzaAdiacente("sud", null);
		stanza1.impostaStanzaAdiacente("est", null);
		stanza1.impostaStanzaAdiacente("ovest", null);
		assertEquals("Stanza\n" + "Uscite:  nord sud ovest est\n" + "Attrezzi nella stanza: piccone2 (3kg) piccone3 (3kg) piccone4 (3kg) piccone5 (3kg) piccone6 (3kg) piccone7 (3kg) piccone8 (3kg) piccone9 (3kg) piccone10 (3kg) piccone1 (3kg) ", stanza1.toString());
	}
	
}
