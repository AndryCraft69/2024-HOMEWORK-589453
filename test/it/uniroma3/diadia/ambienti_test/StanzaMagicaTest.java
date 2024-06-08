package it.uniroma3.diadia.ambienti_test;

import static org.junit.Assert.*;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.Test;

public class StanzaMagicaTest {
	Stanza stanza = new StanzaMagica("Stanza Magica", 1);

	@Test
	public void test_addAttrezzo_sogliaNonSuperata() {
		stanza.addAttrezzo(new Attrezzo("piccone", 3));
		assertEquals("Stanza Magica\n" + "Uscite: \n" + "Attrezzi nella stanza: piccone (3kg) ", stanza.toString());
	}
	@Test
	public void test_addAttrezzo_sogliaSuperata() {
		stanza.addAttrezzo(new Attrezzo("piccone", 3));
		stanza.addAttrezzo(new Attrezzo("piccone", 3));
		assertEquals("Stanza Magica\n" + "Uscite: \n" + "Attrezzi nella stanza: piccone (3kg) enoccip (6kg) ", stanza.toString());
	}
	

}
