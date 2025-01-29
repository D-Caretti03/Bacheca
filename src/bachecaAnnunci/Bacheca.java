package bachecaAnnunci;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import exceptions.BachecaException;
import utilities.Costanti;

/**
 * La <strong>classe Bacheca </strong>si occupa di definire il costruttore e i metodi degli oggetti di tipo Bacheca
 * 
 * <p>
 * 
 * L' implements Iterable permette alla classe di essere percorsa elemento per elemento grazie a un ciclo 
 * (tipicamente for-each), grazie all' override del metodo Iterator
 * 
 * @param bacheca 	ArrayList di oggetti di tipo Annuncio
 * @param login		oggetto di tipo Utente che contiene le informazioni dell' utente che ha fatto l'accesso
 */
public class Bacheca implements Iterable<Annuncio>{
	private ArrayList<Annuncio> bacheca;
	public Utente login;
	
	/**
	 * Costruttore dell' oggetto Bacheca, che inizializza l' ArrayList di Annunci
	 */
	public Bacheca() {
		super();
		bacheca = new ArrayList<Annuncio>();
	}
	
	/**
	 * Metodo per aggiungiere un Annuncio alla bacheca e ritorna true se ha successo
	 * @param a		oggetto annuncio che verrà aggiunto alla bacheca
	 * @return		ritorna un valore booleano, true se l' aggiunta è andata a buon fine, altrimenti false
	 */
	public boolean aggiungiAnnuncio(Annuncio a) {
		return bacheca.add(a);
	}

	/**
	 * Metodo che seleziona nella nostra bacheca quegli annunci che contengono determinate parole chiave
	 * passete come parametro
	 * 
	 * @param parolaChiave	Stringa che contiene le parole chiave separate da una virgola ","
	 * @param annunci 		ArrayList che viene ritornato in base alle parole chiave, può anche essere vuoto
	 * @return 				Il metodo ritornerà, se non ci sono state eccezioni, un ArrayList di Annuni
	 * @throws BachecaException		Eccezione che si verifica in determinate condizioni
	 */
	public ArrayList<Annuncio> listaAnnunciParolaChiave(String parolaChiave) throws BachecaException{
		if(parolaChiave == null) throw new BachecaException(Costanti.ECC_PAROLA_CHIAVE_NULL);
		if(parolaChiave.isBlank()) return this.bacheca;
		ArrayList<Annuncio> annunci = new ArrayList<Annuncio>();
		String[] pChiave = parolaChiave.split(", ");
		for(Annuncio a: bacheca) {
			String pChiaveAnn = a.getParolaChiave().toLowerCase();
			HashSet<String> paroleChiaveAnn = new HashSet<>(Arrays.asList(pChiaveAnn.split(", ")));
			for(String parola: pChiave) {
				parola = parola.trim().toLowerCase();
				if(paroleChiaveAnn.contains(parola) && !annunci.contains(a))
					annunci.add(a);
			}
		}
		return annunci;
	}

	/**
	 * Override del metodo Iterator, che permette di ritornare un oggetto di tipo bacheca Iterabile.
	 * 
	 * @return ritorna l' oggetto iterabile Bacheca
	 */
	@Override
	public Iterator<Annuncio> iterator() {
		return this.bacheca.iterator();
	}

	/**
	 * Metodo che ritorna un annuncio avente codice identificativo uguale al parametro in ingresso
	 * 
	 * @param i 		intero che contiene un numero intero che va a identificare un Annuncio
	 * @param trovato	Oggetto di tipo annuncio inizialemnte impostato a null 
	 * @return			ritorna un oggetto di tipo Annuncio avente codice uguale a quello del parametro
	 * @throws BachecaException  	Eccezione che si verifica in determinate condizioni
	 */
	public Annuncio getAnnuncio(int i) throws BachecaException{
		if(i < 0) throw new BachecaException(Costanti.ECC_COD_NEG);
		Annuncio trovato = null;
		for(Annuncio a: bacheca) {
			if(a.getCodice()==i) trovato = a;
		}
		return trovato;
	}

	/**
	 * Metodo che rimuove un determinato un annuncio, come parametri ha una Stringa e un intero, che servono per
	 * identificare il prodotto da rimuovere, se presente, e per capire se si è proprietari di quell'annuncio 
	 * @param email		Stringa contentente la mail dell' utente che ha fatto il login
	 * @param codice	intero che serve per identificare quale annuncio eliminare
	 * @param elim		oggetto di tipo annuncio inizialmente impostato a null
	 * @return
	 * @throws BachecaException
	 */
	public boolean rimuoviAnnuncio(String email, int codice) throws BachecaException {
		Annuncio elim = null;
		if(!controlloCodiceBacheca(codice))
			throw new BachecaException(Costanti.ECC_CODICE_ERR);
		for(Annuncio a: bacheca) {
			Utente u = a.getUtente();
			if(!(u.getEmail().equals(email)) && codice == a.getCodice())
				throw new BachecaException(Costanti.ECC_NON_PROPRIETARIO);
			else
				elim = getAnnuncio(codice);
		}
		return bacheca.remove(elim);
	}
	
	/**
	 * Metodo per capire se esiste un articolo con un determinato codice
	 * @param codice	intero contenente il codice identificativo 
	 * @return			ritona un valore true se presente, altrimenti false
	 */
	public boolean controlloCodiceBacheca(int codice) {
		for(Annuncio a: bacheca) {
			if(a.getCodice() == codice)
				return true;
		}
		return false;
	}

	/**
	 * Override del metodo toString con lo scopo di restituire tutti gli annunci della bacheca
	 * 
	 * @return ritorna una stringa vuota, dato che, in questo caso ci interessa solo mandare a schermo le informazioni
	 */
	@Override
	public String toString() {
		return "Bacheca:\n" +  bacheca.stream().map(Object::toString).collect(java.util.stream.Collectors.joining("\n"));
	}
	
	/**
	 * Metodo per eliminare dalla bacheca gli annunci di vendita scaduti
	 * @return 	ritornerà true se almeno un elemento è stato rimosso, altrimenti false
	 */
	public boolean pulisciBacheca(){
		LocalDate oggi = LocalDate.now(); 
		ArrayList<Annuncio> elim = new ArrayList<Annuncio>();
		for(Annuncio a: bacheca) {
			if(a.getDate() != null) {
				if(a.getDate().isBefore(oggi)) {
					elim.add(a);
				}
			}
		}
		return bacheca.removeAll(elim);
	}
	

	/**
	 * Metodo che serve per restituire tutta la bacheca
	 * @return	ritona un ArrayList di Annunci
	 */
	public ArrayList<Annuncio> getBacheca(){
		return this.bacheca;
	}
}
