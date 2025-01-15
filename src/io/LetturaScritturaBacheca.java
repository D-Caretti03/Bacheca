package io;

import java.io.*;
import java.time.LocalDate;

import bachecaAnnunci.*;
import exceptions.AnnuncioException;
import exceptions.UtenteException;

public class LetturaScritturaBacheca {

	public static final String percorso = new File("").getAbsoluteFile() + "/src/fileTesto/";
	private static final String separatore = ";";
	
	
	public static Bacheca letturaBacheca(String file) throws IOException, ClassNotFoundException, UtenteException, AnnuncioException{
		Bacheca b = new Bacheca();
		file = percorso + file;
		BufferedReader in = new BufferedReader(new FileReader(file));
		int codice = 1;
		String linea;
		while((linea = in.readLine()) != null) {
			String[] dati = linea.split(separatore);
			if(dati.length >= 6) {
				String email = dati[0].trim();
				String nome = dati[1].trim();
				char tipologia = dati[2].trim().charAt(0);
				String oggetto = dati[3].trim();
				double prezzo = Double.parseDouble(dati[4]);
				String parolaChiave = dati[5].trim();
				LocalDate scadenza = null;
				
				if(tipologia == 'v' && dati.length == 7)
					scadenza = LocalDate.parse(dati[6]);
				
				Utente u = new Utente(email, nome);
				Annuncio a;
				if(scadenza != null)
					a = new Annuncio(u, tipologia, oggetto, prezzo, parolaChiave, scadenza, codice);
				else 
					a = new Annuncio(u, tipologia, oggetto, prezzo, parolaChiave, codice);
				b.aggiungiAnnuncio(a);
			}
			codice++;
		}
		return b;
		
	}
	
	public static boolean scritturaBacheca(Bacheca b, String file) throws IOException {
		file = percorso + file;
		
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		
		for(Annuncio a : b) {
			out.write(a.toString());
			out.newLine();
		}
		out.close();
		return true;
	}
}
