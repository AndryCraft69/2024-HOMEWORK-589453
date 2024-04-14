package it.uniroma3.diadia.ambienti_test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.*;

import static org.junit.Assert.*;
import org.junit.*;


public class StanzaTest {
	
	@Test
	public void test_addAttrezzo_attrezzoNullo() {
		Stanza stanza = new Stanza();
		assertEquals(false, stanza.addAttrezzo(null));
	}
	@Test
	public void test_addAttrezzo_attrezzoNonNullo() {
		Stanza stanza = new Stanza();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		assertEquals(true, stanza.addAttrezzo(attrezzo));
	}
	@Test
	public void test_addAttrezzo_stanzaPiena() {
		Stanza stanza = new Stanza();
		for (int i=1; i<=10; i++) {
			Attrezzo attrezzo = new Attrezzo("piccone" + i, 3);
			stanza.addAttrezzo(attrezzo);
		}
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		assertEquals(false, stanza.addAttrezzo(attrezzo));
	}
	
	@Test
	public void test_getAttrezzo_attrezzoNullo() {
		Stanza stanza = new Stanza();
		assertEquals(null, stanza.getAttrezzo(null));
	}
	@Test
	public void test_getAttrezzo_attrezzoPresente() {
		Stanza stanza = new Stanza();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		stanza.addAttrezzo(attrezzo);
		assertEquals("piccone", stanza.getAttrezzo("piccone").getNome());
	}
	@Test
	public void test_getAttrezzo_attrezzoNonPresente() {
		Stanza stanza = new Stanza();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		stanza.addAttrezzo(attrezzo);
		assertEquals(null, stanza.getAttrezzo("spada"));
	}
	
	@Test
	public void test_getStanzaAdiacente_direzioneNulla() {
		Stanza stanza = new Stanza();
		assertEquals(null, stanza.getStanzaAdiacente(null));
	}
	@Test
	public void test_getStanzaAdiacente_stanzaPresenteInQuellaDirezione() {
		Stanza stanza = new Stanza();
		Stanza stanza1 = new Stanza("Stanza");
		String direzione = "nord";
		stanza.impostaStanzaAdiacente(direzione, stanza1);
		assertEquals("Stanza", stanza.getStanzaAdiacente("nord").getNome());
	}
	@Test
	public void test_getStanzaAdiacente_stanzaNonPresenteInQuellaDirezione() {
		Stanza stanza = new Stanza();
		Stanza stanza1 = new Stanza("Stanza");
		String direzione = "nord";
		stanza.impostaStanzaAdiacente(direzione, stanza1);
		assertEquals(null, stanza.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void test_hasAttrezzo_attrezzoNullo() {
		Stanza stanza = new Stanza();
		assertEquals(false, stanza.hasAttrezzo(null));
	}
	@Test
	public void test_hasAttrezzo_attrezzoPresente() {
		Stanza stanza = new Stanza();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		stanza.addAttrezzo(attrezzo);
		assertEquals(true, stanza.hasAttrezzo("piccone"));
	}
	@Test
	public void test_hasAttrezzo_attrezzoNonPresente() {
		Stanza stanza = new Stanza();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		stanza.addAttrezzo(attrezzo);
		assertEquals(false, stanza.hasAttrezzo("spada"));
	}
	
	@Test
	public void test_removeAttrezzo_attrezzoNullo() {
		Stanza stanza = new Stanza();
		assertEquals(false, stanza.removeAttrezzo(null));
	}
	@Test
	public void test_removeAttrezzo_attrezzoPresente() {
		Stanza stanza = new Stanza();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		stanza.addAttrezzo(attrezzo);
		assertEquals(true, stanza.removeAttrezzo(attrezzo));
	}
	@Test
	public void test_removeAttrezzo_attrezzoNonPresente() {
		Stanza stanza = new Stanza();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		stanza.addAttrezzo(attrezzo);
		Attrezzo attrezzo1 = new Attrezzo("spada", 4);
		assertEquals(false, stanza.removeAttrezzo(attrezzo1));
	}
	
	@Test
	public void test_toString_stanzaVuota() {
		Stanza stanza = new Stanza();
		assertEquals("null\n" + "Uscite: \n" + "Attrezzi nella stanza: ", stanza.toString());
	}
	@Test
	public void test_toString_stanzaConNomeUscitaEAttrezzo() {
		Stanza stanza = new Stanza("Stanza");
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		stanza.addAttrezzo(attrezzo);
		String[] uscite = {"nord"};
		stanza.setDirezioni(uscite);
		assertEquals("Stanza\n" + "Uscite:  nord\n" + "Attrezzi nella stanza: piccone (3kg) ", stanza.toString());
	}
	@Test
	public void test_toString_stanzaConNomeMaxUsciteEMaxAttrezzi() {
		Stanza stanza = new Stanza("Stanza");
		for (int i=1; i<=10; i++) {
			Attrezzo attrezzo = new Attrezzo("piccone" + i, 3);
			stanza.addAttrezzo(attrezzo);
		}
		String[] uscite = {"nord", "sud", "est", "ovest"};
		stanza.setDirezioni(uscite);
		assertEquals("Stanza\n" + "Uscite:  nord sud est ovest\n" + "Attrezzi nella stanza: piccone1 (3kg) piccone2 (3kg) piccone3 (3kg) piccone4 (3kg) piccone5 (3kg) piccone6 (3kg) piccone7 (3kg) piccone8 (3kg) piccone9 (3kg) piccone10 (3kg) ", stanza.toString());
	}
	
}
