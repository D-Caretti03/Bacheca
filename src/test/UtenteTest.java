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
 * classe test che testa il costruttore di Utente
 */
class UtenteTest {

	/**
	 * testCostruttore testa che Utente lanci correttamente un'eccezione
	 * @throws UtenteException
	 */
	@Test
	void testCostruttore() throws UtenteException {
		assertThrows(UtenteException.class, () -> {Utente u = new Utente("dani@", "dani");
		});
	}

}
