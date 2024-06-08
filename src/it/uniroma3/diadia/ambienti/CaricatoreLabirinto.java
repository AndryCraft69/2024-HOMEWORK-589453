package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";  

	/* prefisso di una singola riga di testo contenente tutte le specifiche delle stanze buie */
	private static final String STANZE_BUIE_MARKER = "Stanze buie: ";
	
	/* prefisso di una singola riga di testo contenente tutte le specifiche delle stanze bloccate */
	private static final String STANZE_BLOCCATE_MARKER = "Stanze bloccate: "; 

	/* prefisso di una singola riga di testo contenente tutte le specifiche delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER = "Stanze magiche: "; 

	/* prefisso di una singola riga di testo contenente tutti le specifiche dei maghi da posizionare nel formato <nome> <nomeAttrezzo> <pesoAttrezzo> <nomeStanza> */
	private static final String MAGHI_MARKER = "Maghi: ";    

	/* prefisso di una singola riga di testo contenente tutti le specifiche delle streghe da posizionare nel formato <nome> <nomeStanza> */
	private static final String STREGHE_MARKER = "Streghe: ";  

	/* prefisso di una singola riga di testo contenente tutti le specifiche dei cani da posizionare nel formato <nome> <ciboPreferito> <nomeAttrezzo> <pesoAttrezzo> <nomeStanza> */
	private static final String CANI_MARKER = "Cani: ";    

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";  

	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite: ";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;

	private Map<String, Stanza> nome2stanza;

	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaMaghi();
			this.leggiECreaStreghe();
			this.leggiECreaCani();
			this.leggiInizialeEVincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private void leggiECreaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);

		for(String specificaCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String ciboPreferito = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaCane)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("cibo preferito."));
				ciboPreferito = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il cane "+nomeCane+"."));
				nomeStanza = scannerLinea.next();
			}				
			collocaCane(nomeCane, ciboPreferito, nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}

	}
	private void collocaCane(String nomeCane, String ciboPreferito, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			AbstractPersonaggio cane = new Cane(nomeCane, ciboPreferito, new Attrezzo(nomeAttrezzo, peso));
			check(isStanzaValida(nomeStanza),"Cane "+ nomeCane+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).setPersonaggio(cane);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}

	private void leggiECreaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);

		for(String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare la strega "+nomeStrega+"."));
				nomeStanza = scannerLinea.next();
			}				
			collocaStrega(nomeStrega, nomeStanza);
		}

	}
	private void collocaStrega(String nomeStrega, String nomeStanza) throws FormatoFileNonValidoException {
		AbstractPersonaggio strega = new Strega(nomeStrega, nomeStanza);
		check(isStanzaValida(nomeStanza),"Strega "+ nomeStrega+" non collocabile: stanza " +nomeStanza+" inesistente");
		this.nome2stanza.get(nomeStanza).setPersonaggio(strega);
	}

	private void leggiECreaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);

		for(String specificaMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaMago)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare il mago "+nomeMago+"."));
				nomeStanza = scannerLinea.next();
			}				
			collocaMago(nomeMago, nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}

	}
	private void collocaMago(String nomeMago, String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			AbstractPersonaggio mago = new Mago(nomeMago, new Attrezzo(nomeAttrezzo, peso));
			check(isStanzaValida(nomeStanza),"Mago "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).setPersonaggio(mago);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String specificheStanzeMagiche = this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String specificaStanzaMagica : separaStringheAlleVirgole(specificheStanzeMagiche)) {
			String nomeStanzaMagica = null;
			String sogliaMagica = null;
			
			try (Scanner scannerDiLinea = new Scanner(specificaStanzaMagica)) {
				nomeStanzaMagica = scannerDiLinea.next();
				sogliaMagica = scannerDiLinea.next();
				
				Stanza stanzaMagica = new StanzaMagica(nomeStanzaMagica, Integer.parseInt(sogliaMagica));
				this.nome2stanza.put(nomeStanzaMagica, stanzaMagica);
			} 
			catch (NumberFormatException e) {
				check(false, "Soglia Magica di "+nomeStanzaMagica+" non valida");
			}
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specificaStanzaBloccata  : separaStringheAlleVirgole(specificheStanzeBloccate)) {
			String nomeStanzaBloccata = null;
			String direzioneBloccata = null;
			String nomeChiave = null;
			
			try (Scanner scannerDiLinea = new Scanner(specificaStanzaBloccata)) {
				nomeStanzaBloccata = scannerDiLinea.next();
				direzioneBloccata = scannerDiLinea.next();
				nomeChiave = scannerDiLinea.next();
				
				Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, Direzione.valueOf(direzioneBloccata), nomeChiave);
				this.nome2stanza.put(nomeStanzaBloccata, stanzaBloccata);
			} 
			catch (NumberFormatException e) {
				check(false, "Peso chiave di "+nomeStanzaBloccata+" non valida");
			}
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificaStanzaBuia  : separaStringheAlleVirgole(specificheStanzeBuie)) {
			String nomeStanzaBuia = null;
			String nomeOggettoCheIlluminaLaStanza = null;
			String pesoOggettoCheIlluminaLaStanza = null;
			
			try (Scanner scannerDiLinea = new Scanner(specificaStanzaBuia)) {
				nomeStanzaBuia = scannerDiLinea.next();
				nomeOggettoCheIlluminaLaStanza = scannerDiLinea.next();
				pesoOggettoCheIlluminaLaStanza = scannerDiLinea.next();
				
				Stanza stanzaBuia = new StanzaBuia(nomeStanzaBuia, new Attrezzo(nomeOggettoCheIlluminaLaStanza, Integer.parseInt(pesoOggettoCheIlluminaLaStanza)));
				this.nome2stanza.put(nomeStanzaBuia, stanzaBuia);
			} 
			catch (NumberFormatException e) {
				check(false, "Peso oggetto che illumina la stanza di "+nomeStanzaBuia+" non valida");
			}
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.nome2stanza.put(nomeStanza, stanza);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(", ");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) { 
				result.add(scannerDiParole.next());
			}
		}
		return result;
	}


	private void leggiInizialeEVincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = null;
		nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.stanzaIniziale = this.nome2stanza.get(nomeStanzaIniziale);
		this.stanzaVincente = this.nome2stanza.get(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.nome2stanza.get(nomeStanza).addAttrezzo(attrezzo);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.nome2stanza.containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {				
				while (scannerDiLinea.hasNext()) {
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dir = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
					String stanzaDestinazione = scannerDiLinea.next();

					impostaUscita(stanzaPartenza, Direzione.valueOf(dir), stanzaDestinazione);
					impostaUscita(stanzaDestinazione, Direzione.valueOf(dir).opposta(), stanzaPartenza);
				}
			} 
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		Stanza partenzaDa = this.nome2stanza.get(stanzaDa);
		Stanza arrivoA = this.nome2stanza.get(nomeA);
		partenzaDa.impostaStanzaAdiacente(dir, arrivoA);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return this.stanzaVincente;
	}
}
