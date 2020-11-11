package it.unibas.gestioneuniversita.modello;

public class Studente implements Comparable<Studente>{
	private String codice;
	private String nome;
	private String cognome;
	private int eta;
	private int voto;
	private String citta;
	private String regione;

	public Studente (String codice, String nome, String cognome, int eta, int voto, String citta, String regione) {
		if(codice.length() != 6) {
			throw new IllegalArgumentException("Errore, il codice deve essere di 6 caratteri");
		}
		this.codice = codice;
		this.nome = nome;
		this.cognome = cognome;
		this.eta = eta;
		this.voto = voto;
		this.citta = citta;
		this.regione = regione;
	}

	public String getCodice() {
		return this.codice;
	}

	public String getNome() {
		return this.nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public int getEta() {
		return this.eta;
	}

	public int getVoto() {
		return this.voto;
	}

	public String getCitta() {
		return this.citta;
	}

	public String getRegione() {
		return this.regione;
	}

	public String codiceNomeCognome() {
		return this.codice + "," + this.nome + "," + this.cognome;
	}

	public String nomeCognome() {
		return this.nome + "," + this.cognome;
	}

	public int compareTo(Studente altroStudente){
		if(this.getEta() != altroStudente.getEta()) {
			return this.getEta() - altroStudente.getEta();
		}
		return this.getCognome().compareTo(altroStudente.getCognome());
	}

	public int hashCode() {
		return this.toString().hashCode();
	}

	public boolean equals(Object o) {
		Studente altroStudente = (Studente)o;
		return this.toString().equals(altroStudente.toString());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nCodice: " + this.codice);
		sb.append("\nNome: " + this.nome);
		sb.append("\nCognome: " + this.cognome);
		sb.append("\nEta: " + this.eta);
		sb.append("\nVoto: " + this.voto);
		sb.append("\nCitta: " + this.citta);
		sb.append("\nRegione: " + this.regione);
		return sb.toString();
	}
	
}