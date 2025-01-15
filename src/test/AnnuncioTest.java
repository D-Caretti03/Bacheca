package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Utente;
import exceptions.AnnuncioException;
import exceptions.UtenteException;

class AnnuncioTest {

	@Test
	void testCostruttore() throws AnnuncioException, UtenteException {
		Utente u = new Utente("daniele@gmail", "Daniele");
		Annuncio a = new Annuncio(u, 'a', "Libro", 30.5, "", 0);
		LocalDate data1 = LocalDate.of(2025,  1, 10);
		Annuncio b = new Annuncio(u, 'v', "Libro", 20.5, "", data1, 1);
		System.out.println(a.toString());
	}

}
