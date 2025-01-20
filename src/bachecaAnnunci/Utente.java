package bachecaAnnunci;

import exceptions.UtenteException;
import utilities.Costanti;

/**
 * La <strong>classe utente </strong>si occupa di definire il costruttore e i metodi degli oggetti di tipo Utente
 * 
 * 
 * @param email Stringa che contiene la mail dell' oggetto Utente
 * @param nome  Stringa che contiene il nome dell' oggetto Utente
 */
public class Utente {
	private String email;
	private String nome;
	
	/**
	 * Questo è il costruttore di Utente, che riceve 2 parametri 2 Stringhe) che sono i dati con la quale 
	 * l' utente esegue l' accesso
	 * <p>
	 * Lanciamo un eccezione se non vengono rispettate tutte le condizioni necessarie per la creazione
	 * di un oggetto Utente
	 * 
	 * @param email		Srtinga con la quale l' utente esegue l' accesso
	 * @param nome		Stringa con la quale l' utente esegue l' accesso
	 * @throws UtenteException	Eccezione che viene lanciate se si verifica una determinata condizione
	 */
	public Utente(String email, String nome) throws UtenteException{
		super();
		if(email == null) throw new  UtenteException(Costanti.ECC_EMAIL_NULL);
		if(email.isBlank()) throw new UtenteException(Costanti.ECC_EMAIL_VUOTA);
		if(!email.contains("@") || !email.contains(".")) throw new UtenteException(Costanti.ECC_EMAIL_ERRATA);
		if(nome == null) throw new UtenteException(Costanti.ECC_NOME_NULL);
		if(nome.isBlank()) throw new UtenteException(Costanti.ECC_NOME_VUOTO);
		this.email = email;
		this.nome = nome;
	}

	/**
	 * Override del metodo toString con lo scopo di restituire le iformazioni di un utente
	 * <p>
	 * Utile perchè ci permette, tramite il metodo toString di restituire una stringa editata dai programmatori
	 * 
	 * @return ritorna la stringa editata contentente email e nome dell oggetto Utente
	 */
	@Override
	public String toString() {
		return "[email=" + email + ", nome=" + nome + "]";
	}

	/**
	 * Metodo per ritornarare una stringa che contiene la mail dell Utente
	 * 
	 * @return ritorna una stringa contenente la mail dell oggetto Utente
	 */
	public String getEmail() {
		return this.email;
	}
	

	/**
	 * Metodo per ritornarare una stringa che contiene il nome dell Utente
	 * 
	 * @return ritorna una stringa contenente il nome dell oggetto Utente
	 */
	public String getNome() {
		return this.nome;
	}
	
}
