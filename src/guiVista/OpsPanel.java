package guiVista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.contollo.ControlloBacheca;

public class OpsPanel extends JPanel{
	public OpsPanel(ControlloBacheca controllo) {
		setLayout(new FlowLayout());
		JButton addAnnuncio = new JButton("Aggiungi");
		JButton rimuoviAnnuncio = new JButton("Rimuovi");
		JButton cerca = new JButton("Cerca");
		
		addAnnuncio.addActionListener(controllo);
		rimuoviAnnuncio.addActionListener(controllo);
		cerca.addActionListener(controllo);
		
		add(addAnnuncio);
		add(rimuoviAnnuncio);
		add(cerca);
	}
} 
