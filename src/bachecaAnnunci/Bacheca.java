package bachecaAnnunci;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import exceptions.BachecaException;
import utilities.Costanti;

public class Bacheca implements Iterable<Annuncio>{
	private ArrayList<Annuncio> bacheca;

	public Bacheca() {
		super();
		bacheca = new ArrayList<Annuncio>();
	}
	
	public boolean aggiungiAnnuncio(Annuncio a) {
		bacheca.add(a);
		return true;
	}

	public ArrayList<Annuncio> listaAnnunciParolaChiave(String parolaChiave) throws BachecaException{
		if(parolaChiave == null) throw new BachecaException(Costanti.ECC_PAROLA_CHIAVE_NULL);
		ArrayList<Annuncio> annunci = new ArrayList<Annuncio>();
		String[] pChiave = parolaChiave.split(", ");
		for(Annuncio a: bacheca) {
			String pChiaveAnn = a.getParolaChiave().toLowerCase();
			HashSet<String> paroleChiaveAnn = new HashSet<>(Arrays.asList(pChiaveAnn.split(", ")));
			for(String parola: pChiave) {
				parola = parola.trim().toLowerCase();
				if(paroleChiaveAnn.contains(parola) && !annunci.contains(a))
					annunci.add(a);
			}
		}
		return annunci;
	}

	@Override
	public Iterator<Annuncio> iterator() {
		return this.bacheca.iterator();
	}

	public Annuncio getAnnuncio(int i) throws BachecaException{
		if(i < 0) throw new BachecaException(Costanti.ECC_COD_NEG);
		Annuncio trovato = null;
		for(Annuncio a: bacheca) {
			if(a.getCodice()==i) trovato = a;
		}
		return trovato;
	}

	public boolean rimuoviAnnuncio(String email, int codice) throws BachecaException {
		Annuncio elim = null;
		if(!controlloCodiceBacheca(codice))
			throw new BachecaException(Costanti.ECC_CODICE_ERR);
		for(Annuncio a: bacheca) {
			Utente u = a.getUtente();
			if(!(u.getEmail().equals(email)) && codice == a.getCodice())
				throw new BachecaException(Costanti.ECC_NON_PROPRIETARIO);
			else
				elim = getAnnuncio(codice);
		}
		return bacheca.remove(elim);
	}
	
	public boolean controlloCodiceBacheca(int codice) {
		for(Annuncio a: bacheca) {
			if(a.getCodice() == codice)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		System.out.println("Bacheca : \n");
		for(Annuncio a: bacheca)
			System.out.println(a.toString()+ "\n");
		return "";
	}
	
	public boolean pulisciBacheca() throws BachecaException{
		LocalDate oggi = LocalDate.now(); 
		ArrayList<Annuncio> elim = new ArrayList<Annuncio>();
		for(Annuncio a: bacheca) {
			if(a.getDate() != null) {
				if(a.getDate().isBefore(oggi)) {
					elim.add(a);
				}
			}
		}
		return bacheca.removeAll(elim);
	}

}
