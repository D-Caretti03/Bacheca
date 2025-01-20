package guiVista;

import bachecaAnnunci.Bacheca;
import exceptions.BachecaException;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.contollo.*;

/**
 * Quest classe imposta, grazie al suo costruttore, come sarà organizzata la GUI
 * 
 * @param vistaBacheca	oggetto di tipo JPanel che sarà inserito nella GUI
 */
public class BachecaPanel extends JPanel{
	JPanel vistaBacheca;
	
	/**
	 * Costruttore della classe BachecaPanel, qui chiameremo una serie di oggetti che faranno funzionare la 
	 * nostra applicazione.
	 * ContentPanel per la parte della bacheca, ControlloBacheca per le azioni che compiremo nella gui,
	 * di solito la pressione di un pulsante, OpsPanel per la parte in cui ci sono i pulsani che contollano le
	 * azioni possibili nella nosta bacheca
	 * 
	 * @param model		oggetto di tipo bacheca che passiamo al ContentPanel per costruire la Bbacheca grafica
	 * @throws BachecaException
	 */
	public BachecaPanel(Bacheca model) throws BachecaException {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ContentPanel contenutoBacheca = new ContentPanel(model);
		ControlloBacheca controllo = new ControlloBacheca(contenutoBacheca, model);
		OpsPanel operazioneBacheca = new OpsPanel(controllo);
		
		vistaBacheca = new JPanel();
		vistaBacheca.add(contenutoBacheca);
		add(vistaBacheca, BorderLayout.CENTER);
		add(operazioneBacheca, BorderLayout.NORTH);
	}
	
	/**
	 * Metodo void che ci aiuta a riscrivere la pagina principlae della GUI con le informazioni aggiornate
	 */
	public void aggiorna() {
		revalidate();
		repaint();
	}
}
