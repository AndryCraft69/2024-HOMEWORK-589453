package it.uniroma3.diadia.comandi_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {
		
	private String istruzione;
	private FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
	private Comando comando;
	 
	public void setUp(String istruzione) {
		comando = factory.costruisciComando(istruzione);
	}
	
	@Test
	public void test_costruisciComando_comandoVai() {
		istruzione = "vai";
		setUp(istruzione);
		assertEquals("vai", comando.getNome());
	}
	@Test
	public void test_costruisciComando_comandoPrendi() {
		istruzione = "prendi";
		setUp(istruzione);
		assertEquals("prendi", comando.getNome());
	}
	@Test
	public void test_costruisciComando_comandoPosa() {
		istruzione = "posa";
		setUp(istruzione);
		assertEquals("posa", comando.getNome());
	}
	@Test
	public void test_costruisciComando_comandoAiuto() {
		istruzione = "aiuto";
		setUp(istruzione);
		assertEquals("aiuto", comando.getNome());
	}
	@Test
	public void test_costruisciComando_comandoFine() {
		istruzione = "fine";
		setUp(istruzione);
		assertEquals("fine", comando.getNome());
	}
	@Test
	public void test_costruisciComando_comandoGuarda() {
		istruzione = "guarda";
		setUp(istruzione);
		assertEquals("guarda", comando.getNome());
	}
	@Test
	public void test_costruisciComando_comandoNonValido() {
		istruzione = "comando non valido";
		setUp(istruzione);
		assertEquals(null, comando.getNome());
	}

}
