package main;

import java.time.LocalDate;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Bacheca;
import bachecaAnnunci.Utente;
import commandLine.InterfacciaRigaComando;
import exceptions.AnnuncioException;
import exceptions.BachecaException;
import exceptions.UtenteException;
import gui.BachecaGui;
/**Classe main dove viene inizializzata una bacheca di esempio e passata alle funzioni grafiche
 * 
 */
public class main {
	public static Bacheca bacheca;
	private static int cod = 1;
	public static void main(String[] args) throws BachecaException, AnnuncioException, UtenteException{
		Utente utente1 = new Utente("dani@gmail.com", "Dani");
		Utente utente2 = new Utente("Luca@gmail.com", "Luca");
		Utente utente3 = new Utente("giio@gmail.com", "Giio");
		
		LocalDate data1 = LocalDate.of(2025, 8, 2);
		LocalDate data2 = LocalDate.of(2026, 10, 23);
		
		Annuncio annuncio1 = new Annuncio(utente1, 'v', "Collana", 50.0, "Gioielli, oro, diamanti, lucida, brillante", data2, cod++);
		Annuncio annuncio2 = new Annuncio(utente2, 'v', "Orecchini", 20.0, "Gioielli", data1,cod++);
		Annuncio annuncio3 = new Annuncio(utente3, 'a', "Bello mondo", 12.5, "Libri", cod++);
		
		bacheca = new Bacheca();
		bacheca.aggiungiAnnuncio(annuncio1);
		bacheca.aggiungiAnnuncio(annuncio2); 
		bacheca.aggiungiAnnuncio(annuncio3);
		
		interfacciaGrafica();
		interfacciaRigaComando();
	}
	/**Viene chiamata la funzione per visualizzare ed eseguire operazioni su un'interfaccia da riga di comando
	 * 
	 * @throws AnnuncioException
	 * @throws UtenteException
	 * @throws BachecaException
	 */
	private static void interfacciaRigaComando() throws AnnuncioException, UtenteException, BachecaException {
		new InterfacciaRigaComando(bacheca, cod).start();
	}
	
	/**
	 * Viene chiamata la funzione per visualizzare ed eseguire operazioni su una GUI
	 * @throws BachecaException
	 */
	private static void interfacciaGrafica() throws BachecaException {
		new BachecaGui(bacheca);
	}
}
