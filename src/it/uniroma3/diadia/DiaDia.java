package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.IOConsole.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Università, ma oggi è diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'è?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissà!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;

	private IO io;

	public DiaDia(IO io) {
		this.partita = new Partita();
		this.io = io;
	}

	public DiaDia(Labirinto labirinto, IO io) {
		this(io);
		this.partita = new Partita(labirinto);
	}

	public void gioca() {
		String istruzione;

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do		
			istruzione = io.leggiRiga();	
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione è eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva(io);

		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		/* N.B. unica istanza di IOConsole
		di cui sia ammessa la creazione */
		Scanner scannerDiLinee = null;
		try{
			scannerDiLinee = new Scanner(System.in);
			IO io = new IOConsole(scannerDiLinee);
			/*
		Labirinto labirinto = new LabirintoBuilder()
		.addStanzaIniziale("LabCampusOne")
		.addStanzaVincente("Biblioteca")
		.addAdiacenza("LabCampusOne", "Biblioteca", "ovest")
		.getLabirinto();
			 */
			//Labirinto labirinto = new Labirinto();
			Labirinto labirinto = Labirinto.newBuilder("labirinto.txt").getLabirinto();
			DiaDia gioco = new DiaDia(labirinto, io);
			gioco.gioca();
		}
		finally {
			scannerDiLinee.close();
		}

	}
}