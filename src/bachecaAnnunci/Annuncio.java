package bachecaAnnunci;

import java.time.LocalDate;

import bachecaAnnunci.Annuncio;
import exceptions.AnnuncioException;
import utilities.Costanti;

public class Annuncio{
	private int codice = 0;
	private Utente utente;
	private boolean tipologia;
	private String articolo;
	private double prezzo;	
	private String parolaChiave = "";
	private LocalDate scadenza;
	
	/**
	 * istanzia un nuovo annuncio
	 * 
	 * @param utente		array list contenente email e nome dell'utente
	 * @param tipologia		variabile booleana che indica la tipologia di annuncio (True acquisto, False vendita)
	 * @param articolo		stringa che indica il nome dell'articolo
	 * @param prezzo		prezzo dell'articolo
	 * @param parolaChiave	eventuali parole chiave per la ricerca di un articolo
	 * @param scadenza		data di scadenza in caso di vendita
	 * @param codice		codice identificativo dell'annuncio 
	 */
	public Annuncio(Utente utente, boolean tipologia, String articolo, double prezzo, String parolaChiave,
			LocalDate scadenza, int codice) throws AnnuncioException{
		super();
		this.utente = utente;
		this.tipologia = tipologia;
		if(articolo == null) throw new AnnuncioException(Costanti.ECC_ART_NULL);
		if(prezzo < 0) throw new AnnuncioException(Costanti.ECC_PREZZO_NEG);
		if(parolaChiave == null) throw new AnnuncioException(Costanti.ECC_PAROLA_CHIAVE_NULL);
		LocalDate oggi = LocalDate.now();
		if(scadenza.isBefore(oggi)) throw new AnnuncioException(Costanti.ECC_DATA_PASSATA);
		this.articolo = articolo;
		this.prezzo = prezzo;
		this.parolaChiave = parolaChiave;
		this.scadenza = scadenza;
		this.codice = codice;
	}
	
	/**
	 * istanzia un nuovo annuncio di acquisto senza la data di scadenza
	 * 
	 * @param utente
	 * @param tipologia
	 * @param articolo
	 * @param prezzo
	 * @param parolaChiave
	 * @throws AnnuncioException
	 */
	public Annuncio(Utente utente, boolean tipologia, String articolo, double prezzo, String parolaChiave, int codice) throws AnnuncioException{
		super();
		this.utente = utente;
		this.tipologia = tipologia;
		if(articolo == null) throw new AnnuncioException(Costanti.ECC_ART_NULL);
		if(prezzo < 0) throw new AnnuncioException(Costanti.ECC_PREZZO_NEG);
		if(parolaChiave == null) throw new AnnuncioException(Costanti.ECC_PAROLA_CHIAVE_NULL);
		this.articolo = articolo;
		this.prezzo = prezzo;
		this.parolaChiave = parolaChiave;
		this.codice = codice;
	}

	public int getCodice(Annuncio a) {
		return a.codice;
	}

	public boolean getTipologia(Annuncio a) {
		return a.tipologia;
	}

	public String getParolaChiave(Annuncio a) {
		return a.parolaChiave;
	}
	
	
	public LocalDate getDate(Annuncio a){
		if(a.tipologia) return a.scadenza;
		else return null;
	}

	@Override
	public String toString() {
		String tipo = tipologia ? "Acquisto":"Vendita";
		return "Annuncio [codice=" + codice + ", utente=" + utente.toString() + ", tipologia=" + tipo + ", articolo="
				+ articolo + ", prezzo=" + prezzo + ", parolaChiave=" + parolaChiave + ", scadenza=" + scadenza + "]";
	}

	public Utente getUtente(Annuncio a) {
		return a.utente;
	}
	
}
