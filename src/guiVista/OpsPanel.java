package guiVista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import gui.contollo.ControlloBacheca;

/**
 * In questa classe serve per impostare la parte di controllo della nostra GUI
 * <p>
 * 
 * La parte fondamentale, oltre a inserire i pulsanti di classe JButton nel panel, c'è anche la parte di contollo 
 * che si occupa di eseguire certe azioni quando un pulsante viene premuto
 */
public class OpsPanel extends JPanel{
	
	/**
	 * Costruttore della nostra classe OpsPanel nel quale impostiamo il Layout di questa parte della GUI e inseriremo i 
	 * vari pulsanti per il controllo
	 * @param controllo	oggetto di tipo controllo che eseguirà determinate azioni in base al pulsante premuto
	 */
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
