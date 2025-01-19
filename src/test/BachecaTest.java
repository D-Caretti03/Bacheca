package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Bacheca;
import bachecaAnnunci.Utente;
import exceptions.AnnuncioException;
import exceptions.BachecaException;
import exceptions.UtenteException;
import utilities.Costanti;

class BachecaTest {

	Utente u;
	Annuncio a1, a2;
	Bacheca b;
	int codice = 0;
	
	@BeforeEach
	void testCostruttore() throws UtenteException, AnnuncioException {
		u = new Utente("daniele@gmail.com", "Daniele");
		a1 = new Annuncio(u, 'a', "Libro", 30.5, "Nero", codice++);
		LocalDate data1 = LocalDate.of(2025,  2, 5);
		a2 = new Annuncio(u, 'v', "Telefono", 100, "Nuovo, Nero", data1, codice++);
		b = new Bacheca();
	}
	
	@Test
	void testAggiungiAnnuncio() throws BachecaException {
		assertTrue(b.aggiungiAnnuncio(a1));
		assertEquals(a1, b.getAnnuncio(0));
		assertNotEquals(a1, b.getAnnuncio(1));
		assertTrue(b.aggiungiAnnuncio(a2));
	}
	
	@Test
	void testEliminaAnnuncio() throws BachecaException{
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
		assertTrue(b.rimuoviAnnuncio(u.getEmail(), 0));
	}
	
	@Test
	void testEliminaForEach() throws AnnuncioException {
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
		for (Annuncio a: b) {
			final Exception e = assertThrows(AnnuncioException.class, () -> { a.remove();
			});
		}
	}
	
	@Test
	void testRicerca() throws BachecaException{
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
		assertEquals("[Annuncio [codice=0, utente=[email=daniele@gmail.com, nome=Daniele], tipologia=Acquisto, articolo=Libro, prezzo=30.5, parolaChiave=Nero, scadenza=null], Annuncio [codice=1, utente=[email=daniele@gmail.com, nome=Daniele], tipologia=Vendita, articolo=Telefono, prezzo=100.0, parolaChiave=Nuovo, Nero, scadenza=2025-02-05]]", b.listaAnnunciParolaChiave("Nero, Nuovo").toString());
	}
	
	@Test
	void testPulisciBacheca() throws BachecaException {
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
		assertFalse(b.pulisciBacheca());//non sono presenti date già passate, altrimenti verrebbe sollevata un'eccezione
	}
}
