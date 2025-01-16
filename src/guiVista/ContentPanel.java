package guiVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Bacheca;

public class ContentPanel extends JPanel{

	//private static final ActionListener null = null;
	private Bacheca model;
	private JPanel annuncio;
	private JLabel nom;
	private JTextField nomArt;
	private JLabel prez;
	private JTextField prezArt;
	private JButton acquista;
	private Annuncio ann;
	private static int stat = 0;
	public ContentPanel(Bacheca model) {
		this.model = model;
		setLayout(new GridBagLayout());
			
		upDateView(null);
		
	}
	
	public void upDateView(String ricerca) {
		int x = 0;
		int y = 0;
		for(Annuncio a : model) {
			creaInserz(a);
			if(a.getTipologia(a) == 'v') {
				annuncio.setBorder(BorderFactory.createTitledBorder("Annuncio di Vendita"));
			}
			else {
				annuncio.setBorder(BorderFactory.createTitledBorder("Annuncio di Acquisto"));
			}
			GridBagConstraints gbc = new GridBagConstraints();
			GridBagConstraints gbcTop = new GridBagConstraints();
			gbc.gridx = 0;
			gbc.gridy = 0; 
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			annuncio.add(nom, gbc);
				
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			annuncio.add(nomArt, gbc);
				
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			annuncio.add(prez, gbc);
				
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			annuncio.add(prezArt, gbc);
				
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			acquista = new JButton("acquista");
				//acquista.addActionListener(null);
			annuncio.add(acquista, gbc);
				
			gbcTop.gridx = x;
			gbcTop.gridy = y;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			add(annuncio, gbcTop);
			x++;
			if(x == 3) {
				x = 0;
				y++;
			}
		}
		repaint();
	}
	
	public void creaInserz(Annuncio a) {
		annuncio = new JPanel();
		annuncio.setLayout(new GridBagLayout());
		ann = a;
		nom = new JLabel("Prodotto: ");
		nomArt = new JTextField(15);
		prez = new JLabel("Prezzo: ");
		prezArt = new JTextField(15);
		nomArt.setText(ann.getArticolo(ann));
		nomArt.setEditable(false);
		prezArt.setText(Double.toString(ann.getPrezzo(ann)));
		prezArt.setEditable(false);
	}
}
