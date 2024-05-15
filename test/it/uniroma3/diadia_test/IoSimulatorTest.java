package it.uniroma3.diadia_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IoSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;

public class IoSimulatorTest {

	@Test
	public void test_PartitaCasuale() {		
		List<String> lista = new ArrayList<String>();
		lista.add("aiuto");
		lista.add("guarda");
		lista.add("prendi osso");
		lista.add("guarda");
		lista.add("vai est");
		lista.add("vai est");
		lista.add("posa osso");
		lista.add("guarda");
		lista.add("fine");
		
		IoSimulator ioSimulator = new IoSimulator(lista);
		DiaDia simulazione = new DiaDia(new Labirinto(), ioSimulator);
		simulazione.gioca();
		
		List<String> elencoMessaggiStampati = ioSimulator.getElencoMessaggiStampati();
		
		assertEquals(""+
				"Ti trovi nell'Università, ma oggi è diversa dal solito...\n" +
				"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'è?\n"+
				"I locali sono popolati da strani personaggi, " +
				"alcuni amici, altri... chissà!\n"+
				"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
				"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
				"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
				"Per conoscere le istruzioni usa il comando 'aiuto'.", elencoMessaggiStampati.get(0));
		assertEquals("vai aiuto prendi posa fine guarda ", elencoMessaggiStampati.get(1));
		assertEquals("Atrio\n" + "Uscite:  nord sud ovest est\n" + "Attrezzi nella stanza: osso (1kg) ", elencoMessaggiStampati.get(2));
		assertEquals("Atrio\n" + "Uscite:  nord sud ovest est\n" + "Attrezzi nella stanza: ", elencoMessaggiStampati.get(3));
		assertEquals("Aula N11\n" + "Uscite:  ovest est\n" + "Attrezzi nella stanza: ", elencoMessaggiStampati.get(4));
		assertEquals("Laboratorio Campus\n" + "Uscite:  ovest est\n" + "Attrezzi nella stanza: ", elencoMessaggiStampati.get(5));
		assertEquals("Laboratorio Campus\n" + "Uscite:  ovest est\n" + "Attrezzi nella stanza: osso (1kg) ", elencoMessaggiStampati.get(6));
	}

}
