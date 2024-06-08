package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	private static final String MESSAGGIO_SALUTO_A_VUOTO = "Non c'è nessuno da salutare.";
	
	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			String risposta = personaggio.saluta();
			super.getIo().mostraMessaggio(risposta);
		}
		else {
			super.getIo().mostraMessaggio(MESSAGGIO_SALUTO_A_VUOTO);
		}
	}

}
