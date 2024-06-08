package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita è associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
	private Map<String, Attrezzo> attrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	private AbstractPersonaggio personaggio = null;
	
    
	public Stanza() {        
        this(null);
	}
    
	/**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.attrezzi = new HashMap<>();
        this.stanzeAdiacenti = new HashMap<>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione in cui sarà posta la stanza adiacente.
     * @param stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) { 
    	stanzeAdiacenti.put(direzione, stanza);
    }
    
    public void setPersonaggio(AbstractPersonaggio personaggio) {
    	this.personaggio = personaggio;
    }
    
   	public AbstractPersonaggio getPersonaggio() {
    	return this.personaggio;
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
        return stanzeAdiacenti.get(direzione);
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
    	ArrayList<Attrezzo> attrezzi = new ArrayList<>(this.attrezzi.values());
        return attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(attrezzi.size() != NUMERO_MASSIMO_ATTREZZI) {
	        if (attrezzo != null) {
	        	this.attrezzi.put(attrezzo.getNome(), attrezzo);
	        	return true;
	        }
	        else {
	        	return false;
	        }
    	}
    	else {
    		return false;
    	}
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (Direzione direzione : this.getDirezioni())
    		if (direzione!=null)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo attrezzo : this.getAttrezzi()) {
    		if(attrezzo != null) {
    			risultato.append(attrezzo.toString()+" ");
    		}
    	}
    	if(personaggio!=null) {
    		risultato.append("\nPersonaggio nella stanza: " + this.getPersonaggio().getNome());
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.containsValue(attrezzo);
	}

	/**
     * Restituisce l'attrezzo "attrezzo" se presente nella stanza.
	 * @param attrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzoDaCercare) {
		return this.attrezzi.get(nomeAttrezzoDaCercare);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzoDaRimuovere) {
		if(attrezzoDaRimuovere != null)
			return this.attrezzi.remove(attrezzoDaRimuovere.getNome()) != null;
		else
			return false;
	}


	public Set<Direzione> getDirezioni() {
		return new TreeSet<>(this.stanzeAdiacenti.keySet());
    } 

	public Map<Direzione, Stanza> getMapStanzeAdiacenti() {
		return stanzeAdiacenti;
	}
	
	@Override
	public boolean equals(Object o) {
		Stanza that = (Stanza) o;
		if(o!=null && this.nome!=null) {
			return this.nome.equals(that.getNome());
		}
		else if(this.nome == null && that.getNome()==null) {
			return true;
		}
		else {
			return false;
		}
	}

}