package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Utente;
import exceptions.AnnuncioException;
import exceptions.UtenteException;

/**
 * Classe test dove viene testato il costruttore di Annuncio
 */
class AnnuncioTest {

	/**
	 * testCostruttore testa che Annuncio lanci correttamente un'eccezione
	 * @throws AnnuncioException
	 * @throws UtenteException
	 */
	@Test
	void testCostruttore() throws AnnuncioException, UtenteException {
		Utente u = new Utente("daniele@gmail.com", "Daniele");
		Annuncio a = new Annuncio(u, 'a', "Libro", 30.5, "", 0);
		LocalDate data1 = LocalDate.of(2025,  1, 10);
		final Exception e = assertThrows(AnnuncioException.class, () -> {Annuncio b = new Annuncio(u, 'a', "Libro", 20.5, "", data1, 1);
		});
	}

}
