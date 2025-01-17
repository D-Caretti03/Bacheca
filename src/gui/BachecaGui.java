package gui;

import javax.swing.JFrame;
import guiVista.BachecaPanel;
import bachecaAnnunci.Bacheca;
import exceptions.BachecaException;

import javax.swing.JPanel;

public class BachecaGui extends JFrame{
	public BachecaGui(Bacheca model) throws BachecaException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 700);
		setTitle("Gui");
		
		JPanel BachecaPanel = new BachecaPanel(model);
		setContentPane(BachecaPanel);
		setVisible(true);
	}
}
