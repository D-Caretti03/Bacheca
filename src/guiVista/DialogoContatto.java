package guiVista;

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
	private JTextField nomArt, cat;
	private JCheckBox check;
	private JTextField fPrezzo;
	private JTextField prezzo;
	private JComponent[] addPanel;
	private JComponent[] cercaPanel;
	private char tipologia;
	public DialogoContatto() {
// sicerano le componenti grafiche che andranno nella finestra di dialogo
		nomArt = new JTextField(20);
		cat = new JTextField(20); 
		check = new JCheckBox();
		prezzo = new JTextField(15);
		
		addPanel = new JComponent[] { new JLabel("Articolo"), nomArt, new JLabel("Categoria"), cat, new JCheckBox("vendita"), new JLabel("Prezzo"), prezzo};
		cercaPanel = new JComponent[] { new JLabel("Articolo"), nomArt};
	}

	public Annuncio getInputs(String msg) throws UtenteException, AnnuncioException {
		Annuncio res = null;	
		Utente u = new Utente("Daniele", "Dica@ra");
		int result = JOptionPane.showConfirmDialog(null, addPanel, msg, JOptionPane.CANCEL_OPTION);		
		if (result == JOptionPane.OK_OPTION) { 
			if(check.isSelected()) {
				tipologia = 'a';
			}else {
				tipologia = 'v';
			}
			res = new Annuncio(u, tipologia, nomArt.getText(), Double.parseDouble(prezzo.getText()), cat.getText(), 1);
			return res;
		} else {
			return null;
		}
	}
	
	public String getCerca(String msg) {
		String res;
		int result = JOptionPane.showConfirmDialog(null, cercaPanel, msg, JOptionPane.CANCEL_OPTION);		
		if (result == JOptionPane.OK_OPTION) { 
			return res = nomArt.getText();
		}else {
			return null;
		}
	}
}
