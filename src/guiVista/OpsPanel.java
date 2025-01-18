package guiVista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.contollo.ControlloBacheca;

public class OpsPanel extends JPanel{
	public OpsPanel(ControlloBacheca controllo) {
		setLayout(new FlowLayout());
		
		JButton login = new JButton("Login");
		JButton addAnnuncio = new JButton("Aggiungi");
		JButton cerca = new JButton("Cerca");
		JButton rimuoviAnnuncio = new JButton("Pulisci Bacheca");
		JButton mostra = new JButton("Mostra Bacheca");
		
		addAnnuncio.addActionListener(controllo);
		rimuoviAnnuncio.addActionListener(controllo);
		cerca.addActionListener(controllo);
		login.addActionListener(controllo);
		mostra.addActionListener(controllo);
		
		add(login);
		add(addAnnuncio);
		add(cerca);
		add(rimuoviAnnuncio);
		add(mostra);
		
	}
} 
