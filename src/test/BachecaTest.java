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

/**
 * Classe test dove viene testata la bacheca con le sue operazioni
 */
class BachecaTest {

	Utente u;
	Annuncio a1, a2;
	Bacheca b;
	int codice = 0;
	
	/**
	 * prima di ogni test vengono creati e aggiunti degli annunci alla bacheca
	 * @throws UtenteException
	 * @throws AnnuncioException
	 */
	@BeforeEach
	void testCostruttore() throws UtenteException, AnnuncioException {
		u = new Utente("daniele@gmail.com", "Daniele");
		a1 = new Annuncio(u, 'a', "Libro", 30.5, "Nero", codice++);
		LocalDate data1 = LocalDate.of(2025,  2, 5);
		a2 = new Annuncio(u, 'v', "Telefono", 100, "Nuovo, Nero", data1, codice++);
		b = new Bacheca();
	}
	
	/**
	 * viene testata l'aggiunta di un annuncio alla bacheca
	 * @throws BachecaException
	 */
	@Test
	void testAggiungiAnnuncio() throws BachecaException {
		assertTrue(b.aggiungiAnnuncio(a1));
		assertEquals(a1, b.getAnnuncio(0));
		assertNotEquals(a1, b.getAnnuncio(1));
		assertTrue(b.aggiungiAnnuncio(a2));
	}
	
	/**
	 * viene testato che gli annunci vengono eliminati correttamente
	 * @throws BachecaException
	 */
	@Test
	void testEliminaAnnuncio() throws BachecaException{
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
		assertTrue(b.rimuoviAnnuncio(u.getEmail(), 0));
	}
	
	/**
	 * viene testato che in caso di iterazione sulla bacheca, alla chiamata del comando Annuncio.remove venga sollevata un'eccezione
	 * @throws AnnuncioException
	 */
	@Test
	void testEliminaForEach() throws AnnuncioException {
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
		for (Annuncio a: b) {
			final Exception e = assertThrows(AnnuncioException.class, () -> { a.remove();
			});
		}
	}
	
	/**
	 * viene testata la ricerca tramite una stringa
	 * 
	 * @throws BachecaException
	 */
	@Test
	void testRicerca() throws BachecaException{
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
		assertEquals("[Annuncio [codice=0, utente=[email=daniele@gmail.com, nome=Daniele], tipologia=Acquisto, articolo=Libro, prezzo=30.5, parolaChiave=Nero, scadenza=null], Annuncio [codice=1, utente=[email=daniele@gmail.com, nome=Daniele], tipologia=Vendita, articolo=Telefono, prezzo=100.0, parolaChiave=Nuovo, Nero, scadenza=2025-02-05]]", b.listaAnnunciParolaChiave("Nero, Nuovo").toString());
	}
	
	/**
	 * viene testato che pulisciBacheca elimini gli annunci scaduti (in questo caso il risultato è sempre false, perché creando l'annuncio viene sollevata 
	 * un'eccezione in caso di data già scaduta), per un test accurato bisognerebbe tenere aperto il programma dopo le 00:00
	 * e chiamare la funzione
	 * 
	 * @throws BachecaException
	 */
	@Test
	void testPulisciBacheca() throws BachecaException {
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
		assertFalse(b.pulisciBacheca());//non sono presenti date già passate, altrimenti verrebbe sollevata un'eccezione
	}
}
