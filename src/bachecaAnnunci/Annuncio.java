package bachecaAnnunci;

import java.time.LocalDate;

import bachecaAnnunci.Annuncio;
import exceptions.AnnuncioException;
import utilities.Costanti;

public class Annuncio{
	private int codice = 0;
	private Utente utente;
	private char tipologia;
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
	 * istanzia un nuovo annuncio di acquisto senza la data di scadenza
	 * 
	 * @param utente
	 * @param tipologia
	 * @param articolo
	 * @param prezzo
	 * @param parolaChiave
	 * @throws AnnuncioException
	 */
	public Annuncio(Utente utente, char tipologia, String articolo, double prezzo, String parolaChiave, int codice) throws AnnuncioException{
		super();
		this.utente = utente;
		if(tipologia != 'a') throw new AnnuncioException(Costanti.ECC_TIPO_ERR);
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

	public int getCodice() {
		return this.codice;
	}

	public char getTipologia() {
		return this.tipologia;
	}

	public String getParolaChiave() {
		return this.parolaChiave;
	}

	public LocalDate getDate(){
		if(this.tipologia == 'v') return this.scadenza;
		else return null;
	}
	public String getArticolo(){	
		return this.articolo;
	}
	
	public double getPrezzo(){	
		return this.prezzo;
	}
	

	@Override
	public String toString() {
		String tipo = tipologia == 'a' ? "Acquisto":"Vendita";
		return "Annuncio [codice=" + codice + ", utente=" + utente.toString() + ", tipologia=" + tipo + ", articolo="
				+ articolo + ", prezzo=" + prezzo + ", parolaChiave=" + parolaChiave + ", scadenza=" + scadenza + "]";
	}

	public Utente getUtente() {
		return this.utente;
	}
	
	public void remove() throws AnnuncioException {
		throw new AnnuncioException(Costanti.ECC_REM_NON_SUPP);
	}
	
}
