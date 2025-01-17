package guiVista;

import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Utente;
import exceptions.AnnuncioException;
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
		check = new JCheckBox();
		prezzo = new JTextField(15);
		data = new JTextField(15);
		nomeUtente = new JTextField(15);
		emailUtente = new JTextField(15);
		
		addPanel = new JComponent[] { new JLabel("Articolo"), nomArt, new JLabel("Categoria"), cat, new JCheckBox("vendita"), new JLabel("Prezzo"), prezzo, new JLabel("Data"), data};
		cercaPanel = new JComponent[] { new JLabel("Articolo"), nomArt};
		inputUtente = new JComponent[] { new JLabel("Nome Utente"), nomeUtente, new JLabel("e-mail Utente"), emailUtente};
	}

	public Annuncio getInputs(String msg, Utente u) throws UtenteException, AnnuncioException {
		Annuncio res = null;	
		int result = JOptionPane.showConfirmDialog(null, addPanel, msg, JOptionPane.CANCEL_OPTION);		
		if (result == JOptionPane.OK_OPTION) { 
			if(check.isSelected()) {
				tipologia = 'a';
				res = new Annuncio(u, tipologia, nomArt.getText(), Double.parseDouble(prezzo.getText()), cat.getText(), cod++);
			}else {
				tipologia = 'v';
				LocalDate dataF = LocalDate.parse(data.getText());
				res = new Annuncio(u, tipologia, nomArt.getText(), Double.parseDouble(prezzo.getText()), cat.getText(), dataF, cod++);
			}
			return res;
		} else {
			return null;
		}
	}
	
	public String getCerca(String msg) {
		String res;
		ContentPanel.x = 0;
		ContentPanel.y = 0;
		int result = JOptionPane.showConfirmDialog(null, cercaPanel, msg, JOptionPane.CANCEL_OPTION);		
		if (result == JOptionPane.OK_OPTION) { 
			return res = nomArt.getText();
		}else {
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
}
