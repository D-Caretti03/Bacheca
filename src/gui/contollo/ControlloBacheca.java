package gui.contollo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import bachecaAnnunci.Annuncio;
import main.main;
import bachecaAnnunci.Bacheca;
import bachecaAnnunci.Utente;
import exceptions.AnnuncioException;
import exceptions.BachecaException;
import exceptions.UtenteException;
import guiVista.ContentPanel;
import guiVista.DialogoContatto;

/** Questa classe si occupa del controllo degli eventi, chiamando in modo opportuno una determinata parte di codice
* in base al pulsante che viene premuto
* 
* <p>
* 
* @param model	oggetto di tipo Bacheca che il costruttore imposterà in base a ciò che riceve
* @param view	oggetto di tipo ContentPanel che serve per lo più ad aggiornare il ContentPanel
* @param input oggetto di tipo ArrayList che sarà usato per salvare il dato di ritorno da un metodo esterno
* @param u 	oggetto di tipo Utente che contiene i dati di chi ha fatto il login
*/
public class ControlloBacheca implements ActionListener{
	
	private Bacheca model;
	private ContentPanel view;   //Campi del contollo
	private ArrayList<Annuncio> input;
	public Utente u;
	  
	/**Costruttore della nostra classe 
	 * @param view	oggetto di tipo ContentPanel che contiene il nostro contentPanel
	 * @param model	oggetto di tipo Bacheca che contiene la nostra Bacheca
	 */
	public ControlloBacheca(ContentPanel view, Bacheca model) {
		this.model = model;
		this.view = view;
	}

	/**
	 * Metodo void override che in base all' ActionEvent ottenuto eseguira una certa azione
	 * In particolare l' oggetto fornito in ingresso ActionEvent dal quale viene estratto il testo del pulsante
	 * premuto, capendo così che azione eseguire
	 */
	@Override 
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if(source.getText().equals("Aggiungi")) {
			try {
				input = new DialogoContatto().getInputs("Aggiungi", u);
			} catch (UtenteException | AnnuncioException | BachecaException | NumberFormatException e1) {
				JOptionPane.showMessageDialog(null,  e1.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
			}
			try {
				view.upDateView(input);
			} catch (BachecaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(source.getText().equals("Cerca")) {
			try {
				input = new DialogoContatto().getCerca("Cerca");
			} catch (BachecaException e1) {
				e1.printStackTrace();
			}
			try {
				view.upDateView(input);
			} catch (BachecaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(source.getText().equals("Login")) {
			try {
				u = new DialogoContatto().inputUtente("Login");
				model.login = u;
			} catch (UtenteException e1) {
				JOptionPane.showMessageDialog(null,  e1.getMessage(), "ERROR", JOptionPane.WARNING_MESSAGE);
			};
			try {
				view.upDateView(model.getBacheca());
			} catch (BachecaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(source.getText().equals("Pulisci Bacheca")) {
			try {
				input = new DialogoContatto().inputPulisci("Pulisci Bacheca");
			} catch (BachecaException e1) {
				e1.printStackTrace();
			}
			try {
				view.upDateView(input);
			} catch (BachecaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(source.getText().equals("Mostra Bacheca")) {
			try {
				input = new DialogoContatto().mostraBacheca("Mostra Bacheca");
			} catch (BachecaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				view.upDateView(input);
			} catch (BachecaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
