package bachecaAnnunci;

import java.time.LocalDate;

import bachecaAnnunci.Annuncio;
import exceptions.AnnuncioException;
import utilities.Costanti;

/**
 * La <strong>classe Annuncio</strong> si occupa di definire il costruttore e i metodi degli oggetti di tipo Annuncio
 * 
 * <p>
 * 
 * In questa classe usiamo l' Overloadin dei costruttori, il che vuol dire che, in base ai parametri passati,
 * l'oggetto istanziato da questa classe potrà essere differente, in particolare se inseriamo la Data di scadenza
 * 
 * @param utente		array list contenente email e nome dell'utente
 * @param tipologia		variabile booleana che indica la tipologia di annuncio (True acquisto, False vendita)
 * @param articolo		stringa che indica il nome dell'articolo
 * @param prezzo		prezzo dell'articolo
 * @param parolaChiave	eventuali parole chiave per la ricerca di un articolo
 * @param scadenza		data di scadenza in caso di vendita
 * @param codice		codice identificativo dell'annuncio 
 */

public class Annuncio{
	private int codice = 0;
	private Utente utente;
	private char tipologia;
	private String articolo;
	private double prezzo;	
	private String parolaChiave = "";
	private LocalDate scadenza;
	

	/**
	 * Istanza di un nuovo Annuncio di vendita che imposta tutte le variabili
	 * @param utente		array list contenente email e nome dell'utente
	 * @param tipologia		variabile booleana che indica la tipologia di annuncio (True acquisto, False vendita)
	 * @param articolo		stringa che indica il nome dell'articolo
	 * @param prezzo		prezzo dell'articolo
	 * @param parolaChiave	eventuali parole chiave per la ricerca di un articolo
	 * @param scadenza		data di scadenza in caso di vendita
	 * @param codice		codice identificativo dell'annuncio 
	 * @throws AnnuncioException Eccezione che si verifica in determinate condizioni
	 */
	public Annuncio(Utente utente, char tipologia, String articolo, double prezzo, String parolaChiave,
			LocalDate scadenza, int codice) throws AnnuncioException{
		super();
		this.utente = utente;
		if(tipologia != 'a' && tipologia != 'v') throw new AnnuncioException(Costanti.ECC_TIPO_ERR);
		if(articolo == null) throw new AnnuncioException(Costanti.ECC_ART_NULL);
		if(articolo.isBlank()) throw new AnnuncioException(Costanti.ECC_ART_VUOTO);
		if(prezzo < 0) throw new AnnuncioException(Costanti.ECC_PREZZO_NEG);
		if(parolaChiave == null) throw new AnnuncioException(Costanti.ECC_PAROLA_CHIAVE_NULL);
		LocalDate oggi = LocalDate.now();
		if(scadenza == null)throw new AnnuncioException(Costanti.ECC_DATA_NULL);
		if(scadenza.isBefore(oggi)) throw new AnnuncioException(Costanti.ECC_DATA_PASSATA);
		if(tipologia == 'a' && scadenza != null) throw new AnnuncioException(Costanti.ECC_DATA_ACQ);
		this.tipologia = tipologia;
		this.articolo = articolo;
		this.prezzo = prezzo;
		this.parolaChiave = parolaChiave;
		this.scadenza = scadenza;
		this.codice = codice;
	}
	
