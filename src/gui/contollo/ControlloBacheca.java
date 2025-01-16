package gui.contollo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import bachecaAnnunci.Annuncio;
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
	
	private Utente u;
	
	public ControlloBacheca(ContentPanel view, Bacheca model) {
		this.model = model;
		this.view = view;
	}

	@Override 
	public void actionPerformed(ActionEvent e) {
		Annuncio input = null;
		JButton source = (JButton) e.getSource();
		if(source.getText().equals("Aggiungi")) {
			System.out.println("Aggiungi");
			try {
				input = new DialogoContatto().getInputs("Aggiungi Contatto");
				
			} catch (UtenteException | AnnuncioException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(input !=null) {
				model.aggiungiAnnuncio(input);
				System.out.println("Aggiungi");
			}
			view.upDateView(null);
			
		}else if(source.getText().equals("Cerca")) {
			System.out.println("Cerca");
			String cerca = new DialogoContatto().getCerca("Cerca");
			if(input !=null) {
				model.aggiungiAnnuncio(input);
				System.out.println("Aggiungi");
			}
			view.upDateView(cerca);
		}
		
	}
}
