package it.uniroma3.diadia.giocatore_test;

import static org.junit.Assert.*;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {

	@Test
	public void test_addAttrezzo_attrezzoNullo() {
		Borsa borsa = new Borsa();
		assertEquals(false, borsa.addAttrezzo(null));
	}
	@Test
	public void test_addAttrezzo_attrezzoNonNullo() {
		Borsa borsa = new Borsa();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		assertEquals(true, borsa.addAttrezzo(attrezzo));
	}
	@Test
	public void test_addAttrezzo_borsaPiena() {
		Borsa borsa = new Borsa();
		for (int i=1; i<=10; i++) {
			Attrezzo attrezzo = new Attrezzo("piccone" + i, 3);
			borsa.addAttrezzo(attrezzo);
		}
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		assertEquals(false, borsa.addAttrezzo(attrezzo));
	}
	@Test
	public void test_addAttrezzo_superatoLimitePeso() {
		Borsa borsa = new Borsa(0);
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		assertEquals(false, borsa.addAttrezzo(attrezzo));
	}
	
	@Test
	public void test_getAttrezzo_attrezzoNullo() {
		Borsa borsa = new Borsa();
		assertEquals(null, borsa.getAttrezzo(null));
	}
	@Test
	public void test_getAttrezzo_attrezzoPresente() {
		Borsa borsa = new Borsa();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		borsa.addAttrezzo(attrezzo);
		assertEquals("piccone", borsa.getAttrezzo("piccone").getNome());
	}
	@Test
	public void test_getAttrezzo_attrezzoNonPresente() {
		Borsa borsa = new Borsa();
		assertEquals(null, borsa.getAttrezzo("piccone"));
	}

	@Test
	public void test_hasAttrezzo_attrezzoNullo() {
		Borsa borsa = new Borsa();
		assertEquals(false, borsa.hasAttrezzo(null));
	}
	@Test
	public void test_hasAttrezzo_attrezzoPresente() {
		Borsa borsa = new Borsa();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		borsa.addAttrezzo(attrezzo);
		assertEquals(true, borsa.hasAttrezzo("piccone"));
	}
	@Test
	public void test_hasAttrezzo_attrezzoNonPresente() {
		Borsa borsa = new Borsa();
		assertEquals(false, borsa.hasAttrezzo("piccone"));
	}
	
	@Test
	public void test_isEmpty_borsaVuota() {
		Borsa borsa = new Borsa();
		assertEquals(true, borsa.isEmpty());
	}
	@Test
	public void test_isEmpty_borsaConAttrezzi() {
		Borsa borsa = new Borsa();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		borsa.addAttrezzo(attrezzo);
		assertEquals(false, borsa.isEmpty());
	}
	
	@Test
	public void test_removeAttrezzo_attrezzoNullo() {
		Borsa borsa = new Borsa();
		assertEquals(null, borsa.removeAttrezzo(null));
	}
	@Test
	public void test_removeAttrezzo_attrezzoPresente() {
		Borsa borsa = new Borsa();
		Attrezzo attrezzo = new Attrezzo("piccone", 3);
		borsa.addAttrezzo(attrezzo);
		assertEquals("piccone", borsa.removeAttrezzo("piccone").getNome());
	}
	@Test
	public void test_removeAttrezzo_attrezzoNonPresente() {
		Borsa borsa = new Borsa();
		Attrezzo attrezzo = new Attrezzo("spada", 3);
		borsa.addAttrezzo(attrezzo);
		assertEquals(null, borsa.removeAttrezzo("piccone"));
	}
}
