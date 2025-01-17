package main;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Bacheca;
import bachecaAnnunci.Utente;
import commandLine.InterfacciaRigaComando;
import exceptions.AnnuncioException;
import exceptions.BachecaException;
import exceptions.UtenteException;
import gui.BachecaGui;
import gui.contollo.*;

public class main {
	private static Bacheca bacheca;
	public static void main(String[] args) throws BachecaException, AnnuncioException, UtenteException{
		Utente utente1 = new Utente("dani@gmail.com", "Dani");
		Utente utente2 = new Utente("Luca@gmail.com", "Luca");
		Utente utente3 = new Utente("giio@gmail.com", "Giio");
		
		Annuncio annuncio1 = new Annuncio(utente1, 'v', "Collana", 50.0, "Gioielli", 1);
		Annuncio annuncio2 = new Annuncio(utente2, 'v', "Orecchini", 20.0, "Gioielli", 2);
		Annuncio annuncio3 = new Annuncio(utente1, 'a', " Bello mondo", 12.5, "Libri", 3);
		
		bacheca = new Bacheca();
		bacheca.aggiungiAnnuncio(annuncio1);
		bacheca.aggiungiAnnuncio(annuncio2); 
		bacheca.aggiungiAnnuncio(annuncio3);
		
		//interfacciaGrafica();
		interfacciaRigaComando();
	}
	
	private static void interfacciaRigaComando() throws AnnuncioException, UtenteException, BachecaException {
		new InterfacciaRigaComando().start();
	}
	/*private static void interfacciaGrafica() {	
	private static void interfacciaGrafica() throws BachecaException {
		new BachecaGui(bacheca);
	}*/
}
