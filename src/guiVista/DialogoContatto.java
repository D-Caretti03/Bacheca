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
	public DialogoContatto() {
// sicerano le componenti grafiche che andranno nella finestra di dialogo
		nomArt = new JTextField(20);
		cat = new JTextField(20); 
		check = new JCheckBox("Vendita");
		prezzo = new JTextField(15);
		data = new JTextField(15);
		nomeUtente = new JTextField(15);
		emailUtente = new JTextField(15);
		
		addPanel = new JComponent[] { new JLabel("Articolo"), nomArt, new JLabel("Parole chiave (separate da ,)"), cat, check, new JLabel("Prezzo"), prezzo, new JLabel("Data (AAAA-MM-GG)"), data};
		cercaPanel = new JComponent[] { new JLabel("Articolo"), nomArt};
		inputUtente = new JComponent[] { new JLabel("Nome Utente"), nomeUtente, new JLabel("e-mail Utente"), emailUtente};
	}
  
	public ArrayList<Annuncio> getInputs(String msg, Utente u) throws UtenteException, AnnuncioException, BachecaException {	
		Annuncio a = null;
		int result = JOptionPane.showConfirmDialog(null, addPanel, msg, JOptionPane.CANCEL_OPTION);		
		if(check.isSelected()) {
			System.out.println("vendita");
			if(data.getText().isBlank()) throw new AnnuncioException(Costanti.ECC_DATA_NULL);
			tipologia = 'v';
			LocalDate dataF = LocalDate.parse(data.getText());
			a = new Annuncio(u, tipologia, nomArt.getText(), Double.parseDouble(prezzo.getText()), cat.getText(), dataF, cod++);
			
		}else {
			System.out.println("acquisto");
			tipologia = 'a';
			a = new Annuncio(u, tipologia, nomArt.getText(), Double.parseDouble(prezzo.getText()), cat.getText(), cod++);
		}
		if (result == JOptionPane.OK_OPTION) { 
			main.bacheca.aggiungiAnnuncio(a);
			return main.bacheca.listaAnnunciParolaChiave(cat.getText());
		} else {
			return main.bacheca.getBacheca();
		}
	}
	
	public ArrayList<Annuncio> getCerca(String msg) throws BachecaException {
		ArrayList<Annuncio> res;  
		int result = JOptionPane.showConfirmDialog(null, cercaPanel, msg, JOptionPane.CANCEL_OPTION);		
		if (result == JOptionPane.OK_OPTION) { 
			res =  main.bacheca.listaAnnunciParolaChiave(nomArt.getText());
			System.out.println(main.bacheca.listaAnnunciParolaChiave(""));
			return res;
		}
		else {
			return null;
		}
		
	}
	
	public Utente inputUtente(String msg) throws UtenteException {
		int result = JOptionPane.showConfirmDialog(null, inputUtente, msg, JOptionPane.CANCEL_OPTION);	
		if (result == JOptionPane.OK_OPTION) { 
			return new Utente(emailUtente.getText() ,nomeUtente.getText());
		}else {
			return null;
		}
	}
	
	public ArrayList<Annuncio> inputPulisci(String msg) throws BachecaException{
			main.bacheca.pulisciBacheca();
			return main.bacheca.getBacheca();
	}
	
	public ArrayList<Annuncio> mostraBacheca(String msg) throws BachecaException{
			return main.bacheca.getBacheca();
	}
	
	
}
