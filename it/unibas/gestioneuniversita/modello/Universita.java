package it.unibas.gestioneuniversita.modello;

import java.util.*;

public class Universita {
	private String nome;
	private String sede;
	private List<Studente> listaStudenti = new ArrayList<Studente>();

	public Universita(String nome, String sede) {
		this.nome = nome;
		this.sede = sede;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSede() {
		return this.sede;
	}

	public List<Studente> getListaStudenti() {
		return this.listaStudenti;
	}

	public void addStudente(Studente studente) {
		this.listaStudenti.add(studente);
	}

	private List<Studente> listaStudentiFuoriSede() {
		List<Studente> lista = new ArrayList<Studente>();
		for(Studente studente : this.listaStudenti) {
			if(studente.getCitta() != this.sede) {
				lista.add(studente);
			}
		}
		return lista;
	}

	public double percentualeFuorisede() {
		double numeroStudenti = this.listaStudenti.size();
		double numeroFuoriSede = this.listaStudentiFuoriSede().size();
		return ((numeroFuoriSede/numeroStudenti)*100);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nNome: " + this.nome);
		sb.append("\nSede: " + this.sede);
		for(Studente studente : this.listaStudenti) {
			sb.append(studente.toString());
		}
		return sb.toString();
	}
}