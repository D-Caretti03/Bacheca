package guiVista;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Bacheca;
import bachecaAnnunci.Utente;
import main.main;
import utilities.Costanti;
import exceptions.AnnuncioException;
import exceptions.BachecaException;
import exceptions.UtenteException;

import javax.swing.JOptionPane;

/**
 * In questa classe eseguiremo le azioni richieste dall' ActionEvent del ControlloBacheca, oltre a creare i
 * vat JComponent che saranno delle finestre in cui inesrirmo i dati che l' utente vuole, a patto che rispettino
 * le exception
 * 
 * @param nomArt, cat, nomeUtente, emailUtente oggetti di tipo JTextField in cui inesriremo il dato richiesto
 * @param check		oggetto di tipo JCheckBox selezzioneremo se vogliamo creare un annuncio di vendita, altriimenti di acquisto
 * @param prezzo	oggetto di tipo JTextField nel quale inseriamo il prezzo dell' annuncio
 * @param data 		oggetto di tipo JTextField nel quale inseriamo la data di scadenza dell' annuncio
 * 
 * @param addPanel  	oggetto di tipo addPanel che sarà la finestra che comparirà quando vorremo inserire un annuncio
 * @param cercaPanel	oggetto di tipo addPanel che sarà la finestra che comparirà quando vorremo cercare nella bacheca un annuncio con la parola chiave inserita
 * @param inputUtente	oggetto di tipo addPanel che sarà la finestra che comparirà quando vorremo fare il login
 * 
 * @param cod 		variabile intera statica che viene incrementata per dare un id unico a ogni prodotto
 * @param tipologia variabile char che sta a indicare se si vuole aggiungere un annuncio di vendita o di acquisto
 */
public class DialogoContatto {
	private JTextField nomArt, cat, nomeUtente, emailUtente;
	private JCheckBox check;
	
	private JTextField fPrezzo;
	private JTextField prezzo;
	private JTextField data;
	private JComponent[] addPanel;
	private JComponent[] cercaPanel;
	private JComponent[] inputUtente;
	private static int cod = 4;
	private char tipologia;
	
	/**
	 * Costruttore della nostra classe che imposta le finestre in cui inserire diversi dati
	 */
	public DialogoContatto() {
		nomArt = new JTextField(20);
		cat = new JTextField(20); 
		check = new JCheckBox("Vendita");
		prezzo = new JTextField(15);
		data = new JTextField(15);
		nomeUtente = new JTextField(15);
		emailUtente = new JTextField(15);
		
		addPanel = new JComponent[] { new JLabel("Articolo"), nomArt, new JLabel("Parole chiave (separate da ,)"), cat, check, new JLabel("Prezzo"), prezzo, new JLabel("Data Scadenza (AAAA-MM-GG)"), data};
		cercaPanel = new JComponent[] { new JLabel("Articolo"), nomArt};
		inputUtente = new JComponent[] { new JLabel("Nome Utente"), nomeUtente, new JLabel("e-mail Utente"), emailUtente};
	}
  
	/**
	 * Metodo con lo scopo di aggiungiere un articolo alla bacheca e restituire l' arrayList con le parole chiave simili
	 * 
	 * @param msg	String che contiene un messaggio
	 * @param u		oggetto di tipo utente contentente le informazione dell' utente che ha fatto il login
	 * @return		ArrayList che ritorna la bacheca aggiornata
	 * @throws UtenteException
	 * @throws AnnuncioException
	 * @throws BachecaException
	 */
	public ArrayList<Annuncio> getInputs(String msg, Utente u) throws UtenteException, AnnuncioException, BachecaException {	
		Annuncio a = null;
		int result = JOptionPane.showConfirmDialog(null, addPanel, msg, JOptionPane.CANCEL_OPTION);		
		if(check.isSelected()) {
			if(data.getText().isBlank()) throw new AnnuncioException(Costanti.ECC_DATA_NULL);
			tipologia = 'v';
			LocalDate dataF = LocalDate.parse(data.getText());
			a = new Annuncio(u, tipologia, nomArt.getText(), Double.parseDouble(prezzo.getText()), cat.getText(), dataF, cod++);
			
		}else {
			tipologia = 'a';
			a = new Annuncio(u, tipologia, nomArt.getText(), Double.parseDouble(prezzo.getText()), cat.getText(), cod++);
			main.bacheca.listaAnnunciParolaChiave(cat.getText());
		}
		if (result == JOptionPane.OK_OPTION && tipologia == 'a') { 
			main.bacheca.aggiungiAnnuncio(a);
			return main.bacheca.listaAnnunciParolaChiave(cat.getText());
		} 
		else if(result == JOptionPane.OK_OPTION && tipologia == 'v') {
			main.bacheca.aggiungiAnnuncio(a);
			return main.bacheca.getBacheca();
		}
		else {
			return main.bacheca.getBacheca();
		}
	}
	
	/**
	 * Metodo con lo scopo di ritornare un ArrayList contenente colo gli annunci con parola chiave richiesta dall utente
	 * @param msg	String che contiene un messaggio
	 * @return		ArrayList che ritorna solo gli annunci aventi come parola chiave la parola/e rischieste dall utente
	 * @throws BachecaException
	 */
	public ArrayList<Annuncio> getCerca(String msg) throws BachecaException {
		ArrayList<Annuncio> res;  
		int result = JOptionPane.showConfirmDialog(null, cercaPanel, msg, JOptionPane.CANCEL_OPTION);		
		if (result == JOptionPane.OK_OPTION) { 
			res =  main.bacheca.listaAnnunciParolaChiave(nomArt.getText());
			return res;
		}
		else {
			return null;
		}
		
	}
	
	/**
	 * Metodo con lo scopo di fare il login dell' utente
	 * @param msg	String che contiene un messaggio
	 * @return		un oggetto utente è ritornato, che va a indicare chi ha fatto il login
	 * @throws UtenteException
	 */
	public Utente inputUtente(String msg) throws UtenteException {
		int result = JOptionPane.showConfirmDialog(null, inputUtente, msg, JOptionPane.CANCEL_OPTION);	
		if (result == JOptionPane.OK_OPTION) { 
			return new Utente(emailUtente.getText() ,nomeUtente.getText());
		}else {
			return null;
		}
	}
	
	/**
	 * Metodo con lo scopo di eliminare gli annunci scaduti e quindi ritornare la bacheca aggiornata
	 * @param msg	String che contiene un messaggio
	 * @return		ArrayList che ritorna la bacheca aggiornata, senza gli annunci scaduti
	 * @throws BachecaException
	 */
	public ArrayList<Annuncio> inputPulisci(String msg) throws BachecaException{
			main.bacheca.pulisciBacheca();
			return main.bacheca.getBacheca();
	}
	

	/**
	 * Metodo che ritorna la bacheca allo stato in cui è chiamato il metodo 
	 * @param msg	String che contiene un messaggio
	 * @return		ArrayList che ritorna la bacheca allo stato in cui è chiamato il metodo
	 * @throws BachecaException
	 */
	public ArrayList<Annuncio> mostraBacheca(String msg) throws BachecaException{
			return main.bacheca.getBacheca();
	}
	
	
}
