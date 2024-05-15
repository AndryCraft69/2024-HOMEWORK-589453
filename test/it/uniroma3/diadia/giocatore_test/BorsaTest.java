package it.uniroma3.diadia.giocatore_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNome;
import it.uniroma3.diadia.attrezzi.ComparatorePerPesoENome;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {
	Borsa borsa = new Borsa();
	Borsa borsa0 = new Borsa(0);
	Attrezzo attrezzo = new Attrezzo("piccone", 3);

	@Test
	public void test_addAttrezzo_attrezzoNullo() {
		assertEquals(false, borsa.addAttrezzo(null));
	}
	@Test
	public void test_addAttrezzo_attrezzoNonNullo() {
		assertEquals(true, borsa.addAttrezzo(attrezzo));
	}
	@Test
	public void test_addAttrezzo_borsaPiena() {
		for (int i=1; i<=10; i++) {
			borsa.addAttrezzo(new Attrezzo("piccone" + i, 3));
		}
		assertEquals(false, borsa.addAttrezzo(attrezzo));
	}
	@Test
	public void test_addAttrezzo_superatoLimitePeso() {
		assertEquals(false, borsa0.addAttrezzo(attrezzo));
	}
	
	@Test
	public void test_getAttrezzo_attrezzoNullo() {
		assertEquals(null, borsa.getAttrezzo(null));
	}
	@Test
	public void test_getAttrezzo_attrezzoPresente() {
		borsa.addAttrezzo(attrezzo);
		assertEquals("piccone", borsa.getAttrezzo("piccone").getNome());
	}
	@Test
	public void test_getAttrezzo_attrezzoNonPresente() {
		assertEquals(null, borsa.getAttrezzo("piccone"));
	}

	@Test
	public void test_hasAttrezzo_attrezzoNullo() {
		assertEquals(false, borsa.hasAttrezzo(null));
	}
	@Test
	public void test_hasAttrezzo_attrezzoPresente() {
		borsa.addAttrezzo(attrezzo);
		assertEquals(true, borsa.hasAttrezzo(attrezzo));
	}
	@Test
	public void test_hasAttrezzo_attrezzoNonPresente() {
		assertEquals(false, borsa.hasAttrezzo(new Attrezzo("piccone", 0)));
	}
	
	@Test
	public void test_isEmpty_borsaVuota() {
		assertEquals(true, borsa.isEmpty());
	}
	@Test
	public void test_isEmpty_borsaConAttrezzi() {
		borsa.addAttrezzo(attrezzo);
		assertEquals(false, borsa.isEmpty());
	}
	
	@Test
	public void test_removeAttrezzo_attrezzoNullo() {
		assertEquals(null, borsa.removeAttrezzo(null));
	}
	@Test
	public void test_removeAttrezzo_attrezzoPresente() {
		borsa.addAttrezzo(attrezzo);
		assertEquals("piccone", borsa.removeAttrezzo("piccone").getNome());
	}
	@Test
	public void test_removeAttrezzo_attrezzoNonPresente() {
		Attrezzo attrezzo = new Attrezzo("spada", 3);
		borsa.addAttrezzo(attrezzo);
		assertEquals(null, borsa.removeAttrezzo("piccone"));
	}
	
	@Test
	public void test_getContenutoOrdinatoPerPeso_borsaVuota() {
		assertEquals(new ArrayList<Attrezzo>(), borsa.getContenutoOrdinatoPerPeso());
	}
	@Test
	public void test_getContenutoOrdinatoPerPeso_attrezziConPesoInOrdineDecrescente() {
		Attrezzo attrezzo1 = new Attrezzo("1", 4);
		Attrezzo attrezzo2 = new Attrezzo("2", 3);
		Attrezzo attrezzo3 = new Attrezzo("3", 2);
		Attrezzo attrezzo4 = new Attrezzo("4", 1);
		
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo3);
		borsa.addAttrezzo(attrezzo4);
		
		List<Attrezzo> lista = new ArrayList<Attrezzo>();
		lista.add(attrezzo4);
		lista.add(attrezzo3);
		lista.add(attrezzo2);
		lista.add(attrezzo1);
		
		assertEquals(lista, borsa.getContenutoOrdinatoPerPeso());
	}
	@Test
	public void test_getContenutoOrdinatoPerPeso_attrezziConPesoInOrdineCasuale() {
		Attrezzo attrezzo1 = new Attrezzo("1", 2);
		Attrezzo attrezzo2 = new Attrezzo("2", 3);
		Attrezzo attrezzo3 = new Attrezzo("3", 1);
		Attrezzo attrezzo4 = new Attrezzo("4", 4);
		
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo3);
		borsa.addAttrezzo(attrezzo4);
		
		List<Attrezzo> lista = new ArrayList<Attrezzo>();
		lista.add(attrezzo3);
		lista.add(attrezzo1);
		lista.add(attrezzo2);
		lista.add(attrezzo4);
		
		assertEquals(lista, borsa.getContenutoOrdinatoPerPeso());
	}
	
	@Test
	public void test_getContenutoOrdinatoPerNome_borsaVuota() {
		assertEquals(new TreeSet<Attrezzo>(), borsa.getContenutoOrdinatoPerNome());
	}
	@Test
	public void test_getContenutoOrdinatoPerNome_attrezziConNomeInOrdineAlfabeticoAlContrario() {
		Attrezzo spada = new Attrezzo("spada", 0);
		Attrezzo piccone = new Attrezzo("piccone", 0);
		Attrezzo pala = new Attrezzo("pala", 0);
		Attrezzo torcia = new Attrezzo("torcia", 0);
		
		borsa.addAttrezzo(torcia);
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(piccone);
		borsa.addAttrezzo(pala);
		
		
		SortedSet<Attrezzo> set = new TreeSet<Attrezzo>(new ComparatorePerNome());
		set.add(pala);
		set.add(piccone);
		set.add(spada);
		set.add(torcia);
		
		assertEquals(set, borsa.getContenutoOrdinatoPerNome());
	}
	@Test
	public void test_getContenutoOrdinatoPerNome_attrezziConNomeInOrdineCasuale() {
		Attrezzo spada = new Attrezzo("spada", 0);
		Attrezzo piccone = new Attrezzo("piccone", 0);
		Attrezzo pala = new Attrezzo("pala", 0);
		Attrezzo torcia = new Attrezzo("torcia", 0);
		
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(piccone);
		borsa.addAttrezzo(pala);
		borsa.addAttrezzo(torcia);
		
		SortedSet<Attrezzo> set = new TreeSet<Attrezzo>(new ComparatorePerNome());
		set.add(pala);
		set.add(piccone);
		set.add(spada);
		set.add(torcia);
		
		assertEquals(set, borsa.getContenutoOrdinatoPerNome());
	}
	
	@Test
	public void test_getContenutoRaggruppatoPerPeso_borsaVuota() {
		Map<Integer, Set<Attrezzo>> mappa = new TreeMap<Integer, Set<Attrezzo>>();
		assertEquals(mappa, borsa.getContenutoRaggruppatoPerPeso());
	}
	@Test
	public void test_getContenutoRaggruppatoPerPeso_pesiDiversi() {
		Attrezzo spada = new Attrezzo("spada", 1);
		Attrezzo piccone = new Attrezzo("piccone", 2);
		Attrezzo pala = new Attrezzo("pala", 3);
		Attrezzo torcia = new Attrezzo("torcia", 4);
		
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(piccone);
		borsa.addAttrezzo(pala);
		borsa.addAttrezzo(torcia);
				
		
		assertEquals("[spada (1kg)]", borsa.getContenutoRaggruppatoPerPeso().get(1).toString());
		assertEquals("[piccone (2kg)]", borsa.getContenutoRaggruppatoPerPeso().get(2).toString());
		assertEquals("[pala (3kg)]", borsa.getContenutoRaggruppatoPerPeso().get(3).toString());
		assertEquals("[torcia (4kg)]", borsa.getContenutoRaggruppatoPerPeso().get(4).toString());
	}
	@Test
	public void test_getContenutoRaggruppatoPerPeso_pesiCasuali() {
		Attrezzo spada = new Attrezzo("spada", 2);
		Attrezzo piccone = new Attrezzo("piccone", 2);
		Attrezzo pala = new Attrezzo("pala", 1);
		Attrezzo torcia = new Attrezzo("torcia", 4);
		
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(piccone);
		borsa.addAttrezzo(pala);
		borsa.addAttrezzo(torcia);
				
		
		assertEquals("[pala (1kg)]", borsa.getContenutoRaggruppatoPerPeso().get(1).toString());
		assertEquals("[piccone (2kg), spada (2kg)]", borsa.getContenutoRaggruppatoPerPeso().get(2).toString());
		assertEquals("[torcia (4kg)]", borsa.getContenutoRaggruppatoPerPeso().get(4).toString());
	}
	
	@Test
	public void test_getSortedSetOrdinatoPerPeso_borsaVuota() {
		assertEquals(new TreeSet<Attrezzo>(), borsa.getSortedSetOrdinatoPerPeso());
	}
	@Test
	public void test_getSortedSetOrdinatoPerPeso_attrezziConNomeInOrdineCasuale() {
		Attrezzo spada = new Attrezzo("spada", 4);
		Attrezzo piccone = new Attrezzo("piccone", 2);
		Attrezzo pala = new Attrezzo("pala", 2);
		Attrezzo torcia = new Attrezzo("torcia", 1);
		
		borsa.addAttrezzo(spada);
		borsa.addAttrezzo(piccone);
		borsa.addAttrezzo(pala);
		borsa.addAttrezzo(torcia);
		
		SortedSet<Attrezzo> set = new TreeSet<Attrezzo>(new ComparatorePerPesoENome());
		set.add(torcia);
		set.add(pala);
		set.add(piccone);
		set.add(spada);
		
		assertEquals(set, borsa.getSortedSetOrdinatoPerPeso());
	}
}
