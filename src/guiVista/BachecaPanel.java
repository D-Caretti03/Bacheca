package guiVista;

import bachecaAnnunci.Bacheca;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.contollo.*;

public class BachecaPanel extends JPanel{
	public BachecaPanel(Bacheca model) {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ContentPanel contenutoBacheca = new ContentPanel(model);
		ControlloBacheca controllo = new ControlloBacheca(contenutoBacheca, model);
		OpsPanel operazioneBacheca = new OpsPanel(controllo);
		
		JPanel vistaBacheca = new JPanel();
		vistaBacheca.add(contenutoBacheca);
		add(vistaBacheca, BorderLayout.CENTER);
		add(operazioneBacheca, BorderLayout.NORTH);
	}
}
