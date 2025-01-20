package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

import bachecaAnnunci.*;
import exceptions.AnnuncioException;
import exceptions.UtenteException;
import io.LetturaScritturaBacheca;

/**
 * Classe test che testa la lettura e scrittura da file
 */
class LetturaScritturaBachecaTest {

	Utente u;
	Annuncio a1, a2;
	Bacheca b;
	
	/**
	 * prima di ogni test viene creata una bacheca e vengono aggiunti degli annunci
	 * @throws UtenteException
	 * @throws AnnuncioException
	 */
	@BeforeEach
	void testInizializzazione() throws UtenteException, AnnuncioException {
		int codice = 1;
		u = new Utente("daniele@gmail", "daniele");
		a1 = new Annuncio(u, 'a', "Libro", 30.5, "Nero", codice++);
		LocalDate data1 = LocalDate.of(2025, 02, 05);
		a2 = new Annuncio(u, 'v', "Telefono", 100.0, "Nuovo, Nero", data1, codice ++);
		b = new Bacheca();
		b.aggiungiAnnuncio(a1);
		b.aggiungiAnnuncio(a2);
	}
	
	/**
	 * viene testato che una bacheca letta da file sia uguale a un'altra bacheca inizializzata da programma con gli stessi campi
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws UtenteException
	 * @throws AnnuncioException
	 */
	@Test
	void testLetturaBacheca() throws ClassNotFoundException, IOException, UtenteException, AnnuncioException {
		String file = "bacheca.txt";
		Bacheca b_file = LetturaScritturaBacheca.letturaBacheca(file);
		assertEquals(b.toString(), b_file.toString());
	}
	
	/**
	 * viene testato che la scrittura su file abbia esito positivo
	 * @throws IOException
	 */
	@Test
	void testScritturaBacheca() throws IOException {
		String file = "bacheca_out.txt";
		assertTrue(LetturaScritturaBacheca.scritturaBacheca(b, file));
	}

}
