package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.ambienti.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_DOPO_TRASFERIMENTO = "[La strega ti ha trasferito in un'altra stanza]";
	private static final String RISATA_DELLA_STREGA = "MUAHAHAHAHA!";
	private static final String PRESENTAZIONE_DI_DEFAULT = "Sono una maga cattiva!";

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	public Strega(String nome) {
		super(nome, PRESENTAZIONE_DI_DEFAULT);
	}
	
	@Override
	public String agisci(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Stanza stanzaConMenoAttrezzi = null;
		Stanza stanzaConPiùAttrezzi = null;
		
		int attrezziMin = 0;
		int attrezziMax = 0;
		boolean primaStanzaVisitata = true;
		for(Stanza stanza : stanzaCorrente.getMapStanzeAdiacenti().values()) {
			if(stanza != null) {
				if(primaStanzaVisitata) {
					attrezziMin = stanza.getAttrezzi().size();
					attrezziMax = stanza.getAttrezzi().size();
					stanzaConPiùAttrezzi = stanza;
					stanzaConMenoAttrezzi = stanza;
					primaStanzaVisitata = false;
				}
				else {
					if(stanza.getAttrezzi().size() < attrezziMin) {
						attrezziMin = stanza.getAttrezzi().size();
						stanzaConMenoAttrezzi = stanza;
					}
					if(stanza.getAttrezzi().size() > attrezziMax) {
						attrezziMax = stanza.getAttrezzi().size();
						stanzaConPiùAttrezzi = stanza;
					}
				}
			}
		}
		
		if(super.haSalutato) {
			partita.setStanzaCorrente(stanzaConPiùAttrezzi);
		}
		else {
			partita.setStanzaCorrente(stanzaConMenoAttrezzi);
		}
		
		return MESSAGGIO_DOPO_TRASFERIMENTO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		
		return RISATA_DELLA_STREGA;
	}

}
