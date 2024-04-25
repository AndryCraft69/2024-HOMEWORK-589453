package it.uniroma3.diadia.comandi_test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	Partita partita = new Partita();
	Comando comando = new ComandoVai();
	Stanza stanza = new Stanza();
	
	@Before
	public void setUp() {
		partita.setStanzaCorrente(stanza);
	}
	
	@Test
	public void test_esegui_direzioneNulla() {
		comando.setParametro(null);
		comando.esegui(partita);
		assertEquals(stanza, partita.getStanzaCorrente());
		//il test controlla che non abbia cambiato stanza, com'è giusto che sia
	}
	@Test
	public void test_esegui_direzioneInesistente() {
		comando.setParametro("nord");
		comando.esegui(partita);
		assertEquals(stanza, partita.getStanzaCorrente());
		//il test controlla che non abbia cambiato stanza, com'è giusto che sia
	}
	@Test
	public void test_esegui_stanzaPresenteInQuellaDirezione() {
		Stanza stanza1 = new Stanza("Stanza1");
		Stanza stanza2 = new Stanza("Stanza2");
		partita.setStanzaCorrente(stanza1);
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		comando.setParametro("nord");
		comando.esegui(partita);
		assertEquals(stanza2, partita.getStanzaCorrente());
	}
	
}
