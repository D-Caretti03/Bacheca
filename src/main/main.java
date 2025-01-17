package main;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Bacheca;
import bachecaAnnunci.Utente;
import commandLine.InterfacciaRigaComando;
import exceptions.AnnuncioException;
import exceptions.BachecaException;
import exceptions.UtenteException;
import gui.BachecaGui;

public class main {
	public static Bacheca bacheca;
	private static int cod = 1;
	public static void main(String[] args) throws BachecaException, AnnuncioException, UtenteException{
		Utente utente1 = new Utente("dani@gmail.com", "Dani");
		Utente utente2 = new Utente("Luca@gmail.com", "Luca");
		Utente utente3 = new Utente("giio@gmail.com", "Giio");
		
		Annuncio annuncio1 = new Annuncio(utente1, 'v', "Collana", 50.0, "Gioielli", cod++);
		Annuncio annuncio2 = new Annuncio(utente2, 'v', "Orecchini", 20.0, "Gioielli", cod++);
		Annuncio annuncio3 = new Annuncio(utente1, 'a', " Bello mondo", 12.5, "Libri", cod++);
		
		bacheca = new Bacheca();
		bacheca.aggiungiAnnuncio(annuncio1);
		bacheca.aggiungiAnnuncio(annuncio2); 
		bacheca.aggiungiAnnuncio(annuncio3);
		
		interfacciaGrafica();
		interfacciaRigaComando();
	}
	
	private static void interfacciaRigaComando() throws AnnuncioException, UtenteException, BachecaException {
		new InterfacciaRigaComando(bacheca, cod).start();
	}
	
	private static void interfacciaGrafica() throws BachecaException {
		new BachecaGui(bacheca);
	}
}
