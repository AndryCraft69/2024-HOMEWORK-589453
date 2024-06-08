package it.uniroma3.diadia.comandi_test;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

public class ComandoVaiTest {
	private Partita partita = new Partita();
	private Comando comando = new ComandoVai();
	private Stanza stanza = new Stanza();
	IO io = new IOConsole(new Scanner(System.in));
	
	@Before
	public void setUp() {
		partita.setStanzaCorrente(stanza);
	}
	
	@Test
	public void test_esegui_direzioneNulla() {
		comando.setParametro(null);
		comando.setIO(io);
		comando.esegui(partita);
		assertEquals(stanza, partita.getStanzaCorrente());
		//il test controlla che non abbia cambiato stanza, com'è giusto che sia
	}
	@Test
	public void test_esegui_direzioneInesistente() {
		comando.setParametro("nord");
		comando.setIO(io);
		comando.esegui(partita);
		assertEquals(stanza, partita.getStanzaCorrente());
		//il test controlla che non abbia cambiato stanza, com'è giusto che sia
	}
	@Test
	public void test_esegui_stanzaPresenteInQuellaDirezione() {
		Stanza stanza1 = new Stanza("Stanza1");
		Stanza stanza2 = new Stanza("Stanza2");
		partita.setStanzaCorrente(stanza1);
		stanza1.impostaStanzaAdiacente(Direzione.nord, stanza2);
		comando.setParametro("nord");
		comando.setIO(io);
		comando.esegui(partita);
		assertEquals(stanza2, partita.getStanzaCorrente());
	}
	
}
