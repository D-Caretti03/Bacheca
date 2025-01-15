package bachecaAnnunci;

import java.time.LocalDate;
import java.util.ArrayList;
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
		boolean inserita;
		for(Annuncio a: bacheca) {
			inserita = false;
			for(String parola: pChiave) {
				if(a.getParolaChiave(a).contains(parola) && !inserita) {
					annunci.add(a);
					inserita = true;
				}
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
			if(a.getCodice(a)==i) trovato = a;
		}
		return trovato;
	}

	public boolean rimuoviAnnuncio(String email, int codice) throws BachecaException {
		Annuncio elim = null;
		for(Annuncio a: bacheca) {
			Utente u = a.getUtente(a);
				
			if(!(u.getEmail(u).equals(email)) && codice == a.getCodice(a))
				throw new BachecaException(Costanti.ECC_NON_PROPRIETARIO);
			else
				elim = getAnnuncio(codice);
		}
		return bacheca.remove(elim);
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
			if(a.getDate(a) != null) {
				if(a.getDate(a).isBefore(oggi)) {
					elim.add(a);
				}
			}
		}
		return bacheca.removeAll(elim);
	}

}
