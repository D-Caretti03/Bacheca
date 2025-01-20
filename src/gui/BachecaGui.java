package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import guiVista.BachecaPanel;
import bachecaAnnunci.Bacheca;
import exceptions.BachecaException;

import javax.swing.JPanel;

/**
 * Questa classe serve per iniziallizare la finestra grafica della nostra applicazione
 * 
 * <p>
 * 
 * Questa sarà la finestra principale della nosta GUI
 * 
 * @param BachecaPanel 	oggetto di tipo frame che costituirà la parte principla della GUI
 */
public class BachecaGui extends JFrame{
	public static JPanel BachecaPanel;
	
	/**
	 * Questo è il costruttore della nostra classe che prende come parametro model, possiamo anche lanciare un 
	 * eccezione per la bacheca nel caso in cui qualcosa non vada
	 * 
	 * <p>
	 * 
	 * Qui imposteremo la chiusura e terminazione dell' applicazione, le dimensione e posizione della finestra,
	 * il suo titolo, le dimensioni minime e infine creeremo un un oggetto BachecaPanel che sarà il contenuto 
	 * principale della GUI
	 * @param model 	oggetto di tipo Bacheca passato come parametro al costruttore
	 * @throws BachecaException
	 */
	public BachecaGui(Bacheca model) throws BachecaException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 700);
		setTitle("Bacheca");
		setMinimumSize(new Dimension(1500, 1000));
		BachecaPanel = new BachecaPanel(model);
		setContentPane(BachecaPanel);
		setVisible(true);
	}
}