	/**
	 * istanza di un nuovo annuncio di Acquisto che imposta tutte le variabili tranne la scadenza
	 * 
	 * @param utente		array list contenente email e nome dell'utente
	 * @param tipologia		variabile booleana che indica la tipologia di annuncio (True acquisto, False vendita)
	 * @param articolo		stringa che indica il nome dell'articolo
	 * @param prezzo		prezzo dell'articolo
	 * @param parolaChiave	eventuali parole chiave per la ricerca di un articolo
	 * @param codice		codice identificativo dell'annuncio 
	 * @throws AnnuncioException Eccezione che si verifica in determinate condizioni
	 */
	public Annuncio(Utente utente, char tipologia, String articolo, double prezzo, String parolaChiave, int codice) throws AnnuncioException{
		super();
		this.utente = utente;
		if(tipologia == 'v') throw new AnnuncioException(Costanti.ECC_NODATA_VEND);
		if(tipologia != 'a' && tipologia != 'v') throw new AnnuncioException(Costanti.ECC_TIPO_ERR);
		if(articolo == null) throw new AnnuncioException(Costanti.ECC_ART_NULL);
		if(articolo.isBlank()) throw new AnnuncioException(Costanti.ECC_ART_VUOTO);
		if(prezzo < 0) throw new AnnuncioException(Costanti.ECC_PREZZO_NEG);
		if(parolaChiave == null) throw new AnnuncioException(Costanti.ECC_PAROLA_CHIAVE_NULL);
		this.tipologia = tipologia;
		this.articolo = articolo;
		this.prezzo = prezzo;
		this.parolaChiave = parolaChiave;
		this.codice = codice;
	}

	/**
	 * Metodo che ritorna un intero, che è il codice identificativo dell' Annuncio
	 * @return	ritrona il codice dell' articolo
	 */
	public int getCodice() {
		return this.codice;
	}


	/**
	 * Metodo che ritorna un carattere, che serve per capire se un Annuncio è di vendita o di acquisto
	 * @return	ritrona 'v' se l'articolo è di vendita o 'a' se è di acquisto
	 */
	public char getTipologia() {
		return this.tipologia;
	}


	/**
	 * Metodo che ritorna una Stringa, che contiene nessuna una o più parole chiave dell' Articolo
	 * @return	ritrona una Stringa che contiene le parole chiave dell' articolo
	 */
	public String getParolaChiave() {
		return this.parolaChiave;
	}

	/**
	 * Metodo che ritorna un LocalDate, che è una data usata per individuare quando un Articolo di vendita, scade
	 * @return	se l' articolo è di tipo vendita ritorna la data di scadenza, altrimenti null
	 */
	public LocalDate getDate(){
		if(this.tipologia == 'v') return this.scadenza;
		else return null;
	}
	
	/**
	 * Metodo che ritorna una Stringa, che da un nome all'articolo dell'Annuncio
	 * @return	ritrona una stringa contenente il nome dell' articolo dell' Annuncio
	 */
	public String getArticolo(){	
		return this.articolo;
	}
	

	/**
	 * Metodo che ritorna un double, che è il prezzo di un aritcolo ii vendita o la somma disposti a pagare 
	 * per un acquisto
	 * @return	ritorna un double, che è il prezzo di vendita o di acquisto di un Annuncio
	 */
	public double getPrezzo(){	
		return this.prezzo;
	}
	

	/**
	 * Override del metodo toString con lo scopo di restituire tutti i dati dell' annuncio
	 * 
	 * @return ritorna una stringa contentete i dati dell' articolo editato dai programmatori
	 */
	@Override
	public String toString() {
		String tipo = tipologia == 'a' ? "Acquisto":"Vendita";
		return "Annuncio [codice=" + codice + ", utente=" + utente.toString() + ", tipologia=" + tipo + ", articolo="
				+ articolo + ", prezzo=" + prezzo + ", parolaChiave=" + parolaChiave + ", scadenza=" + scadenza + "]";
	}

	/**
	 * Metodo per ritornare un oggetto di tipo Utente che va a identificare chi è il proprietario dell' Annuncio
	 * 
	 * @return ritorna un oggetto di tipo Utente, contenente nome e mail del proprietario dell' Annuncio
	 */
	public Utente getUtente() {
		return this.utente;
	}
	
	/**Metodo per non permettere la rimozioni di annunci tramite la foreach nella bacheca
	 * 
	 * @throws AnnuncioException
	 */
	public void remove() throws AnnuncioException {
		throw new AnnuncioException(Costanti.ECC_REM_NON_SUPP);
	}
	
}
