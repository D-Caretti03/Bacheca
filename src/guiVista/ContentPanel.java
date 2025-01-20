package guiVista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bachecaAnnunci.Annuncio;
import bachecaAnnunci.Bacheca;
import bachecaAnnunci.Utente;
import exceptions.BachecaException;
import gui.BachecaGui;
import gui.contollo.ControlloBacheca;

/**
 * Questa classe si occupa della creazione della bacheca all'interno della nostra GUI
 * 
 * @param model 	oggetto di tipo bacheca per impostare la bacheca grafica
 * @param annuncio 	oggetto JPanel per creare vari annunci prensenti nella bacheca
 * @param nom 		oggetto di tipo JLabel che contiene una stringa che viene mandata a schermo nell' panel annuncio
 * @param nomArt 	oggetto di tipo JTextField che contiene il nome dell' articolo dell' annuncio
 * @param prez 		oggetto di tipo JLabel che contiene una stringa che viene mandata a schermo nell' panel annuncio
 * @param prezArt	oggetto di tipo JTextField che contiene il prezzo dell' articolo dell' annuncio
 * @param acquista	oggetto di tipo JButton che simula l' acquisto del prodotto venduto
 * @param rimuovi 	oggetto di tipo JButton che rimuove l' articolo, compare solo se si è proprietari dell' annunio
 * @param data		oggetto di tipo JLabel che contiene una stringa che viene mandata a schermo nell' panel annuncio
 * @param dataArt	oggetto di tipo JTextField che contiene la data di scadenza dell' annuncio
 * @param pChiave 	oggetto di tipo JLabel che contiene una stringa che viene mandata a schermo nell' panel annuncio
 * @param pChiave 	oggetto di tipo JTextArea nel quale viene inserita una stringa che conterrà le parole chiave per la ricerca
 * @param x 		variabile di tipo intero che va a definire la posizione sull asse orizzontale dell' annuncio creato
 * @param y 		variabile di tipo intero che va a definire la posizione sull asse verticale dell' annuncio creato
 */
public class ContentPanel extends JPanel{
	
	//private static final ActionListener null = null;
	private Bacheca model;
	private JPanel annuncio;
	private JLabel nom;
	private JTextField nomArt;
	private JLabel prez;
	private JTextField prezArt;
	private JButton acquista;
	private JButton rimuovi;
	private JLabel data;
	private JTextField dataArt;
	private JLabel pChiave;
	private JTextArea pChiaveArt;
	ControlloBacheca controllo;
	static int x = 0;
	static int y = 0;
	
	/**
	 * Costruttore della classe ContentPanel che imposta il Layout dell Panel e inserisce la view
	 * @param model		oggetto di tipo Bacheca che fornisce i dati per visualizzare gli annunci
	 * @throws BachecaException
	 */
	public ContentPanel(Bacheca model) throws BachecaException {
		this.model = model;
		setLayout(new GridBagLayout());
			
		upDateView(model.getBacheca());
		
	}
	

	/**
	 * Metodo void che va a creare e aggiornare la vista della nosta bacheca.
	 * Grazie all' iteratore andremo a visitare tutto l' ArrayList e comporremo il nostro ContentPanel,
	 * impostando anche la posizione di ogni elemento Jpanel che viene creato.
	 * è stato inserito anche un ActionLlistener che su un pulsante rimuovi per fare la remove di un elemento
	 * @param list 	Parametro passato al metodo sulla quale basare la creazione del content Panel
	 * @throws BachecaException
	 */
	public void upDateView(ArrayList<Annuncio> list) throws BachecaException {
		removeAll();
		if(list != null) {
		for(Annuncio a : list) {
			creaInserz(a);
			if(a.getTipologia() == 'v') {
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
			annuncio.add(pChiave, gbc);
			
			gbc.gridx = 1;
			gbc.gridy = 2;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			gbc.fill = GridBagConstraints.BOTH;
			annuncio.add(pChiaveArt, gbc);
			
			if(a.getTipologia() == 'v') {
				gbc.gridx = 0;
				gbc.gridy = 3;
				gbc.weightx = 0.0;
				gbc.weighty = 0.0;
				annuncio.add(data, gbc);
				
				gbc.gridx = 1;
				gbc.gridy = 3;
				gbc.weightx = 0.0;
				gbc.weighty = 0.0;
				annuncio.add(dataArt, gbc);
			}
			
			gbc.gridx = 0;
			gbc.gridy = 4;
			gbc.weightx = 0.0;
			gbc.weighty = 0.0;
			if(model.login != null) {
				if(a.getUtente().getEmail().equals(model.login.getEmail())){
					rimuovi = new JButton("Rimuovi");
					rimuovi.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								model.rimuoviAnnuncio(model.login.getEmail(), a.getCodice());
								upDateView(model.getBacheca());
							} catch (BachecaException e1) {
								e1.printStackTrace();
							}
						}
					});
					annuncio.add(rimuovi, gbc);		
				}
				else {
					if(a.getTipologia() == 'v'){
						acquista = new JButton("Acquista");
						annuncio.add(acquista, gbc);
					}else {
						acquista = new JButton("Vendi");
						annuncio.add(acquista, gbc);
					}
				}
				}
			
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
	}
		x = 0;
		y = 0;
		if(BachecaGui.BachecaPanel != null) {
			BachecaGui.BachecaPanel.revalidate();
			BachecaGui.BachecaPanel.repaint();
		}
}
			
	
/**
 * Metodo per inizializzare il panel dell' annuncio, impostando il Layout e inserendo tutti gli elementi 
 * per dare informazioni sull' annuncio.
 * Impostando anche se l' annuncio è di vendita o di acquisto
 * @param a
 * @throws BachecaException
 */
	public void creaInserz(Annuncio a) throws BachecaException {
		annuncio = new JPanel();
		annuncio.setLayout(new GridBagLayout());
		nom = new JLabel("Prodotto: ");
		nomArt = new JTextField(15);
		prez = new JLabel("Prezzo: ");
		prezArt = new JTextField(15);
		nomArt.setText(a.getArticolo());
		nomArt.setEditable(false);
		prezArt.setText(Double.toString(a.getPrezzo()));
		prezArt.setText(Double.toString(a.getPrezzo()));
		prezArt.setEditable(false);
		pChiave = new JLabel("Parole chiave: ");
		pChiaveArt = new JTextArea(a.getParolaChiave());
		pChiaveArt.setLineWrap(true);
		pChiaveArt.setWrapStyleWord(true);
		pChiaveArt.setEditable(false);
		if(a.getTipologia() == 'v') {
			data = new JLabel("Data scadenza: ");
			dataArt = new JTextField(15);
			dataArt.setText(a.getDate().toString());
			dataArt.setEditable(false);
			annuncio.setBorder(BorderFactory.createTitledBorder("Annuncio di Vendita: " + a.getUtente().getNome()));
		}
		else {
			annuncio.setBorder(BorderFactory.createTitledBorder("Annuncio di Acquisto " + a.getUtente().getNome()));
		}
	}
}
