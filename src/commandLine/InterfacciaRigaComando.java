package commandLine;

import java.time.LocalDate;

import bachecaAnnunci.*;
import exceptions.AnnuncioException;
import exceptions.BachecaException;
import exceptions.UtenteException;
import input.Input;
import utilities.Costanti;

public class InterfacciaRigaComando {
	
	private String email = null;
	private String nome = null;
	private static int codice = 1;
	private char tipologia;
	private String articolo = null;
	private double prezzo;
	private String parolaChiave = "";
	private LocalDate scadenza;
	private Utente u;
	private Annuncio a;
	private Bacheca b = new Bacheca();
	
	
	
	public InterfacciaRigaComando(Bacheca b, int cod) {
		super();
		this.b = b;
		InterfacciaRigaComando.codice=cod;
	}

	public void start() throws AnnuncioException, UtenteException, BachecaException {
		boolean exit = false;
		while(!exit) {
			stampaMenu();
			int scelta = Input.readInt();
			switch(scelta) {
				case 0 -> login();
				case 1 -> aggiungiAnnuncio();
				case 2 -> stampaBacheca();
				case 3 -> eliminaAnnuncio();
				case 4 -> cercaParoleChiave();
				case 5 -> pulisciBacheca();
				case 9 -> exit = true;
				default -> System.out.println("Errore, inserisci una scelta tra quelle proposte");
			}
		}
		exit();
	}

	private void stampaMenu() {
		System.out.println("Menù bacheca annunci: \n");
		System.out.println("0 -- Esegui il login");
		System.out.println("1 -- Aggiungi annuncio");
		System.out.println("2 -- Visualizza bacheca");
		System.out.println("3 -- Elimina un annuncio");
		System.out.println("4 -- Cerca per parole chiave");
		System.out.println("5 -- Pulisci la bacheca dalle vendite scadute");
		System.out.println("9 -- Termina sessione");
	}
	
	private void login() throws UtenteException {
		email = Input.readString("Inserisci la tua email");
		nome = Input.readString("Inserisci il tuo nome");
		u = new Utente(email, nome);
	}
	
	private void aggiungiAnnuncio() throws AnnuncioException, BachecaException, UtenteException {
		if (u == null)
			throw new UtenteException(Costanti.ECC_UTENTE_NULL);
		tipologia = Input.readChar("Inserisci la tipologia (a/v) (acquisto/vendita)");
		articolo = Input.readString("Inserisci il nome dell'articolo");
		prezzo = Input.readDouble("Inserisci il prezzo");
		parolaChiave = Input.readString("Inserisci delle parole chiave separate da una virgola (questo campo si può lasciare vuoto)");
		if(tipologia == 'v') {
			String data = Input.readString("Inserisci la data di scadenza dell'annuncio (AAAA-MM-GG)");
			scadenza = LocalDate.parse(data);
			a = new Annuncio(u, tipologia, articolo, prezzo, parolaChiave, scadenza, codice++);
		}
		else {
			a = new Annuncio(u, tipologia, articolo, prezzo, parolaChiave, codice++);
			stampaListaParoleChiave(parolaChiave);
		}
		b.aggiungiAnnuncio(a);
	}
	
	private void stampaBacheca() {
		b.toString();
	}
	
	private void eliminaAnnuncio() throws BachecaException {
		int cod = Input.readInt("Inserisci il codice dell'annuncio che vuoi rimuovere (ricorda che per rimuovere un annuncio devi esserne il proprietario)");
		b.rimuoviAnnuncio(email, cod);
	}
	
	public void cercaParoleChiave() throws BachecaException {
		String paroleChiave = Input.readString("Inserisci le parole chiave per cercare annunci simili nella bacheca (dividili con , e uno spazio)");
		stampaListaParoleChiave(paroleChiave);
	}
	
	public void stampaListaParoleChiave(String paroleChiave) throws BachecaException {
		System.out.println("\nLista di annunci che combaciano con le parole chiave inserite\n" + b.listaAnnunciParolaChiave(paroleChiave) + "\n");
	}
	
	public void pulisciBacheca() throws BachecaException {
		b.pulisciBacheca();
	}
	
	public void exit() {
		System.out.println("\nUscita dalla sessione Bacheca Annunci: Arrivederci!\n");
		System.exit(1);
	}
	
}
