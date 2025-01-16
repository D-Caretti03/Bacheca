package bachecaAnnunci;

import exceptions.UtenteException;
import utilities.Costanti;

public class Utente {
	private String email;
	private String nome;
	
	
	public Utente(String email, String nome) throws UtenteException{
		super();
		if(email == null) throw new  UtenteException(Costanti.ECC_EMAIL_NULL);
		if(email.isBlank()) throw new UtenteException(Costanti.ECC_EMAIL_VUOTA);
		if(!email.contains("@") || !email.contains(".")) throw new UtenteException(Costanti.ECC_EMAIL_ERRATA);
		if(nome == null) throw new UtenteException(Costanti.ECC_NOME_NULL);
		if(nome.isBlank()) throw new UtenteException(Costanti.ECC_NOME_VUOTO);
		this.email = email;
		this.nome = nome;
	}


	@Override
	public String toString() {
		return "[email=" + email + ", nome=" + nome + "]";
	}


	public String getEmail() {
		return this.email;
	}
	
}
