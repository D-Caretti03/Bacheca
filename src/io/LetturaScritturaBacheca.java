package io;

import java.io.*;
import bachecaAnnunci.*;

public class LetturaScritturaBacheca {

	public static final String percorso = new File("").getAbsoluteFile() + "/src/fileTesto/";
	private static final String separatore = ";";
	
	
	public static Bacheca letturaBacheca(String file) throws IOException, ClassNotFoundException{
		file = percorso + file;
		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Bacheca bacheca = (Bacheca)in.readObject();
		in.close();
		fileIn.close();
		return bacheca;
	}
}
