package it.unibas.gestioneuniversita.modello;

import java.util.*;

public class ComparatorePerVoto implements Comparator<Studente> {
	public int compare(Studente studente1, Studente studente2) {
		if(studente1.getVoto() != studente2.getVoto()) {
			return studente2.getVoto() - studente1.getVoto();
		}
		return studente1.getEta() - studente2.getEta();
	}
}