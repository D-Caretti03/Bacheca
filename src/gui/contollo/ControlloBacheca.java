package gui.contollo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import bachecaAnnunci.Annuncio;
import main.main;
import bachecaAnnunci.Bacheca;
import bachecaAnnunci.Utente;
import exceptions.AnnuncioException;
import exceptions.BachecaException;
import exceptions.UtenteException;
import guiVista.ContentPanel;
import guiVista.DialogoContatto;

public class ControlloBacheca implements ActionListener{
	
	private Bacheca model;
	private ContentPanel view;   //Campi del contollo
	private ArrayList<Annuncio> input;
	public Utente u;
	  
	public ControlloBacheca(ContentPanel view, Bacheca model) {
		this.model = model;
		this.view = view;
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if(source.getText().equals("Aggiungi")) {
			System.out.println("Aggiungi");
			try {
				try {
					input = new DialogoContatto().getInputs("Aggiungi", u);
				} catch (BachecaException e1) {
					e1.printStackTrace();
				}
				
			} catch (UtenteException | AnnuncioException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(input !=null) {
				System.out.println("Aggiungi");
			}
			try {
				view.upDateView(input);
			} catch (BachecaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}else if(source.getText().equals("Cerca")) {
			System.out.println("Cerca");
			try {
				input = new DialogoContatto().getCerca("Cerca");
			} catch (BachecaException e1) {
				e1.printStackTrace();
			}
			if(input !=null) {
				System.out.println("Cerca");
			}
			try {
				view.upDateView(input);
			} catch (BachecaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(source.getText().equals("login")) {
			System.out.println("login");
			try {
				u = new DialogoContatto().inputUtente("login");
				model.login = u;
			} catch (UtenteException e1) {
				e1.printStackTrace();
			};
		}else if(source.getText().equals("Rimuovi")) {
			System.out.println("rimuovi");
		}else if(source.getText().equals("Pulisci Bacheca")) {
			System.out.println("Pulisci Bacheca");
		}
	}
}
